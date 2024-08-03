package com.zand.bookstore.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShoppingCardIds implements Serializable {
    private String userId;
    private String bookId;
}
