package com.studyspot.model;

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
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds r to the cafe's list of ratings
    public void addRating(Rating r) {
        // stub
    }

    // EFFECTS: returns the average noise rating of the cafe in the format: "Noise Level: x/5";
    // return N/A if there are no ratings
    public String getAvgNoiseLevel() {
        return null; // stub
    }

    // EFFECTS: returns the average cleanliness rating of the cafe in the format: "Cleanliness: x/5";
    // return N/A if there are no ratings 
    public String getAvgCleanliness() {
        return null; // stub
    }

    // EFFECTS: returns the average lighting rating of the cafe in the format: "Lighting: x/5";
    // return N/A if there are no ratings
    public String getAvgLighting() {
        return null; // stub
    }

    // EFFECTS: returns the average seating rating of the cafe in the format: "Seating: x/5";
    // return N/A if there are no ratings
    public String getAvgSeating() {
        return null; // stub
    }

    // EFFECTS: returns the average drink quality rating of the cafe in the format: "Drink Quality: 
    // x/5"; return N/A if there are no ratings
    public String getAvgDrinkQuality() {
        return null; // stub
    }

    // EFFECTS: returns the average food quality rating of the cafe in the format: "Food Quality:
    // x/5"; return N/A if there are no ratings
    public String getAvgFoodQuality() {
        return null; // stub
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
