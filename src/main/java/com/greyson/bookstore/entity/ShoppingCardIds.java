package com.greyson.bookstore.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShoppingCardIds implements Serializable {
    /**
     * User id, shopping-cart's owner
     */
    private String userId;

    /**
     * Book id, reference to book table
     */
    private String bookId;
}
