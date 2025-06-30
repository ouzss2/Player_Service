package org.formation.playerservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Player {

    @Id
    private String name;

    private String email;
    private int score = 0;

    // Constructeurs
    public Player() {}

    public Player(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters & setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
