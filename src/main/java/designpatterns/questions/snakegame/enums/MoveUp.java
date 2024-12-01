package designpatterns.questions.snakegame.enums;

import java.awt.*;

public class MoveUp implements DirectionStrategy {
    @Override
    public Point move(Point currentPosition, int boardWidth, int boardHeight) {
        int newY = currentPosition.y - 1;
        if (newY < 0) {
            newY = boardHeight - 1;
        }
        return new Point(currentPosition.x, newY);
    }

    public String toString() {
        return "Up";
    }
    public boolean ignore(DirectionStrategy directionStrategy) {
        if(directionStrategy.toString().equals("Down")) return true;
        return false;
    }
}
