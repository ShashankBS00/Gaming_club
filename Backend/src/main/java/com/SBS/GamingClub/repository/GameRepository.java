package com.SBS.GamingClub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SBS.GamingClub.Entitis.Game;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    // Find game by name
    Game findByName(String name);
    
    // Find games by status
    List<Game> findByStatus(String status);
    
    // Find games with amount less than or equal to
    List<Game> findByAmountLessThanEqual(float amount);
    
    // Find games by name containing (case-insensitive search)
    List<Game> findByNameContainingIgnoreCase(String name);
    
    // Find games by description containing
    List<Game> findByDescriptionContaining(String description);
}