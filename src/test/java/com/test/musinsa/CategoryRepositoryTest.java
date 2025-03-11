package com.test.musinsa;

import com.test.musinsa.repository.CategoryRepository;
import com.test.musinsa.repository.entity.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("전체 카테고리 조회")
    void testFindAllCategory() {

        // Given : 테스트 데이터 저장
        Category category1 = new Category();
        category1.setName("Test Category1");
        Category category2 = new Category();
        category1.setName("Test Category2");
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        // When : findAll()을 호출하여 모든 카테고리를 조회
        List<Category> categories = categoryRepository.findAll();

        // Then : 조회된 데이터 확인
        assertThat(categories).hasSize(2);

        categoryRepository.findAll();
    }
}
