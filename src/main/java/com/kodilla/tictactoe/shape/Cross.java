package com.kodilla.tictactoe.shape;

import javafx.scene.image.Image;

 public class Cross implements Shape {

     private static final Image CROSS = new Image("file:src/main/resources/cross.png");

     @Override
     public Image getShape() {
         return CROSS;
     }
 }
