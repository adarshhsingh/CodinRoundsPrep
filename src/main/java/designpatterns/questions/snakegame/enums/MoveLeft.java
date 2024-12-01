package designpatterns.questions.snakegame.enums;

import java.awt.*;

public class MoveLeft implements DirectionStrategy {
    @Override
    public Point move(Point currentPosition, int boardWidth, int boardHeight) {
        int newX = currentPosition.x - 1;
        if (newX < 0) {
            newX = boardWidth - 1;
        }
        return new Point(newX, currentPosition.y);
    }
    public String toString() {
        return "Left";
    }
    public boolean ignore(DirectionStrategy directionStrategy) {
        if(directionStrategy.toString().equals("Right")) return true;
        return false;
    }
}
