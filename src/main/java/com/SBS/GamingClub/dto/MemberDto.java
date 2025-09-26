package com.SBS.GamingClub.dto;

public class MemberDto {
    private String name;
    private String phone;
    private float fees;
    private float balance;

    // Default constructor
    public MemberDto() {}

    // Constructor with fields
    public MemberDto(String name, String phone, float fees, float balance) {
        this.name = name;
        this.phone = phone;
        this.fees = fees;
        this.balance = balance;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getFees() {
        return fees;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}