package com.zand.bookstore.config;

import com.zand.bookstore.dao.BookDao;
import com.zand.bookstore.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mappings(@Mapping(target = "categoryId",ignore = true))
    Book convertBookDaoToBook(BookDao bookDao);
}
