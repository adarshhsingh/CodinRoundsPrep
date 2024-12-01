package designpatterns.questions.snakegame.enums;

import java.awt.*;

public class MoveRight implements DirectionStrategy {
    @Override
    public Point move(Point currentPosition, int boardWidth, int boardHeight) {
        int newX = currentPosition.x + 1;
        if (newX >= boardWidth) {
            newX = 0;
        }
        return new Point(newX, currentPosition.y);
    }
    public String toString() {
        return "Right";
    }
    public boolean ignore(DirectionStrategy directionStrategy) {
        if(directionStrategy.toString().equals("Left")) return true;
        return false;
    }
}
