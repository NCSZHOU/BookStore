-- Test data for category table
INSERT INTO CATEGORY(CATEGORY_NAME, CATEGORY_DESCRIPTION)
values ('Literature', 'Literature'),
       ('Fiction', 'Fiction'),
       ('Magazine', 'Magazine'),
       ('Tourism', 'Tourism');

-- Test data for book table
INSERT INTO BOOK(BOOK_ID, BOOK_TITLE, CATEGORY_ID, BOOK_AUTHOR, BOOK_PRICE, BOOK_IS_AVAILABLE, QUANTITY)
values ('E38-0542-9808', 'One hundred years solitude', 1, 'Gabriel García Márquez', 128.50, true, 20),
       ('E38-0542-9802', 'War and Peace', 1, 'Leo Tolstoy', 38.90, true, 210),
       ('E38-0542-9803', 'The Red and the Black', 1, 'Stendhal', 58.80, true, 80),
       ('E38-0542-9804', 'Wuthering Heights', 1, 'EmilyBronte', 63.35, true, 320),
       ('E38-0542-9805', 'The Hunchback of Notre-Dame', 1, 'Victor Hugo', 12.50, true, 120);

-- Test data for shopping cart table
INSERT INTO SHOPPING_CART(USER_ID, BOOK_ID, QUANTITY)
values ('404249022', 'E38-0542-9802', 3),
       ('404249022', 'E38-0542-9803', 4),
       ('404249022', 'E38-0542-9804', 1);
