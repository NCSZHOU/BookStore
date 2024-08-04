package com.greyson.bookstore.service;

import com.greyson.bookstore.entity.Book;
import com.greyson.bookstore.entity.ShoppingCart;
import com.greyson.bookstore.repository.CategoryRepository;
import com.greyson.bookstore.repository.ShoppingCartRepository;
import com.greyson.bookstore.common.CommonResponse;
import com.greyson.bookstore.common.Constants;
import com.greyson.bookstore.config.BookMapper;
import com.greyson.bookstore.dao.BookDao;
import com.greyson.bookstore.exception.BookStockException;
import com.greyson.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public CommonResponse save(String userId, List<BookDao> bookList) {
        log.info("Start to save shopping cart ");
        log.debug("Saving for user:{}, total {} books", userId, bookList.size());
        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        bookList.stream().forEach(book -> {
            ShoppingCart cart = new ShoppingCart();
            cart.setUserId(userId);
            cart.setBookId(book.getBookId());
            cart.setQuantity(book.getQuantity());
            shoppingCartList.add(cart);
        });
        log.debug("Saving for user:{} successfully", userId);
        shoppingCartRepository.saveAll(shoppingCartList);
        return CommonResponse.response(HttpStatus.OK.value(), HttpStatus.OK.toString(), bookList.size());
    }

    public CommonResponse checkout(String userId, List<BookDao> bookDaoList) throws Exception {
        log.error("Start to checkout shopping cart .");
        double result = 0;
        for (BookDao bookDao : bookDaoList) {
            if (!hasEnoughStock(bookDao.getQuantity(), bookDao.getBookId())) {
                String message = Constants.INDICATOR_BOOK_START + bookDao.getTitle() + Constants.INDICATOR_BOOK_END + Constants.NOT_ENOUGH_STOCK;
                log.error(message);
                throw new BookStockException(message);
            }
            result += bookDao.getPrice() * bookDao.getQuantity();
        }
        // checkout success, delete goods in the shopping cart table
        shoppingCartRepository.deleteGoodsByUserId(userId);

        // Manage the stock of book
        bookDaoList.stream().forEach(bookDao -> {
            bookRepository.updateBookStock(bookDao.getBookId(), bookDao.getQuantity());
        });
        log.info("Checkout shopping cart for user:{} successfully", userId);

        return CommonResponse.response(
                HttpStatus.OK.value(),
                HttpStatus.OK.toString(),
                Math.round(result * 100.0) / 100.0);
    }

    private boolean hasEnoughStock(long quantity, String bookId) {
        String stock = bookRepository.findBookQuantityByBookId(bookId);
        if (stock == null || stock.isEmpty() || Integer.parseInt(stock) == 0) {
            return false;
        }
        return Long.valueOf(stock) >= quantity;
    }

    public List<BookDao> retrieveCartByUserId(String userId) {
        log.info("Start to retrieve shopping cart ....");
        List<BookDao> bookDaoList = new ArrayList<>();
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAllByUserId(userId);
        if (shoppingCartList == null || shoppingCartList.isEmpty()) return new ArrayList<>();
        shoppingCartList.stream().forEach(shoppingCart -> {
            Book book = bookRepository.findBookByBookId(shoppingCart.getBookId());
            BookDao bookDao = BookMapper.INSTANCE.convertBookToBookDao(book);
            bookDao.setCategory(categoryRepository.findNameByCategoryId(book.getCategoryId()));
            bookDao.setQuantity(shoppingCart.getQuantity());
            bookDaoList.add(bookDao);
        });
        log.info("Retrieve shopping cart successfully for user:{}", userId);
        return bookDaoList;
    }
}
