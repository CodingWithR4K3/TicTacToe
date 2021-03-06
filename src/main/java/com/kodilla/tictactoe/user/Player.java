package com.kodilla.tictactoe.user;

import com.kodilla.tictactoe.shape.Shape;

public class Player implements User {

    private Shape actualShape;

    @Override
    public Shape getActualShape() {
        return actualShape;
    }

    @Override
    public void setActualShape(Shape shape) {
        actualShape = shape;
    }

    @Override
    public boolean isComputer() {
        return false;
    }

    @Override
    public String toString() {
        return "Player";
    }
}