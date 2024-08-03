package com.zand.bookstore.repository;

import com.zand.bookstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select count(c) from Category c where c.name=:name")
    int findIdCountByName(@Param("name") String title);

    @Query(value = "select c.id from Category c where c.name=:name")
    Long findIdByName(@Param("name") String name);

    @Query(value = "select c.name from Category c where c.id=:id")
    String findNameByCategoryId(@Param("id") Long id);
}
