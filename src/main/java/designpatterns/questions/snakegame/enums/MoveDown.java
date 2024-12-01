package designpatterns.questions.snakegame.enums;

import java.awt.*;

public class MoveDown implements DirectionStrategy {
    @Override
    public Point move(Point currentPosition, int boardWidth, int boardHeight) {
        int newY = currentPosition.y + 1;
        if (newY >= boardHeight) {
            newY = 0;
        }
        return new Point(currentPosition.x, newY);
    }

    /**
     * @return
     */
    @Override
    public boolean ignore(DirectionStrategy directionStrategy) {
       if(directionStrategy.toString().equals("Up")) return true;
       return false;
    }

    public String toString() {
        return "Down";
    }
}
