package com.greyson.bookstore.service;

import com.greyson.bookstore.entity.Book;
import com.greyson.bookstore.common.CommonResponse;
import com.greyson.bookstore.common.Constants;
import com.greyson.bookstore.config.BookMapper;
import com.greyson.bookstore.dao.BookDao;
import com.greyson.bookstore.repository.BookRepository;
import com.greyson.bookstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookStoreService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public CommonResponse saveBooks(List<BookDao> bookDaoList) {
        log.info("Start to save book");
        List<Book> bookList = new ArrayList<>();
        int failed = 0;
        List<String> failedBookIds = new ArrayList<>();
        for (BookDao bookDao : bookDaoList) {
            Book book = BookMapper.INSTANCE.convertBookDaoToBook(bookDao);
            if (categoryRepository.findIdCountByName(bookDao.getCategory()) < 1) {
                log.error("There is no category : {} for book + [{}]", bookDao.getCategory(), bookDao.getTitle());
                failed++;
                if(failedBookIds.isEmpty()) failedBookIds.add(Constants.ADD_FAILED_BOOK_ID);
                failedBookIds.add(bookDao.getBookId());
                continue;
            }

            // set category id for book
            book.setCategoryId(categoryRepository.findIdByName(bookDao.getCategory()));

            // Check the database has the book's stock, if yes add it to original quantity
            String quantity = bookRepository.findBookQuantityByBookId(book.getBookId());
            Boolean hasNoOriginQuantity = quantity == null || quantity.isEmpty() || Integer.parseInt(quantity) == 0;
            book.setQuantity(hasNoOriginQuantity ? book.getQuantity() : book.getQuantity() + Integer.parseInt(quantity));
            bookList.add(book);
        }
        bookRepository.saveAll(bookList);

        String message = String.format(Constants.SAVE_RESULT_MESSAGE, bookDaoList.size(), failed);
        log.info(message);
        return CommonResponse.response(
                HttpStatus.OK.value(),
                message,
                failedBookIds);
    }

    public List<Book> retrieveAllBooks() {
        log.info("Start to retrieve books.");
        return bookRepository.findAllAvailableBooks();
    }
}
