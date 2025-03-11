package com.test.musinsa.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {

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

}
