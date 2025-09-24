package com.SBS.GamingClub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SBS.GamingClub.Entitis.Member;
import java.util.List

@Repository
public interface memberRepository extends JpaRepository<Member, Integer> {
    // Basic CRUD operations are inherited from JpaRepository
    
    // Optional: Add custom query methods
    // Find member by phone number
    Member findByPhone(String phone);
    
    // Find member by name containing
    List<Member> findByNameContaining(String name);
    
    // Find members with balance greater than
    List<Member> findByBalanceGreaterThan(float balance);
    
    // Find members with balance less than
    List<Member> findByBalanceLessThan(float balance);
}