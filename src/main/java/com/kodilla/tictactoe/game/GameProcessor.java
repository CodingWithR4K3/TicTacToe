package com.kodilla.tictactoe.game;

import com.kodilla.tictactoe.shape.Shape;
import com.kodilla.tictactoe.user.Player;
import com.kodilla.tictactoe.user.User;
import com.kodilla.tictactoe.utils.MessageBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Random;


public class GameProcessor {

    private final GameInfo gameInfo;
    private final GameChecker gameChecker;
    private final Leaderboard leaderboard;

    public GameProcessor(Player actualPlayer, Leaderboard leaderboard) {
        this.gameInfo = new GameInfo(actualPlayer);
        this.leaderboard = leaderboard;
        this.gameChecker = new GameChecker();
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void clickButton(Button button, GridPane gridPane) {

        if (gameInfo.getEndGame()) {
            int id = Integer.parseInt(button.getId());
            button.setGraphic(new ImageView(GameInfo.getActualPlayer().getActualShape().getShape()));
            setCrossAndCircleInButtonsBasedId(id);
            gameInfo.setRoundNumber(gameInfo.getRoundNumber() + 1);

            checkIfGameEnds();

            changePlayer();
        }

        if (gameInfo.getRoundNumber() != 9 && gameInfo.getEndGame()) {
            computerClickButton(gridPane);
        }

        if (gameInfo.getRoundNumber() == 9 && gameInfo.getEndGame()) {
            MessageBox.createDrawBox();
        }
    }

    private void updateLeaderboard() {
        User actualPlayer = GameInfo.getActualPlayer();
        if (!actualPlayer.isComputer()) {
            leaderboard.addUserPoints();
        } else {
            leaderboard.addComputerPoints();
        }
    }

    private void setCrossAndCircleInButtonsBasedId(int buttonID) {

        Shape[] temporaryArray = gameInfo.getGameBoard();
        temporaryArray[buttonID - 1] = GameInfo.getActualPlayer().getActualShape();
        gameInfo.setGameBoard(temporaryArray);
    }

    private int computerMoveGenerator() {
        Random computerMoveGenerator = new Random();
        return computerMoveGenerator.nextInt(9) + 1;
    }

    private String computerMoveInString() {

        boolean correctMove = false;
        Shape[] temporaryArray = gameInfo.getGameBoard();
        int computerMoveInInt = computerMoveGenerator();

        while (!correctMove) {
            if (temporaryArray[computerMoveInInt - 1] != null) {
                computerMoveInInt = computerMoveGenerator();
            } else {
                correctMove = true;
            }
        }

        setCrossAndCircleInButtonsBasedId(computerMoveInInt);
        return "#" + computerMoveInInt;
    }

    public void computerClickButton(GridPane gridPane) {
        Button computerButton = (Button) gridPane.lookup(computerMoveInString());
        computerButton.setGraphic(new ImageView(GameInfo.getActualPlayer().getActualShape().getShape()));
        computerButton.setDisable(true);
        gameInfo.setRoundNumber(gameInfo.getRoundNumber() + 1);

        checkIfGameEnds();

        changePlayer();
    }

    private void checkIfGameEnds() {
        if (gameChecker.checkDidYouWin(gameInfo.getGameBoard())) {
            gameInfo.setEndGame(true);
            updateLeaderboard();
            createMessageBox();
        }
    }

    private void changePlayer() {
        User temporaryPlayer = GameInfo.getActualPlayer();

        gameInfo.setActualPlayer(gameInfo.getSecondPlayer());
        gameInfo.setSecondPlayer(temporaryPlayer);
    }

    public void createMessageBox() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Start new game", ButtonType.OK);
        alert.setTitle("Game Over");
        alert.setHeaderText("Game Over");
        alert.setContentText("Won " + GameInfo.getActualPlayer());
        alert.showAndWait().ifPresent(rs -> {
        });
    }
}