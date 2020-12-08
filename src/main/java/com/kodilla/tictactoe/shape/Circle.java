package com.kodilla.tictactoe.shape;

import javafx.scene.image.Image;

public class Circle implements Shape {

    public static final Image circle = new Image("file:src/main/resources/circle.jpeg");

    @Override
    public Image getShape() {
        return circle;
    }
}
