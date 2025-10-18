package com.SBS.GamingClub.services;

import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.SBS.GamingClub.Entitis.Member;
import com.SBS.GamingClub.Entitis.Recharge;
import com.SBS.GamingClub.Entitis.Collections;
import com.SBS.GamingClub.repository.memberRepository;
import com.SBS.GamingClub.repository.RechargeRepository;
import com.SBS.GamingClub.repository.CollectionRepository;
import com.SBS.GamingClub.dto.MemberDto;

@Service
public class MemberService {
    
    @Autowired
    private memberRepository memberRepository;
    
    @Autowired
    private RechargeRepository rechargeRepository;
    
    @Autowired
    private CollectionRepository collectionRepository;
    
    @Transactional
    public Member saveMember(MemberDto memberDto) {
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setPhone(memberDto.getPhone());
        member.setBalance(memberDto.getFees());
        
        // Save member first
        member = memberRepository.save(member);
        
        // Create and save recharge record
        Recharge recharge = new Recharge();
        recharge.setMember(member);
        recharge.setAmount(memberDto.getFees());
        recharge.setDateTime(LocalDateTime.now());
        rechargeRepository.save(recharge);
        // Get today's collections
        LocalDateTime now = LocalDateTime.now();
        List<Collections> todaysCollections = collectionRepository.findByDate(now);
        
        if (!todaysCollections.isEmpty()) {
            Collections collections = todaysCollections.get(0);
            collections.setAmount(collections.getAmount() + memberDto.getFees());
            collectionRepository.save(collections);
        } else {
            Collections collections = new Collections();
            collections.setDate(now);
            collections.setAmount(memberDto.getFees());
            collectionRepository.save(collections);
        }
        
        return member;
    }
    
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    public Optional<Member> getMemberById(int id) {
        return memberRepository.findById(id);
    }
    
    @Transactional
    public Member updateMember(int id, MemberDto memberDto) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setName(memberDto.getName());
            member.setPhone(memberDto.getPhone());
            // Note: We don't update balance directly through DTO, use recharge instead
            return memberRepository.save(member);
        }
        return null;
    }
    
    @Transactional
    public boolean deleteMember(int id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Transactional
    public Member rechargeMemberBalance(int id, float amount) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setBalance(member.getBalance() + amount);
            member = memberRepository.save(member);
            
            // Create recharge record
            Recharge recharge = new Recharge();
            recharge.setMember(member);
            recharge.setAmount(amount);
            recharge.setDateTime(LocalDateTime.now());
            rechargeRepository.save(recharge);
            
            // Update today's collections
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
            
            return member;
        }
        return null;
    }
}
