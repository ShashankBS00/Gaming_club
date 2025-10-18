package com.SBS.GamingClub.dto;

import java.time.LocalDateTime;

public class RechargeDto {
    private Long memberId;
    private float amount;
    private LocalDateTime dateTime;

    // Default constructor
    public RechargeDto() {}

    // Constructor with fields
    public RechargeDto(Long memberId, float amount, LocalDateTime dateTime) {
        this.memberId = memberId;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    // Constructor without dateTime (for new recharges)
    public RechargeDto(Long memberId, float amount) {
        this.memberId = memberId;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
