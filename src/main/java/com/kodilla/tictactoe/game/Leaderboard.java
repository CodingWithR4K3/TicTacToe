package com.kodilla.tictactoe.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class Leaderboard {
    private static final Path PATH = Paths.get("leaderboard.gameinfo");

    private int userPoints;
    private int computerPoints;

    public void addUserPoints() {
        userPoints++;
        update();
    }

    public void addComputerPoints() {
        computerPoints++;
        update();
    }

    public void init() throws IOException {
        if (Files.exists(PATH)) {
            String scores = Files.readAllLines(PATH).get(0);
            String[] splitScores = scores.split(",");

            userPoints = Integer.parseInt(splitScores[0]);
            computerPoints = Integer.parseInt(splitScores[1]);
        }
    }

    public void update() {
        try {
            String scores = String.format("%d,%d", userPoints, computerPoints);
            Files.write(PATH, Collections.singletonList(scores));

        } catch (IOException e) {
            System.err.println("Could not update Leaderboard file!!!");
        }
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "userPoints=" + userPoints +
                ", computerPoints=" + computerPoints +
                '}';
    }

    public int getUserPoints() {
        return userPoints;
    }

    public int getComputerPoints() {
        return computerPoints;
    }
}
