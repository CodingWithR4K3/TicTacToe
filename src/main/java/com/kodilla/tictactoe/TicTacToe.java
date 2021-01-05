package com.kodilla.tictactoe;

import com.kodilla.tictactoe.game.GameInfo;
import com.kodilla.tictactoe.game.GameProcessor;
import com.kodilla.tictactoe.game.Leaderboard;
import com.kodilla.tictactoe.shape.Circle;
import com.kodilla.tictactoe.shape.Cross;
import com.kodilla.tictactoe.user.Computer;
import com.kodilla.tictactoe.user.Player;
import com.kodilla.tictactoe.utils.ApplicationFont;
import com.kodilla.tictactoe.utils.MessageBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {

    private GameProcessor processor;
    private final Image imageback = new Image("file:src/main/resources/background.jpg");
    private final Leaderboard leaderboard = new Leaderboard();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = new Scene(createBoard());
        primaryStage.setTitle("TicTacToe");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        leaderboard.init();
    }

    private Parent createBoard() {

        GridPane grid = new GridPane();

        BackgroundSize backgroundSize = new BackgroundSize(100, 130, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        grid.setBackground(background);

        Label helloLabel = new Label("Welcome in a game of TicTacToe!");
        helloLabel.setFont(new Font("Arial", 14));
        helloLabel.setPadding(new Insets(10, 10, 10, 1));

        grid.add(helloLabel, 0, 0, 3, 1);

        Label startLabel = new Label("Who starts first?");
        startLabel.setFont(ApplicationFont.appFont());

        RadioButton playerButton = new RadioButton("Player");
        playerButton.setFont(ApplicationFont.appFont());

        RadioButton computerButton = new RadioButton("Computer");
        computerButton.setFont(ApplicationFont.appFont());

        ToggleGroup toggleGroupWhichStarts = new ToggleGroup();
        playerButton.setToggleGroup(toggleGroupWhichStarts);
        computerButton.setToggleGroup(toggleGroupWhichStarts);

        grid.add(startLabel, 3, 1, 1, 1);
        grid.add(playerButton, 3, 1, 1, 2);
        grid.add(computerButton, 3, 2, 1, 1);

        Label whatShape = new Label("Choose shape: ");
        whatShape.setFont(ApplicationFont.appFont());

        RadioButton chooseCross = new RadioButton("Cross");
        chooseCross.setFont(ApplicationFont.appFont());

        RadioButton chooseCircle = new RadioButton("Circle");
        chooseCircle.setFont(ApplicationFont.appFont());

        ToggleGroup toggleGroupWhichShape = new ToggleGroup();
        chooseCircle.setToggleGroup(toggleGroupWhichShape);
        chooseCross.setToggleGroup(toggleGroupWhichShape);

        grid.add(whatShape, 3, 2, 1, 2);
        grid.add(chooseCircle, 3, 3, 1, 1);
        grid.add(chooseCross, 3, 4, 1, 1);

        Button[] buttonsArray = new Button[9];

        for (int index = 0; index < 9; index++) {
            Button button = new Button();
            button.setMinSize(126, 108);
            button.setId(String.valueOf(index + 1));
            button.setDisable(true);
            buttonsArray[index] = button;
        }

        grid.add(buttonsArray[0], 0, 1);
        grid.add(buttonsArray[1], 1, 1);
        grid.add(buttonsArray[2], 2, 1);
        grid.add(buttonsArray[3], 0, 2);
        grid.add(buttonsArray[4], 1, 2);
        grid.add(buttonsArray[5], 2, 2);
        grid.add(buttonsArray[6], 0, 3);
        grid.add(buttonsArray[7], 1, 3);
        grid.add(buttonsArray[8], 2, 3);

        for (int index = 0; index < 9; index++) {
            int finalI = index;
            buttonsArray[index].setOnAction((event) -> {
                processor.clickButton(buttonsArray[finalI], grid);
                buttonsArray[finalI].setDisable(true);
            });
        }

        Button newGameButton = new Button("Start new game");
        newGameButton.setFont(ApplicationFont.appFont());
        newGameButton.setId("100");

        grid.add(newGameButton, 3, 0, 1, 1);

        newGameButton.setOnAction((event) -> cleanup(buttonsArray, (RadioButton) toggleGroupWhichStarts.getSelectedToggle(),
                (RadioButton) toggleGroupWhichShape.getSelectedToggle(), grid));


        MessageBox.createHelloBox();

        Button leaderboardButton = new Button("LeaderBoard");
        leaderboardButton.setFont(ApplicationFont.appFont());
        leaderboardButton.setId("100");

        leaderboardButton.setOnAction(event ->
                createLeaderboardBox());

        grid.add(leaderboardButton, 2, 0, 1, 1);

        return grid;
    }

    private void cleanup(Button[] buttonsArray, RadioButton selectedButtonWhoStart, RadioButton selectedButtonWhatShape, GridPane grid) {

        setWhoStart(selectedButtonWhoStart, selectedButtonWhatShape);

        for (int index = 0; index < 9; index++) {
            buttonsArray[index].setGraphic(null);
            buttonsArray[index].setDisable(false);
        }

        if (GameInfo.getActualPlayer() instanceof Computer) {
            processor.computerClickButton(grid);
        }
    }

    private void setWhoStart(RadioButton selectedButtonWhoStart, RadioButton selectedButtonWhatShape) {

        if (selectedButtonWhoStart.getText().equals("Player")) {
            processor = new GameProcessor(new Player(), leaderboard);
        } else if (selectedButtonWhoStart.getText().equals("Computer")) {
            processor = new GameProcessor(new Computer(), leaderboard);
        }

        if (selectedButtonWhatShape.getText().equals("Circle")) {
            GameInfo.getActualPlayer().setActualShape(new Circle());
            processor.getGameInfo().getSecondPlayer().setActualShape(new Cross());
        } else if (selectedButtonWhatShape.getText().equals("Cross")) {
            GameInfo.getActualPlayer().setActualShape(new Cross());
            processor.getGameInfo().getSecondPlayer().setActualShape(new Circle());
        }
    }

    public void createLeaderboardBox() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Leaderboard", ButtonType.CLOSE);
        alert.setTitle("Leaderboard");
        alert.setHeaderText("Leaderboard");
        alert.setContentText("Player : " + " " + leaderboard.getUserPoints() + " " + "Computer: " + " " + leaderboard.getComputerPoints());
        alert.showAndWait().ifPresent(rs -> {
        });
    }

}