package com.gachon.price.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PriceRepository extends JpaRepository<Price, Long> {
    Price findTopByOriginalNameOrderByIdDesc(String originalName);
}
