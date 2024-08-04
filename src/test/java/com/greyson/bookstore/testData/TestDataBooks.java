package com.greyson.bookstore.testData;

import com.greyson.bookstore.entity.Book;
import com.greyson.bookstore.entity.ShoppingCart;
import com.greyson.bookstore.common.CommonResponse;
import com.greyson.bookstore.common.Constants;
import com.greyson.bookstore.dao.BookDao;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class TestDataBooks {
    static String[] bookIds = {"E38-0542-9802", "E38-0542-9803", "E38-0542-9804", "E38-0542-9805"};
    static String[] bookTitle = {"War and Peace", "The Red and the Black", "Wuthering Heights", "The Hunchback of Notre-Dame"};
    static String[] bookAuthor = {"Leo Tolstoy", "Stendhal", "EmilyBronte", "Victor Hugo"};
    public static double[] bookPrice = {38.90, 58.80, 65.35, 122.00};

    private static List<Book> bookList = new ArrayList<>();
    private static List<BookDao> bookDaoList = new ArrayList<>();

    public static List<Book> generateBookList() {
        if (bookList.isEmpty()) {
            for (int i = 0; i < bookIds.length; i++) {
                Book book = new Book();
                book.setBookId(bookIds[i]);
                book.setAuthor(bookAuthor[i]);
                book.setAvailable(true);
                book.setPrice(bookPrice[i]);
                book.setTitle(bookTitle[i]);
                book.setQuantity((int) (Math.random() * 100));
                bookList.add(book);
            }
        }
        return bookList;
    }

    public static List<BookDao> generateBookDaoList() {
        if (bookDaoList.isEmpty()) {
            for (int i = 0; i < bookIds.length; i++) {
                BookDao book = new BookDao();
                book.setBookId(bookIds[i]);
                book.setAuthor(bookAuthor[i]);
                book.setAvailable(true);
                book.setCategory("Literature");
                book.setPrice(bookPrice[i]);
                book.setTitle(bookTitle[i]);
                book.setQuantity(i + 1);
                bookDaoList.add(book);
            }
        }
        return bookDaoList;
    }

    public static List<BookDao> generateInValidatedBookDaoList() {
        List<BookDao> bookDaoList = new ArrayList<>();
        for (int i = 0; i < bookIds.length; i++) {
            BookDao book = new BookDao();
            book.setBookId(bookIds[i]);
            book.setAuthor(bookAuthor[i]);
            book.setAvailable(true);
            book.setPrice(bookPrice[i]);
            book.setTitle(bookTitle[i]);
            book.setCategory("NoCategory");
            book.setQuantity(20);
            bookDaoList.add(book);
        }

        return bookDaoList;
    }

    public static CommonResponse generateSuccessResp(Object data) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(data);
        commonResponse.setCode(HttpStatus.OK.value());
        commonResponse.setMsg(HttpStatus.OK.toString());
        return commonResponse;
    }

    public static CommonResponse generateFailResp() {
        String message = String.format(Constants.SAVE_RESULT_MESSAGE, 0, 4);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(null);
        commonResponse.setCode(HttpStatus.OK.value());
        commonResponse.setMsg(message);
        return commonResponse;
    }

    public static double getTestBooksTotalAmount() {
        double totalAmount = 0.0;
        int index = 1;
        for (double price : TestDataBooks.bookPrice) {
            totalAmount += price * index;
            index++;
        }
        return totalAmount;
    }

    public static List<ShoppingCart> getTestShoppingCartData() {
        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setBookId("E38-0542-9802");
        shoppingCart.setUserId(bookIds[0]);
        shoppingCart.setUserId("404249022");
        shoppingCart.setQuantity(3);
        shoppingCartList.add(shoppingCart);
        return shoppingCartList;
    }
}
