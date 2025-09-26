package com.SBS.GamingClub.dto;

public class PlayDto {
    private int memberId;
    private int gameId;

    // Default constructor
    public PlayDto() {}

    // Constructor with fields
    public PlayDto(int memberId, int gameId) {
        this.memberId = memberId;
        this.gameId = gameId;
    }

    // Getters and Setters
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}