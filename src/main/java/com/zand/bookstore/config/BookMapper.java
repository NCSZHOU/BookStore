package com.zand.bookstore.config;

import com.zand.bookstore.dao.BookDao;
import com.zand.bookstore.entity.Book;
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

    /**,ignore filed : category
     * Convert BookDao to Book ,ignore filed : category
     * @param bookDao
     * @return
     */
    @Mappings(@Mapping(target = "categoryId", ignore = true))
    Book convertBookDaoToBook(BookDao bookDao);

    /**,ignore filed : category
     * Convert Book to BookDao ,ignore filed : category
     * @param book
     * @return
     */
    @Mappings(@Mapping(target = "category", ignore = true))
    BookDao convertBookToBookDao(Book book);
}
