package com.SBS.GamingClub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SBS.GamingClub.Entitis.Member;

@Repository
public interface memberRepository extends JpaRepository<Member, Integer> {
    // Add custom query methods here if needed
}