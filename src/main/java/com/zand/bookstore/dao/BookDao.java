package com.zand.bookstore.dao;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BookDao {
    @ApiModelProperty(value = "bookId", required = true, example = "EM-352-35B-001")
    @NotEmpty(message = "Title cannot be empty!")
    private String bookId;

    @ApiModelProperty(value = "title", required = true, example = "title")
    @NotEmpty(message = "Title cannot be empty!")
    private String title;

    @ApiModelProperty(value = "author", required = true, example = "author")
    @NotEmpty(message = "Author cannot be empty!")
    private String author;

    @ApiModelProperty(value = "category", required = true, example = "category")
    @NotEmpty(message = "Category cannot be empty!")
    private String category;

    @ApiModelProperty(value = "price", required = true, example = "price")
    @NotEmpty(message = "Price cannot be empty!")
    @Pattern(regexp = "/(^[1-9]\\d*(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)/",message = "Please enter correct price, example: 0.22")
    private double price;

    @ApiModelProperty(value = "isAvailable", example = "true")
    private boolean isAvailable = true;

    @ApiModelProperty(value = "quantity", required = true, example = "book quantity")
    @NotEmpty(message = "Quantity cannot be empty!")
    @Min(value = 1, message = "Quantity should bigger than 0")
    private int quantity;
}
