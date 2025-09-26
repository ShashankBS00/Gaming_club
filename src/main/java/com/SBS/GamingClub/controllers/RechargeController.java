package com.SBS.GamingClub.controllers;

import com.SBS.GamingClub.Entitis.Recharge;
import com.SBS.GamingClub.dto.RechargeDto;
import com.SBS.GamingClub.services.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recharges")
@CrossOrigin(origins = "*")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    // POST /api/recharges
    // Creates a new recharge transaction and updates the member's balance.
    @PostMapping
    public ResponseEntity<Recharge> createRecharge(@RequestBody RechargeDto rechargeDto) {
        // Note: The RechargeDto uses Long for memberId, but the Member entity and service
        // use int. We cast here to align with the service method signature.
        if (rechargeDto.getMemberId() == null) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            Recharge recharge = rechargeService.createRecharge(
                rechargeDto.getMemberId().intValue(), 
                rechargeDto.getAmount()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(recharge);
        } catch (RuntimeException e) {
            // Catches "Member not found" exceptions
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // GET /api/recharges/{id}
    // Retrieves a recharge record by its ID.
    @GetMapping("/{id}")
    public ResponseEntity<Recharge> getRechargeById(@PathVariable int id) {
        Optional<Recharge> recharge = rechargeService.getRechargeById(id);
        return recharge.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/recharges/member/{memberId}
    // Retrieves all recharge records for a specific member.
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Recharge>> getMemberRecharges(@PathVariable int memberId) {
        List<Recharge> recharges = rechargeService.getMemberRecharges(memberId);
        if (recharges.isEmpty() && !recharges.isEmpty()) { // Check if member exists logic (optional based on service)
             // The service returns an empty list if the member isn't found.
             // For simplicity, we just return an empty list with 200 OK.
        }
        return ResponseEntity.ok(recharges);
    }
    
    // Additional Query Endpoint Example (matching service method)
    // GET /api/recharges/total?startDate=...&endDate=...
    // Calculates the total recharge amount between two dates.
    /*
    @GetMapping("/total")
    public ResponseEntity<Double> getTotalRechargeAmount(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        double total = rechargeService.getTotalRechargeAmount(startDate, endDate);
        return ResponseEntity.ok(total);
    }
    */
}