package com.kodilla.tictactoe;

import com.kodilla.tictactoe.utils.ApplicationFont;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private final Image imageback = new Image("file:src/main/resources/Background.jpg");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(myBoard());

        primaryStage.setScene(scene);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Parent myBoard() {

        GridPane grid = new GridPane();

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        grid.setBackground(background);

        Label welcomeLabel = new Label("Welcome to TicTacToe");
        welcomeLabel.setFont(new Font("Arial", 18));
        welcomeLabel.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        grid.add(welcomeLabel, 0, 0, 3, 1);

        Label startLabel = new Label("Which one goes first?");
        startLabel.setFont(ApplicationFont.appFont());

        RadioButton playerButton = new RadioButton("User");
        playerButton.setFont(ApplicationFont.appFont());

        RadioButton computerButton = new RadioButton("Computer");
        computerButton.setFont(ApplicationFont.appFont());

        ToggleGroup toggleWhichOneStarts = new ToggleGroup();
        playerButton.setToggleGroup(toggleWhichOneStarts);
        computerButton.setToggleGroup(toggleWhichOneStarts);

        grid.add(startLabel, 3, 0, 1, 1);
        grid.add(playerButton, 3, 1, 1, 2);
        grid.add(computerButton, 3, 2, 1, 1);

        Button[] buttonArray = new Button[9];

        for (int index = 0; index < 9; index++) {
            Button button = new Button();
            button.setMinSize(126, 118);
            buttonArray[index] = button;
        }

        grid.add(buttonArray[0], 0, 1);
        grid.add(buttonArray[1], 1, 1);
        grid.add(buttonArray[2], 2, 1);
        grid.add(buttonArray[3], 0, 2);
        grid.add(buttonArray[4], 1, 2);
        grid.add(buttonArray[5], 2, 2);
        grid.add(buttonArray[6], 0, 3);
        grid.add(buttonArray[7], 1, 3);
        grid.add(buttonArray[8], 2, 3);

        return grid;
    }
}
