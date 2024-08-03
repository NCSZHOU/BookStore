package com.zand.bookstore.dao;

import lombok.Data;

@Data
public class CheckoutResponse {
    private int responseCode;
    private String message;
    private double account;
}
