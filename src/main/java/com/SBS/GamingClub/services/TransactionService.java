package com.SBS.GamingClub.services;

import com.SBS.GamingClub.Entitis.Game;
import com.SBS.GamingClub.Entitis.Member;
import com.SBS.GamingClub.Entitis.Transaction;
import com.SBS.GamingClub.dto.PlayDto; // Correct Import
import com.SBS.GamingClub.repository.GameRepository;
import com.SBS.GamingClub.repository.TransactionRepository;
import com.SBS.GamingClub.repository.memberRepository; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service 
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired 
    private memberRepository memberRepository; 
    
    @Autowired 
    private GameRepository gameRepository; 

    @Transactional
    public Transaction playGame(PlayDto playDto) { // Correct method signature
        
        Optional<Member> memberOpt = memberRepository.findById(playDto.getMemberId());
        if (!memberOpt.isPresent()) {
            throw new RuntimeException("Member not found with ID: " + playDto.getMemberId());
        }
        Member member = memberOpt.get();
        
        Optional<Game> gameOpt = gameRepository.findById(playDto.getGameId());
        if (!gameOpt.isPresent()) {
            throw new RuntimeException("Game not found with ID: " + playDto.getGameId());
        }
        Game game = gameOpt.get();

        float gameAmount = game.getAmount();

        // Check if member has sufficient balance
        if (member.getBalance() < gameAmount) {
            throw new RuntimeException("Insufficient balance for member: " + member.getName());
        }

        // 1. Update member balance and save
        member.setBalance(member.getBalance() - gameAmount);
        memberRepository.save(member);
        
        // 2. Create and save transaction record
        Transaction transaction = new Transaction();
        transaction.setMember(member);
        transaction.setGame(game);
        transaction.setAmount(gameAmount);
        transaction.setDateTime(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
    
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}