package designpatterns.questions.snakegame.snake;

import designpatterns.questions.snakegame.enums.Direction;
import designpatterns.questions.snakegame.enums.DirectionStrategy;
import designpatterns.questions.snakegame.enums.MoveRight;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Snake {
    private static final Logger logger = LoggerFactory.getLogger(Snake.class);
    private List<Point> body;
    private Point lastPoint;
    private DirectionStrategy directionStrategy;
    private Point initialPosition;
    private int initialSize;
    private int boardWidth;
    private int boardHeight;
    public Snake(Point initialPosition, int initialSize, int boardHeight, int boardWidth) {
        this.initialPosition = initialPosition;
        this.initialSize = initialSize;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        body = new LinkedList<>();
        setDefault();
    }
    //move()
    //grow()
    //boolean isSelfCollision()
    public void reset() {
        body.clear();
        setDefault();
    }

    private void setDefault() {
        for (int i = 0; i < initialSize; i++) {
            body.add(new Point(initialPosition.x-i, initialPosition.y));
        }
        lastPoint = body.get(body.size()-1);
        this.directionStrategy = new MoveRight(); // Default direction
    }

    public void move() {
        logger.info("Snake Move Called");
        Point head = body.get(0);
        Point newHead = directionStrategy.move(head, boardWidth, boardHeight);
        body.add(0, newHead);
        lastPoint = body.get(body.size()-1);
        body.remove(body.size()-1);
        logger.info("Snake Moved");
    }

    public void grow() {
        body.add(lastPoint);
    }

    public boolean isSelfCollision() {
        Point head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }
}
