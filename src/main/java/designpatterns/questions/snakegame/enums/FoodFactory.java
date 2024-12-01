package designpatterns.questions.snakegame.enums;

import java.awt.*;
import java.util.Random;

public class FoodFactory extends GameElementFactory {
    private int boardWidth;
    private int boardHeight;

    public FoodFactory(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    @Override
    public Point createElement() {
        Random random = new Random();
        int x = random.nextInt(boardWidth);
        int y = random.nextInt(boardHeight);
        return new Point(x, y);
    }
}
