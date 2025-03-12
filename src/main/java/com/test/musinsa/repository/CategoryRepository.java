package com.test.musinsa.repository;

import com.test.musinsa.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
