package com.kodilla.tictactoe.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MessageBox {


    public static void createDrawBox() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Draw", ButtonType.OK);
        alert.setTitle("Game Over");
        alert.setHeaderText("Gamer Over");
        alert.setContentText("Nobody won. Draw!");
        alert.showAndWait().ifPresent(rs -> {
        });
    }

    public static void createHelloBox() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hello", ButtonType.OK);
        alert.setTitle("Hello!");
        alert.setHeaderText("Welcome in a game of TicTacToe");
        alert.setContentText("Before starting new game first choose Player and Shape!");
        alert.showAndWait().ifPresent(rs -> {
        });
    }
}
