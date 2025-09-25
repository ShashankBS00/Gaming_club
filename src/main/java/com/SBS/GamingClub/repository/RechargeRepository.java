package com.SBS.GamingClub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SBS.GamingClub.Entitis.Recharge;
import com.SBS.GamingClub.Entitis.Member;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RechargeRepository extends JpaRepository<Recharge, Integer> {
    // Find recharges by member
    List<Recharge> findByMember(Member member);
    
    // Find recharges by date range
    List<Recharge> findByDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find recharges by amount greater than
    List<Recharge> findByAmountGreaterThan(float amount);
    
    // Find recharges by member and date range
    List<Recharge> findByMemberAndDateTimeBetween(
        Member member, 
        LocalDateTime startDate, 
        LocalDateTime endDate
    );
    
    // Find recharges ordered by date desc
    List<Recharge> findAllByOrderByDateTimeDesc();
}