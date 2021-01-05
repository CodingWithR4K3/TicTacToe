package com.kodilla.tictactoe.game;

import com.kodilla.tictactoe.shape.Shape;
import com.kodilla.tictactoe.user.Computer;
import com.kodilla.tictactoe.user.Player;
import com.kodilla.tictactoe.user.User;

public class GameInfo {

    private static User actualPlayer;
    private User secondPlayer;
    private Shape[] gameBoard;
    private int roundNumber;
    private boolean endGame;

    public GameInfo(User actualPlayer) {
        GameInfo.actualPlayer = actualPlayer;

        if (actualPlayer instanceof Player) {
            this.secondPlayer = new Computer();
        }
        roundNumber = 0;
        gameBoard = new Shape[9];
        endGame = false;
    }

    public static User getActualPlayer() {
        return actualPlayer;
    }

    public void setActualPlayer(User actualPlayer) {
        GameInfo.actualPlayer = actualPlayer;
    }

    public User getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(User secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Shape[] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Shape[] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public boolean getEndGame() {
        return !endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
}