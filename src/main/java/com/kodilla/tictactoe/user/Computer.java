package com.kodilla.tictactoe.user;

import com.kodilla.tictactoe.shape.Shape;

public class Computer extends Player implements User {

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
    public String toString() {
        return "Computer";
    }
}