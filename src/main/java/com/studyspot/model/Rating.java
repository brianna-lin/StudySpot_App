package com.studyspot.model;

/**
 * Represents a rating of a cafe.
 */
public class Rating {
    private int noiseLevel;
    private int cleanliness;
    private int lighting;
    private int seating;
    private int drinkQuality;
    private int foodQuality;

    // constructs a Rating with the given noiseLevel, cleanliness, lighting, seating, drinkQuality,
    // and foodQuality
    public Rating(int noiseLevel, int cleanliness, int lighting, int seating, 
        int drinkQuality, int foodQuality) {
        this.noiseLevel = noiseLevel;
        this.cleanliness = cleanliness;
        this.lighting = lighting;
        this.seating = seating;
        this.drinkQuality = drinkQuality;
        this.foodQuality = foodQuality;
    }

    // EFFECTS: returns noiseLevel
    public int getNoiseLevel() {
        return -1; // stub
    }

    // EFFECTS: returns cleanliness
    public int getCleanliness() {
        return -1; // stub
    }

    // EFFECTS: returns lighting
    public int getLighting() {
        return -1; // stub
    }

    // EFFECTS: returns seating
    public int getSeating() {
        return -1; // stub
    }

    // EFFECTS: returns drinkQuality
    public int getDrinkQuality() {
        return -1; // stub
    }

    // EFFECTS: returns foodQuality
    public int getFoodQuality() {
        return -1; // stub
    }
}
