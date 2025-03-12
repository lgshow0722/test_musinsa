package com.test.musinsa.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {

    // 카테고리 별 최저가 상품 조회
    @Query(value = """
        SELECT m.* FROM merchandise m
        WHERE m.id = (
            SELECT subM.id
            FROM merchandise subM
            WHERE subM.category_id = m.category_id
            AND subM.price = (
                SELECT MIN(innerM.price)
                FROM merchandise innerM
                WHERE innerM.category_id = m.category_id
            )
            ORDER BY subM.id DESC
            LIMIT 1
        )
    """, nativeQuery = true)
    List<Merchandise> findLowestPricePerCategory();

    // 카테고리별 브랜드 최저가 상품 조회
    @Query("SELECT m.category.name, m.brand.name, MIN(m.price) as price " +
        "FROM Merchandise m " +
        "GROUP BY m.category.id, m.brand.id")
    List<Object[]> findLowestPricePerCategoryByBrand();

    Merchandise findTopByCategoryOrderByIdDesc(Category category);

    Merchandise findTopByCategory_NameOrderByIdDesc(String categoryName);

    Merchandise findTopByCategory_NameOrderByPriceDesc(String categoryName);

    Merchandise findTopByCategory_NameOrderByPriceAsc(String categoryName);
}
