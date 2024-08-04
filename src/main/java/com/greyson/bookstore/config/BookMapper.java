package com.greyson.bookstore.config;

import com.greyson.bookstore.entity.Book;
import com.greyson.bookstore.dao.BookDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    /**
     * Generate instance
     */
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    /**
     * Convert BookDao to Book ,ignore filed : category
     * @param bookDao
     * @return
     */
    @Mappings(@Mapping(target = "categoryId", ignore = true))
    Book convertBookDaoToBook(BookDao bookDao);

    /**
     * Convert Book to BookDao ,ignore filed : category
     * @param book
     * @return
     */
    @Mappings(@Mapping(target = "category", ignore = true))
    BookDao convertBookToBookDao(Book book);
}
