package com.SBS.GamingClub.Entitis;

public class Game {
private int id;
private String name;
private String description;
private float amount;

public Game() {}
public Game(String name, String description, float amount, String status) {
	super();
	this.name = name;
	this.description = description;
	this.amount = amount;
	this.status = status;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}  
public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
	this.amount = amount;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
private String status;

}

