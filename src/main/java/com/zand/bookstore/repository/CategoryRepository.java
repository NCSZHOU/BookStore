package com.zand.bookstore.repository;

import com.zand.bookstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select count(c) from Category c where c.name=:name")
    int findIdCountByTitle(@Param("name") String title);

    @Query(value = "select c.id from Category c where c.name=:name")
    Integer findIdByTitle(@Param("name") String title);
}
