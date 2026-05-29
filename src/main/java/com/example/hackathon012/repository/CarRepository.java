package com.example.hackathon012.repository;

import com.example.hackathon012.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(" SELECT c FROM Car c WHERE c.isDeleted=false AND"+
            "( :keyword IS NULL OR" +
            " LOWER(c.model) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            " LOWER(c.brand) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Car> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query(" SELECT c FROM Car c WHERE c.isDeleted=false AND c.id = :id")
    Optional<Car> findActiveById(@Param("id") long id);
}
