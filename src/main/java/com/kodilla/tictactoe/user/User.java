package com.kodilla.tictactoe.user;

import com.kodilla.tictactoe.shape.Shape;

public interface User {

    Shape getActualShape();

    void setActualShape(Shape shape);

    @Override
    String toString();
}