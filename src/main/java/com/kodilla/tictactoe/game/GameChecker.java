package com.kodilla.tictactoe.game;

import com.kodilla.tictactoe.shape.Shape;

public class GameChecker {

    public boolean checkDidYouWin(Shape[] gameBoard) {
        return checkFirstRow(gameBoard) || checkSecondRow(gameBoard) || checkThirdRow(gameBoard) ||
                checkFirstCol(gameBoard) || checkSecondCol(gameBoard) || checkThirdCol(gameBoard) ||
                checkFirstSlant(gameBoard) || checkSecondSlant(gameBoard);
    }

    private Boolean checkFirstRow(Shape[] gameBoard) {
        return gameBoard[0] == gameBoard[1] && gameBoard[1] == gameBoard[2] && gameBoard[0] != null;
    }

    private Boolean checkSecondRow(Shape[] gameBoard) {
        return gameBoard[3] == gameBoard[4] && gameBoard[3] == gameBoard[5] && gameBoard[3] != null;
    }

    private Boolean checkThirdRow(Shape[] gameBoard) {
        return gameBoard[6] == gameBoard[7] && gameBoard[6] == gameBoard[8] && gameBoard[6] != null;
    }

    private Boolean checkFirstCol(Shape[] gameBoard) {
        return gameBoard[0] == gameBoard[3] && gameBoard[0] == gameBoard[6] && gameBoard[0] != null;
    }

    private Boolean checkSecondCol(Shape[] gameBoard) {
        return gameBoard[1] == gameBoard[4] && gameBoard[1] == gameBoard[7] && gameBoard[1] != null;
    }

    private Boolean checkThirdCol(Shape[] gameBoard) {
        return gameBoard[2] == gameBoard[5] && gameBoard[2] == gameBoard[8] && gameBoard[2] != null;
    }

    private Boolean checkFirstSlant(Shape[] gameBoard) {
        return gameBoard[0] == gameBoard[4] && gameBoard[0] == gameBoard[8] && gameBoard[0] != null;
    }

    private Boolean checkSecondSlant(Shape[] gameBoard) {
        return gameBoard[2] == gameBoard[4] && gameBoard[2] == gameBoard[6] && gameBoard[2] != null;
    }
}
