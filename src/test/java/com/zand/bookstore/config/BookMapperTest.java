package com.zand.bookstore.config;

import com.zand.bookstore.dao.BookDao;
import com.zand.bookstore.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookMapperTest {

    @Test
    void convertBookDaoToBook() {
        BookDao bookDao = new BookDao();
        bookDao.setTitle("test1");
        bookDao.setQuantity(1);
        bookDao.setCategory("test");
        bookDao.setBookId("test2");
        bookDao.setAuthor("test3");
        bookDao.setPrice(2.3);
        Book book = BookMapper.INSTANCE.convertBookDaoToBook(bookDao);

        Assertions.assertEquals(bookDao.getBookId(), book.getBookId());
        Assertions.assertEquals(bookDao.getTitle(), book.getTitle());
        Assertions.assertEquals(bookDao.getPrice(), book.getPrice());
        Assertions.assertNull(book.getCategoryId());
        Assertions.assertEquals(bookDao.getAuthor(), book.getAuthor());

    }

    @Test
    void convertBookToBookDao() {
        Book book = new Book();
        book.setTitle("test1");
        book.setQuantity(1);
        book.setCategoryId(1L);
        book.setBookId("test2");
        book.setAuthor("test3");
        book.setPrice(2.3);
        BookDao bookDao = BookMapper.INSTANCE.convertBookToBookDao(book);

        Assertions.assertEquals(book.getBookId(), bookDao.getBookId());
        Assertions.assertEquals(book.getTitle(), bookDao.getTitle());
        Assertions.assertEquals(book.getPrice(), bookDao.getPrice());
        Assertions.assertNull(bookDao.getCategory());
        Assertions.assertEquals(book.getAuthor(), bookDao.getAuthor());
    }
}