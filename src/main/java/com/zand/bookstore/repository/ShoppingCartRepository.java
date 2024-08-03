package com.zand.bookstore.repository;

import com.zand.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select book from Book book where book.isAvailable=true and book.stock > 0")
    List<Book> findAllAvailableBooks();
}
