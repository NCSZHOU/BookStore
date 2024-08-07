package com.greyson.bookstore.repository;

import com.greyson.bookstore.entity.Book;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select book from Book book where book.isAvailable=true and book.quantity > 0")
    List<Book> findAllAvailableBooks();

    @Nullable
    @Query("select book.quantity from Book book where book.bookId = :bookId")
    String findBookQuantityByBookId(@Param("bookId") String bookId);

    @Query("select book from Book book where book.bookId = :bookId")
    Book findBookByBookId(@Param("bookId") String bookId);

    @Modifying
    @Transactional
    @Query("update Book book set book.quantity= (book.quantity - :quantity) where book.bookId=:bookId")
    int updateBookStock(@Param("bookId") String bookId, @Param("quantity") long quantity);
}
