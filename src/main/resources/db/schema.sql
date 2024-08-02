DROP TABLE IF EXISTS BOOK;
DROP TABLE IF EXISTS CATEGORY;

create table if not exists CATEGORY (
    CATEGORY_ID int not null primary key auto_increment,
    CATEGORY_NAME varchar(100),
    CATEGORY_DESCRIPTION text);

create table if not exists BOOK (
                                    BOOK_ID int not null primary key auto_increment,
                                    CATEGORY_ID int ,
                                    BOOK_TITLE varchar(100),
                                    BOOK_AUTHOR varchar(100),
                                    BOOK_PRICE double,
                                    BOOK_IS_AVAILABLE boolean,
                                    BOOK_STOCK int,
                                    foreign key(CATEGORY_ID) references CATEGORY(CATEGORY_ID)
);
