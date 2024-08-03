package com.zand.bookstore.service;

import com.zand.bookstore.common.CommonResponse;
import com.zand.bookstore.common.Constants;
import com.zand.bookstore.config.BookMapper;
import com.zand.bookstore.dao.BookDao;
import com.zand.bookstore.entity.Book;
import com.zand.bookstore.repository.BookRepository;
import com.zand.bookstore.repository.CategoryRepository;
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
        for (BookDao bookDao : bookDaoList) {
            Book book = BookMapper.INSTANCE.convertBookDaoToBook(bookDao);
            if (categoryRepository.findIdCountByName(bookDao.getCategory()) < 1) {
                log.error("There is no category : {} for book + [{}]", bookDao.getCategory(), bookDao.getTitle());
                failed++;
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
                null);
    }

    public List<Book> retrieveAllBooks() {
        log.info("Start to retrieve books.");
        return bookRepository.findAllAvailableBooks();
    }
}
