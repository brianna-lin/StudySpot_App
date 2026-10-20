package com.studyspot.ui;

import java.util.List;

import com.studyspot.model.Cafe;
import com.studyspot.model.Rating;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudySpotGUI extends Application {

    // EFFECTS: displays the main gui with a search bar and list of cafes
    @Override
    public void start(Stage primaryStage) {
        // stub
    }

    // EFFECTS: returns a display card of the cafe with its name, location, and a button to view its details
    private VBox createCafeCard(Cafe cafe) {
        return null;
    }

    // MODIFIES: cafeList
    // EFFECTS: updates the displayed list of cafes according to the user's search input
    private void updateCafeList(VBox cafeList, List<Cafe> cafes) {
        // stub
    }

    // EFFECTS: displays the details of a given cafe (its name, location, and ratings)
    private void showCafeDetails(Cafe cafe) {
        // stub
    }

    // MODIFIES: cafe
    // EFFECTS: displays a ratings form that lets the user rate the cafe; if the user submits a valid rating,
    // add that new rating to the cafe's list of ratings
    private void showRatingForm(Cafe cafe) {
        // stub
    }
}
