package com.SBS.GamingClub.controllers;

import com.SBS.GamingClub.Entitis.Transaction;
import com.SBS.GamingClub.dto.PlayDto;
import com.SBS.GamingClub.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/play")
    public ResponseEntity<Transaction> playGame(@RequestBody PlayDto playDto) {
        try {
            Transaction transaction = transactionService.playGame(playDto);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            // For exceptions like "Member not found", "Game not found", or "Insufficient balance"
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}