package com.zand.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "BOOK")
public class Book {
    /**
     * Id is the primary key and unique indicator for table Book
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID", nullable = false)
    private Long id;

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
    private int categoryId;

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
     * The stock of book
     */
    @Column(name = "BOOK_STOCK")
    private int stock;
}
