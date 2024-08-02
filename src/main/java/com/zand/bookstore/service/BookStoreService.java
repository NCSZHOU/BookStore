package com.zand.bookstore.service;

import com.zand.bookstore.config.BookMapper;
import com.zand.bookstore.dao.BookDao;
import com.zand.bookstore.entity.Book;
import com.zand.bookstore.entity.Category;
import com.zand.bookstore.repository.BookRepository;
import com.zand.bookstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookStoreService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public boolean saveBook(BookDao bookDao) {
        Book book = BookMapper.INSTANCE.convertBookDaoToBook(bookDao);
        if(categoryRepository.findIdCountByTitle(bookDao.getCategory()) < 1) {
            log.error("There is no category : {}", bookDao.getCategory());
            return false;
        }

        book.setCategoryId(categoryRepository.findIdByTitle(bookDao.getCategory()));
        bookRepository.save(book);
        log.info("Save book {} successful", book.getTitle());
        return true;
    }
    public List<Book> retrieveAllBooks() {
        return bookRepository.findAllAvailableBooks();
    }
}
