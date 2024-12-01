package designpatterns.questions.snakegame.controller;

import designpatterns.questions.snakegame.board.GameBoard;
import designpatterns.questions.snakegame.enums.Direction;
import designpatterns.questions.snakegame.enums.DirectionStrategy;
import designpatterns.questions.snakegame.snake.Snake;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);
    private GameBoard gameBoard;
    private Snake snake;
    private int moveCount;
    private boolean gameOver;
    private int score;
    private ScheduledExecutorService executor;
    private ScheduledFuture<?> scheduledFuture;

    public GameController(GameBoard gameBoard, Snake snake) {
        this.gameBoard = gameBoard;
        this.snake = snake;
        this.moveCount = 0;
        this.score = 0;
        this.gameOver = false;
        this.executor = Executors.newScheduledThreadPool(1);
    }
    public void startGame(){
        gameBoard.clearBoard();
        //gameBoard.placeFood();
        snake.reset();
        moveCount = 0;
        score = 0;
        gameBoard.printBoard(snake);
        printScore();
        logger.info("game started");
        resetTimer();
    }

    private void printScore() {
        System.out.println("Score: "+ score);
    }

    public void moveSnake(DirectionStrategy directionStrategy) {
        logger.info("moveSnake with "+directionStrategy.toString());
        if (gameOver || directionStrategy.ignore(snake.getDirectionStrategy())) {
            logger.info("moveSnake cancelled due to ignore");
            return;
        }

        snake.setDirectionStrategy(directionStrategy);
        logger.info("snake move set "+snake.getDirectionStrategy().toString());

        snake.move();
        logger.info("snake moved");

        moveCount++;

        if (gameBoard.isFood(snake.getBody().get(0))) {
            snake.grow();
            score+=10;
            gameBoard.placeNewFood();
        }

        if (isCollision()) {
            logger.error("Snake collided");
            endGame();
        } else {
            gameBoard.printBoard(snake);
            printScore();
            logger.info("Snake moved " + directionStrategy.toString());
        }
    }

    private boolean isCollision() {
         if(snake.isSelfCollision()) return true;
         // check if wall collision
        Point head = snake.getBody().get(0);
        /*if(head.x == gameBoard.getWidth() || head.y == gameBoard.getHeight()) return true;
        if(head.x < 0 || head.y < 0) return true;*/
        if(gameBoard.isObstacle(head)) return true;
        return false;
    }

    public void endGame() {
        gameOver = true;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        executor.shutdown();
        System.out.println("Game Over!");
        printScore();
        logger.info("Game Over!");
    }
    private class MoveTask implements Runnable {
        @Override
        public void run() {
            if (!gameOver) {
                moveSnake(snake.getDirectionStrategy());
                resetTimer();
            }
        }
    }

    public void resetTimer() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        scheduledFuture = executor.schedule(new MoveTask(), 5, TimeUnit.SECONDS);
    }
}
