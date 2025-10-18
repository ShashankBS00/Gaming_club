package com.SBS.GamingClub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SBS.GamingClub.Entitis.Transaction;
import com.SBS.GamingClub.Entitis.Member;
import com.SBS.GamingClub.Entitis.Game;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // Basic CRUD operations are inherited from JpaRepository
    
    // Find transactions by member
    List<Transaction> findByMember(Member member);
    
    // Find transactions by game
    List<Transaction> findByGame(Game game);
    
    // Find transactions by date range
    List<Transaction> findByDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find transactions by amount greater than
    List<Transaction> findByAmountGreaterThan(float amount);
    
    // Find transactions by member and date range
    List<Transaction> findByMemberAndDateTimeBetween(
        Member member, 
        LocalDateTime startDate, 
        LocalDateTime endDate
    );
    
    // Find transactions by game and date range
    List<Transaction> findByGameAndDateTimeBetween(
        Game game, 
        LocalDateTime startDate, 
        LocalDateTime endDate
    );
}