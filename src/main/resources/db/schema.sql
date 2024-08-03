DROP TABLE IF EXISTS BOOK;
DROP TABLE IF EXISTS CATEGORY;

create table if not exists CATEGORY
(
    CATEGORY_ID          int not null primary key auto_increment,
    CATEGORY_NAME        varchar(100),
    CATEGORY_DESCRIPTION text
);

create table if not exists BOOK
(
    BOOK_ID           varchar(100) not null primary key,
    CATEGORY_ID       int,
    BOOK_TITLE        varchar(100),
    BOOK_AUTHOR       varchar(100),
    BOOK_PRICE        double,
    BOOK_IS_AVAILABLE boolean,
    QUANTITY          int,
    foreign key (CATEGORY_ID) references CATEGORY (CATEGORY_ID)
);

create table if not exists SHOPPING_CART
(
    USER_ID  varchar(100) not null,
    BOOK_ID  varchar(100) not null,
    QUANTITY int,
    primary key (USER_ID, BOOK_ID),
    foreign key (BOOK_ID) references BOOK (BOOK_ID)
);
