package com.zand.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CATEGORY")
public class Category {
    /**
     * Unique key of category
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CATEGORY_ID", nullable = false)
    private Long id;

    /**
     * Name of category, like: Literature
     */
    @Column(name = "CATEGORY_NAME")
    private String name;

    /**
     * Description of category
     */
    @Column(name = "CATEGORY_DESCRIPTION")
    private String description;
}
