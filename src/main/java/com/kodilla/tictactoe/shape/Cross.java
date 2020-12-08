package com.kodilla.tictactoe.shape;

import javafx.scene.image.Image;

public class Cross implements Shape {

    public static final Image cross = new Image("file:src/main/resources/cross.png");

    @Override
    public Image getShape() {
        return cross;
    }
}
