package com.zand.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "BOOK")
public class Book {
    /**
     * id is the primary key and unique indicator for table Book
     */
    @Id
    @Column(name = "BOOK_ID", nullable = false)
    private String bookId;

    /**
     * Book name
     */
    @Column(name = "BOOK_TITLE")
    private String title;

    /**
     * The author of book
     */
    @Column(name = "BOOK_AUTHOR")
    private String author;

    /**
     * Book's category, Example: Literature
     */
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    /**
     * Price of book, two decimal places. Example: 12.55
     */
    @Column(name = "BOOK_PRICE")
    private double price;

    /**
     * Indicator the book is available
     */
    @Column(name = "BOOK_is_available")
    private boolean isAvailable = true;

    /**
     * The quantity of book
     */
    @Column(name = "QUANTITY")
    private int quantity;
}
