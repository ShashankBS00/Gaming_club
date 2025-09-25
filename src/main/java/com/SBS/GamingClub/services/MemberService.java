package com.SBS.GamingClub.services;

import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SBS.GamingClub.Entitis.Member;
import com.SBS.GamingClub.Entitis.Recharge;
import com.SBS.GamingClub.repository.memberRepository;
import com.SBS.GamingClub.repository.RechargeRepository;
import com.SBS.GamingClub.dto.MemberDto;

@Service
public class MemberService {
    
    @Autowired
    private memberRepository memberRepository;
    
    @Autowired
    private RechargeRepository rechargeRepository;
    
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
        
        return member;
    }

    public boolean deleteMember(int id) {
        Optional<Member> existingMember = memberRepository.findById(id);
        if(existingMember.isPresent()) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Member> getMember(int id) {
        return memberRepository.findById(id);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member updateMember(int id, Member member) {
        Optional<Member> existingMember = memberRepository.findById(id);
        if(existingMember.isPresent()) {
            Member oldMember = existingMember.get();
            oldMember.setName(member.getName());
            oldMember.setPhone(member.getPhone());
            oldMember.setBalance(member.getBalance());
            return memberRepository.save(oldMember);
        }
        return null;
    }

    public Member rechargeBalance(int id, float amount) {
        Optional<Member> existingMember = memberRepository.findById(id);
        if(existingMember.isPresent()) {
            Member member = existingMember.get();
            member.setBalance(member.getBalance() + amount);
            
            // Create recharge record
            Recharge recharge = new Recharge();
            recharge.setMember(member);
            recharge.setAmount(amount);
            recharge.setDateTime(LocalDateTime.now());
            rechargeRepository.save(recharge);
            
            return memberRepository.save(member);
        }
        return null;
    }
}