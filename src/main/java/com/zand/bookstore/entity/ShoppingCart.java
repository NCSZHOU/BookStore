package com.zand.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(ShoppingCardIds.class)
@Table(name = "SHOPPING_CART")
public class ShoppingCart {
    /**
     * UserId , the shopping cart's user
     */
    @Id
    @Column(name = "USER_ID")
    private String userId;

    /**
     * The unique id of book
     */
    @Id
    @Column(name = "BOOK_ID")
    private String bookId;

    /**
     * The quantity of book
     */
    @Column(name = "QUANTITY")
    private int quantity;
}
