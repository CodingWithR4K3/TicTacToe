package com.kodilla.tictactoe.shape;

import javafx.scene.image.Image;

 public class Circle implements Shape {

     private static final Image CIRCLE = new Image("file:src/main/resources/circle.png");

     @Override
     public Image getShape() {
         return CIRCLE;
     }
 }
