package com.zand.bookstore.repository;

import com.zand.bookstore.entity.ShoppingCart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    @Query("select sc from ShoppingCart sc where sc.userId = :userId")
    List<ShoppingCart> findAllByUserId(@Param("userId") String userId);

    @Modifying
    @Transactional
    @Query("delete from ShoppingCart sc where sc.userId = :userId")
    void deleteGoodsByUserId(@Param("userId") String userId);
}
