-- Test data for category table
INSERT INTO CATEGORY(CATEGORY_NAME, CATEGORY_DESCRIPTION)
values ('Literature', 'Literature'),
       ('Fiction', 'Fiction'),
       ('Magazine', 'Magazine'),
       ('Tourism', 'Tourism');

-- Test data for book table
INSERT INTO BOOK(BOOK_ID, BOOK_TITLE, CATEGORY_ID, BOOK_AUTHOR, BOOK_PRICE, BOOK_IS_AVAILABLE, QUANTITY)
values ('E38-0542-9802', 'One hundred years solitude', 1, 'Gabriel García Márquez', 128.50, true, 20);
