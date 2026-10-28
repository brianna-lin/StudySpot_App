package com.studyspot.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.studyspot.model.Cafe;
import com.studyspot.model.Rating;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StudySpotGUI extends Application {
    String bgColorVal = "#f7e3dd";
    String dBrownColorVal_1 = "#49270e";
    String dBrownColorVal_2 = "#452218";
    String buttonColorVal = "#c9846f";
    String subTextColorVal = "#806259";

    // EFFECTS: displays the main gui with a search bar and list of cafes
    @Override
    public void start(Stage primaryStage) {
        // main container that stacks all components vertically
        VBox root = new VBox(25);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: " + bgColorVal + ";");
        root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("StudySpot");
        title.setFont(Font.font("Georgia", 35));
        title.setTextFill(Color.web(dBrownColorVal_1));

        // coffee cup icon next to title
        Image coffeeIcon = new Image(getClass().getResource("/coffeecup.png").toExternalForm());
        ImageView coffeeIconView = initCoffeeIcon(coffeeIcon);

        // change the icon of the window to the coffee cup image
        primaryStage.getIcons().add(coffeeIcon);

        // header displaying application name and coffee cup image at the top of the window
        HBox header = new HBox(12, coffeeIconView, title);
        header.setAlignment(Pos.CENTER);

        // sub-header
        Label subHeader = new Label("Find your perfect study spot!");
        subHeader.setFont(Font.font("Arial", 16));
        subHeader.setTextFill(Color.web("#8f694d"));

        // search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search for cafes by name or location");
        searchField.setMaxSize(500, 45);

        Label subHeader2 = new Label("Explore Cafes");
        subHeader2.setFont(Font.font("Georgia", 25));
        subHeader2.setTextFill(Color.web(dBrownColorVal_2));

        List<Cafe> cafes = cafesInit();

        // container to list all cafes
        VBox cafeList = new VBox();
        cafeList.setMaxWidth(600);

        updateCafeList(cafeList, cafes, "");

        // searches according to user input
        searchField.textProperty().addListener((observable, oldValue, newValue) -> 
            {updateCafeList(cafeList, cafes, newValue);});

        ScrollPane cafeScroll = initCafeScroll(cafeList);

        root.getChildren().addAll(header, subHeader, searchField, subHeader2, cafeScroll);

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setTitle("StudySpot");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // EFFECTS: initializes the image view for the coffee cup icon
    private ImageView initCoffeeIcon(Image coffeeIcon) {
        ImageView coffeeIconView = new ImageView(coffeeIcon);
        coffeeIconView.setFitHeight(45);
        coffeeIconView.setFitWidth(45);
        coffeeIconView.setPreserveRatio(true);
        return coffeeIconView;
    }

    // EFFECTS: initializes cafe scroll pane
    private ScrollPane initCafeScroll(VBox cafeList) {
        ScrollPane cafeScroll = new ScrollPane(cafeList);
        cafeScroll.setFitToWidth(true);
        cafeScroll.setMaxWidth(635);
        cafeScroll.setPrefViewportHeight(300);
        cafeScroll.setStyle("-fx-background: "+bgColorVal+";" + "-fx-background-color: "+bgColorVal+";");
        return cafeScroll;
    }

    // MODIFIES: cafeList
    // EFFECTS: updates the displayed list of cafes according to the user's search input
    private void updateCafeList(VBox cafeList, List<Cafe> cafes, String searchInput) {
        cafeList.getChildren().clear();

        String input = searchInput.toLowerCase(Locale.ROOT).trim();

        for (Cafe c : cafes) {
            boolean matchesName = c.getName().toLowerCase(Locale.ROOT).contains(input);
            boolean matchesAddress = c.getAddress().toLowerCase(Locale.ROOT).contains(input);
            boolean matchesCity = c.getCity().toLowerCase(Locale.ROOT).contains(input);

            if (matchesName || matchesAddress || matchesCity) {
                cafeList.getChildren().add(createCafeCard(c, 
                    () -> updateCafeList(cafeList, cafes, searchInput)));
            }
        }

        if (cafeList.getChildren().isEmpty()) {
            Label noResults = new Label("No cafés match your search.");
            noResults.setWrapText(true);
            cafeList.getChildren().add(noResults);
        }
    }

    // EFFECTS: returns a display card of the cafe with its name, location, and a button to view its details
    private VBox createCafeCard(Cafe cafe, Runnable refreshCafeList) {
        Label nameLabel = new Label(cafe.getName());
        nameLabel.setFont(Font.font("Georgia", 20));
        nameLabel.setTextFill(Color.web(dBrownColorVal_2));

        Label cityLabel = new Label(cafe.getCity());
        cityLabel.setTextFill(Color.web("#936152"));

        Label addressLabel = new Label(cafe.getAddress());
        addressLabel.setStyle("-fx-font-style: italic;");
        addressLabel.setTextFill(Color.web("#936152"));

        Button detailsButton = new Button("View Details");
        detailsButton.setStyle(
            "-fx-background-color: " + buttonColorVal + ";" 
            + "-fx-text-fill: white;" 
            + "-fx-background-radius: 7;");
        detailsButton.setOnAction(event -> showCafeDetails(cafe, refreshCafeList));

        VBox cafeCard = new VBox(10, nameLabel, addressLabel, cityLabel, detailsButton);
        cafeCard.setPadding(new Insets(15));
        cafeCard.setMaxWidth(600);
        cafeCard.setStyle(
            "-fx-background-color: white;" 
            + "-fx-background-radius: 12;"
            + "-fx-border-color: #d6a799;"
            + "-fx-border-radius: 12;");

        return cafeCard;
    }

    // EFFECTS: displays the details of a given cafe (its name, location, and ratings)
    private void showCafeDetails(Cafe cafe, Runnable refreshCafeList) {
        Stage detailsStage = new Stage();
        detailsStage.setTitle("StudySpot: " + cafe.getName());

        // change window icon to coffee cup image
        Image coffeeIcon = new Image(getClass().getResource("/coffeecup.png").toExternalForm());
        detailsStage.getIcons().add(coffeeIcon);

        VBox detailsRoot = new VBox(20);
        detailsRoot.setPadding(new Insets(20));
        detailsRoot.setAlignment(Pos.TOP_LEFT);
        detailsRoot.setStyle("-fx-background-color: " + bgColorVal + ";");

        Label nameLabel = new Label(cafe.getName());
        nameLabel.setFont(Font.font("Georgia", 27));
        nameLabel.setTextFill(Color.web(dBrownColorVal_1));
        nameLabel.setWrapText(true);

        Label addressLabel = new Label(cafe.getAddress());
        addressLabel.setFont(Font.font(15));
        addressLabel.setStyle("-fx-font-style: italic;");
        addressLabel.setTextFill(Color.web(subTextColorVal));
        addressLabel.setWrapText(true);

        Label cityLabel = new Label(cafe.getCity());
        cityLabel.setFont(Font.font(15));
        cityLabel.setTextFill(Color.web(subTextColorVal));
        cityLabel.setWrapText(true);

        Label ratingsHeading = new Label("Average Ratings:");
        ratingsHeading.setFont(Font.font("Georgia", 18));

        Label noiseLevelLabel = new Label("Noise Level: " + formatScore(cafe.getAvgNoiseLevel()));
        noiseLevelLabel.setFont(Font.font(15));
        noiseLevelLabel.setTextFill(Color.web(subTextColorVal));

        Label cleanlinessLabel = new Label("Cleanliness: " + formatScore(cafe.getAvgCleanliness()));
        cleanlinessLabel.setFont(Font.font(15));
        cleanlinessLabel.setTextFill(Color.web(subTextColorVal));

        Label lightingLabel = new Label("Lighting: " + formatScore(cafe.getAvgLighting()));
        lightingLabel.setFont(Font.font(15));
        lightingLabel.setTextFill(Color.web(subTextColorVal));

        Label seatingLabel = new Label("Seating: " + formatScore(cafe.getAvgSeating()));
        seatingLabel.setFont(Font.font(15));
        seatingLabel.setTextFill(Color.web(subTextColorVal));

        Label drinkQualityLabel = new Label("Drink Quality: " + formatScore(cafe.getAvgDrinkQuality()));
        drinkQualityLabel.setFont(Font.font(15));
        drinkQualityLabel.setTextFill(Color.web(subTextColorVal));

        Label foodQualityLabel = new Label("Food Quality: " + formatScore(cafe.getAvgFoodQuality()));
        foodQualityLabel.setFont(Font.font(15));
        foodQualityLabel.setTextFill(Color.web(subTextColorVal));

        Button rateButton = new Button("Add Rating");
        rateButton.setStyle(
            "-fx-background-color: " + buttonColorVal + ";" 
            + "-fx-text-fill: white;" 
            + "-fx-background-radius: 7;"
        );
        rateButton.setOnAction(event -> showRatingForm(cafe, () -> 
            {
                noiseLevelLabel.setText("Noise Level: " + formatScore(cafe.getAvgNoiseLevel()));
                cleanlinessLabel.setText("Cleanliness: " + formatScore(cafe.getAvgCleanliness()));
                lightingLabel.setText("Lighting: " + formatScore(cafe.getAvgLighting()));
                seatingLabel.setText("Seating: " + formatScore(cafe.getAvgSeating()));
                drinkQualityLabel.setText("Drink Quality: " + formatScore(cafe.getAvgDrinkQuality()));
                foodQualityLabel.setText("Food Quality: " + formatScore(cafe.getAvgFoodQuality()));

                refreshCafeList.run();
            }));

        detailsRoot.getChildren().addAll(
            nameLabel, 
            addressLabel, 
            cityLabel, 
            ratingsHeading,
            noiseLevelLabel, 
            cleanlinessLabel,
            lightingLabel,
            seatingLabel,
            drinkQualityLabel,
            foodQualityLabel,
            rateButton
        );

        Scene detailsScene = new Scene(detailsRoot, 400, 500);

        detailsStage.setScene(detailsScene);
        detailsStage.setResizable(false);
        detailsStage.show();
    }

    // EFFECTS: formats the score to show two decimal places
    String formatScore(Double score) {
        if (score == null) {
            return "No ratings yet";
        }
        return String.format(Locale.ROOT, "%.1f/5", score);
    }

    // MODIFIES: cafe
    // EFFECTS: displays a ratings form that lets the user rate the cafe; if the user submits a valid rating,
    // add that new rating to the cafe's list of ratings
    private void showRatingForm(Cafe cafe, Runnable submittedRating) {
        Stage ratingsStage = new Stage();
        ratingsStage.setTitle("StudySpot: Rate " + cafe.getName());

        // change window icon to coffee cup image
        Image coffeeIcon = new Image(getClass().getResource("/coffeecup.png").toExternalForm());
        ratingsStage.getIcons().add(coffeeIcon);

        VBox ratingsRoot = new VBox(20);
        ratingsRoot.setPadding(new Insets(20));
        ratingsRoot.setAlignment(Pos.TOP_LEFT);
        ratingsRoot.setStyle("-fx-background-color: " + bgColorVal + ";");

        Label title = new Label("Rate " + cafe.getName());
        title.setFont(Font.font("Georgia", 25));
        title.setTextFill(Color.web(dBrownColorVal_1));

        ComboBox<Integer> noiseLvl = makeScoreBox();
        ComboBox<Integer> cleanliness = makeScoreBox();
        ComboBox<Integer> lighting = makeScoreBox();
        ComboBox<Integer> seating = makeScoreBox();
        ComboBox<Integer> drinkQuality = makeScoreBox();
        ComboBox<Integer> foodQuality = makeScoreBox();

        GridPane scores = new GridPane();
        scores.setHgap(10);
        scores.setVgap(10);

        scores.addRow(0, new Label("Noise Level\n(5 is very quiet, 1 is very loud)"), noiseLvl);
        scores.addRow(1, new Label("Cleanliness"), cleanliness);
        scores.addRow(2, new Label("Lighting"), lighting);
        scores.addRow(3, new Label("Seating"), seating);
        scores.addRow(4, new Label("Drink Quality"), drinkQuality);
        scores.addRow(5, new Label("Food Quality"), foodQuality);

        Button addRatingButton = makeAddRatingButton(cafe, noiseLvl, cleanliness, lighting, seating,
            drinkQuality, foodQuality, ratingsStage, submittedRating);

        ratingsRoot.getChildren().addAll(
            title, 
            scores,
            addRatingButton
        );

        Scene ratingsScene = new Scene(ratingsRoot, 400, 500);

        ratingsStage.setScene(ratingsScene);
        ratingsStage.setResizable(false);
        ratingsStage.show();
    }

    // EFFECTS: makes 5 checkboxes numbered 1-5 that the user can select when making a rating
    private ComboBox<Integer> makeScoreBox() {
        ComboBox<Integer> scores = new ComboBox<>();
        scores.setPromptText("Select");
        scores.getItems().addAll(1, 2, 3, 4, 5);
        return scores;
    }

    // MODIFIES: cafe (upon submitting rating)
    // EFFECTS: makes the rating button for the rating form
    private Button makeAddRatingButton(Cafe cafe, ComboBox<Integer> noiseLvl, ComboBox<Integer> cleanliness,
        ComboBox<Integer> lighting, ComboBox<Integer> seating, ComboBox<Integer> drinkQuality,
        ComboBox<Integer> foodQuality, Stage stage, Runnable submittedRating) {

        Button addRatingButton = new Button("Add Rating");

        addRatingButton.setStyle(
            "-fx-background-color: " + buttonColorVal + ";" 
            + "-fx-text-fill: white;" 
            + "-fx-background-radius: 7;"
        );

        addRatingButton.setOnAction(event -> {
            if (noiseLvl.getValue() == null || 
                cleanliness.getValue() == null || 
                lighting.getValue() == null ||
                seating.getValue() == null || 
                drinkQuality.getValue() == null ||
                foodQuality.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Incomplete Rating");
                    alert.setHeaderText("Please rate each category.");
                    alert.showAndWait();
                    return;
            }

            Rating rating = new Rating(noiseLvl.getValue(), 
                cleanliness.getValue(), 
                lighting.getValue(), 
                seating.getValue(), 
                drinkQuality.getValue(), 
                foodQuality.getValue());

            cafe.addRating(rating);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rating Submitted");
            alert.setHeaderText("Thank you for rating " + cafe.getName() + "!");
            alert.showAndWait();

            stage.close();
            submittedRating.run();
        });

        return addRatingButton;
    }

    // EFFECTS: creates a couple sample cafes with ratings
    private List<Cafe> cafesInit() {
        List<Cafe> cafes = new ArrayList<>();

        Cafe cafe1 = new Cafe("Hanlin Tea & Patisserie", "8328 Capstan Wy Unit 112", "Richmond");
        cafe1.addRating(new Rating(4, 5, 5, 3, 4, 3));
        cafe1.addRating(new Rating(3, 4, 4, 2, 5, 4));

        Cafe cafe2 = new Cafe("Nemesis Coffee GNW", "555 Great Northern Wy", "Vancouver");
        cafe2.addRating(new Rating(3, 3, 5, 4, 4, 4));
        cafe2.addRating(new Rating(2, 4, 4, 5, 3, 5));

        Cafe cafe3 = new Cafe("Juillet Cafe", "4341 North Road Unit 206", "Burnaby");
        cafe3.addRating(new Rating(1, 5, 3, 1, 5, 3));
        cafe3.addRating(new Rating(4, 4, 4, 3, 3, 4));

        Cafe cafe4 = new Cafe("Cafe & Life Co.", "6833 Buswell St #150", "Richmond");
        cafe4.addRating(new Rating(5, 4, 3, 4, 4, 5));
        cafe4.addRating(new Rating(4, 5, 4, 5, 3, 4));

        cafes.add(cafe1);
        cafes.add(cafe2);
        cafes.add(cafe3);
        cafes.add(cafe4);
        
        return cafes;
    }
}
