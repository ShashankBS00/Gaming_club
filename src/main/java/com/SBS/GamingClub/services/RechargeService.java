package com.SBS.GamingClub.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SBS.GamingClub.Entitis.Member;
import com.SBS.GamingClub.Entitis.Recharge;
import com.SBS.GamingClub.Entitis.Collections;
import com.SBS.GamingClub.repository.RechargeRepository;
import com.SBS.GamingClub.repository.memberRepository;
import com.SBS.GamingClub.repository.CollectionRepository;

@Service
public class RechargeService {

    @Autowired
    private RechargeRepository rechargeRepository;

    @Autowired
    private memberRepository memberRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Transactional
    public Recharge createRecharge(int memberId, float amount) {
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        if (!memberOpt.isPresent()) {
            throw new RuntimeException("Member not found");
        }

        Member member = memberOpt.get();
        member.setBalance(member.getBalance() + amount);
        memberRepository.save(member);

        Recharge recharge = new Recharge();
        recharge.setMember(member);
        recharge.setAmount(amount);
        recharge.setDateTime(LocalDateTime.now());
        recharge = rechargeRepository.save(recharge);

        // Update collections for today
        LocalDateTime now = LocalDateTime.now();
        List<Collections> todaysCollections = collectionRepository.findByDate(now);
        
        if (!todaysCollections.isEmpty()) {
            Collections collections = todaysCollections.get(0);
            collections.setAmount(collections.getAmount() + amount);
            collectionRepository.save(collections);
        } else {
            Collections collections = new Collections();
            collections.setDate(now);
            collections.setAmount(amount);
            collectionRepository.save(collections);
        }

        return recharge;
    }

    public List<Recharge> getMemberRecharges(int memberId) {
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        if (memberOpt.isPresent()) {
            return rechargeRepository.findByMember(memberOpt.get());
        }
        return List.of(); // Return empty list if member not found
    }

    public List<Recharge> getRechargesByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return rechargeRepository.findByDateTimeBetween(startDate, endDate);
    }

    public Optional<Recharge> getRechargeById(int id) {
        return rechargeRepository.findById(id);
    }

    public List<Recharge> getRechargesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return rechargeRepository.findByDateTimeBetween(startDate, endDate);
    }

    public double getTotalRechargeAmount(LocalDateTime startDate, LocalDateTime endDate) {
        return rechargeRepository.findByDateTimeBetween(startDate, endDate)
                .stream()
                .mapToDouble(Recharge::getAmount)
                .sum();
    }
}
