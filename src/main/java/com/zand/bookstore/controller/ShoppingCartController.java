package com.zand.bookstore.controller;

import com.zand.bookstore.common.CommonResponse;
import com.zand.bookstore.dao.BookDao;
import com.zand.bookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/shopping-cart")
@Tag(name = "ShoppingCartControllerApi", description = "Shopping cart controller api",
        externalDocs = @ExternalDocumentation(description = "shopping cart controller, support retrieve/save/checkout shopping cart books", url = ""))
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Retrieve shopping cart's goods",
            description = "Retrieve shopping cart's goods",
            parameters = {@Parameter(name = "userId", required = true)},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Failed",
                            content = @Content(mediaType = "application/json")
                    )}
    )
    @GetMapping("/{userId}/retrieve")
    public List<BookDao> retrieveCart(@PathVariable("userId") String userId) {
        log.info("Start to save shopping cart books .");
        return shoppingCartService.retrieveCartByUserId(userId);
    }

    @Operation(summary = "Save shopping cart's goods",
            description = "Save shopping cart's goods",
            parameters = {@Parameter(name = "userId", required = true)},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Failed",
                            content = @Content(mediaType = "application/json")
                    )}
    )
    @PostMapping("/{userId}/save")
    public CommonResponse save(@PathVariable("userId") String userId, @RequestBody List<BookDao> bookList) {
        log.info("Start to save shopping cart books .");
        return shoppingCartService.save(userId, bookList);
    }

    @Operation(summary = "Checkout for shopping cart",
            description = "Checkout for shopping cart",
            parameters = {@Parameter(name = "userId", required = true)},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Failed",
                            content = @Content(mediaType = "application/json")
                    )}
    )
    @PostMapping("/{userId}/checkout")
    public CommonResponse checkout(@PathVariable("userId") String userId, @RequestBody List<BookDao> bookList) {
        log.info("Start to checkout for shopping cart");
        return shoppingCartService.checkout(userId, bookList);
    }

}
