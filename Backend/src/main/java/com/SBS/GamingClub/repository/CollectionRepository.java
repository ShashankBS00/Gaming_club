package com.SBS.GamingClub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SBS.GamingClub.Entitis.Collections;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collections, Integer> {
    // Basic CRUD operations are inherited from JpaRepository
    
    // Find collections by date
    List<Collections> findByDate(LocalDateTime date);
    
    // Find collections between date range
    List<Collections> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find collections with amount greater than
    List<Collections> findByAmountGreaterThan(float amount);
    
    // Find collections with amount less than
    List<Collections> findByAmountLessThan(float amount);
    
    // Find collections by date ordered by amount
    List<Collections> findByDateOrderByAmountDesc(LocalDateTime date);
}