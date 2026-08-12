package com.studyspot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cafe.
 */
public class Cafe {
    private String name;
    private String address;
    private List<Rating> ratings;
    
    // makes a cafe with given name, address, and no ratings
    public Cafe(String name, String address) {
        this.name = name;
        this.address = address;
        ratings = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds r to the cafe's list of ratings
    public void addRating(Rating r) {
        ratings.add(r);
    }

    // EFFECTS: returns the average noise rating of the cafe or null if it has no ratings
    public Double getAvgNoiseLevel() {
        if (ratings.isEmpty()) {
            return null;
        }

        double total = 0;
        for (Rating r : ratings) {
            total += r.getNoiseLevel();
        }

        double avg = total / ratings.size();
        return avg;
    }

    // EFFECTS: returns the average cleanliness rating of the cafe or null if it has no ratings
    public Double getAvgCleanliness() {
        if (ratings.isEmpty()) {
            return null;
        }

        double total = 0;
        for (Rating r : ratings) {
            total += r.getCleanliness();
        }
        
        double avg = total / ratings.size();
        return avg;
    }

    // EFFECTS: returns the average lighting rating of the cafe or null if it has no ratings
    public Double getAvgLighting() {
        if (ratings.isEmpty()) {
            return null;
        }

        double total = 0;
        for (Rating r : ratings) {
            total += r.getLighting();
        }
        
        double avg = total / ratings.size();
        return avg;
    }

    // EFFECTS: returns the average seating rating of the cafe or null if it has no ratings
    public Double getAvgSeating() {
        if (ratings.isEmpty()) {
            return null;
        }

        double total = 0;
        for (Rating r : ratings) {
            total += r.getSeating();
        }
        
        double avg = total / ratings.size();
        return avg;
    }

    // EFFECTS: returns the average drink quality rating of the cafe or null if it has no ratings
    public Double getAvgDrinkQuality() {
        if (ratings.isEmpty()) {
            return null;
        }

        double total = 0;
        for (Rating r : ratings) {
            total += r.getDrinkQuality();
        }
        
        double avg = total / ratings.size();
        return avg;
    }

    // EFFECTS: returns the average food quality rating of the cafe or null if it has no ratings
    public Double getAvgFoodQuality() {
        if (ratings.isEmpty()) {
            return null;
        }

        double total = 0;
        for (Rating r : ratings) {
            total += r.getFoodQuality();
        }
        
        double avg = total / ratings.size();
        return avg;
    }

    // EFFECTS: returns the cafe's name
    public String getName() {
        return name;
    }

    // EFFECTS: returns the cafe's address
    public String getAddress() {
        return address;
    }
}
