package designpatterns.questions.snakegame.enums;

import java.awt.*;

public interface DirectionStrategy {
    Point move(Point currentPosition, int boardWidth, int boardHeight);
    boolean ignore(DirectionStrategy directionStrategy);
    String toString();
}

