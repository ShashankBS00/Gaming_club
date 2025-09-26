// src/main/java/com/SBS/GamingClub/dto/RechargeDto.java

package com.SBS.GamingClub.dto;

import java.time.LocalDateTime;

public class RechargeDto {
    // CHANGE private Long memberId; TO private int memberId;
    private int memberId; // CORRECTED: Should be int to match Member entity ID
    private float amount;
    private LocalDateTime dateTime;

    // Default constructor
    public RechargeDto() {}

    // Constructor with fields
    public RechargeDto(int memberId, float amount, LocalDateTime dateTime) {
        this.memberId = memberId;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    // Constructor without dateTime (for new recharges)
    public RechargeDto(int memberId, float amount) {
        this.memberId = memberId;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    // Getters and Setters
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    // ... rest of the file
}