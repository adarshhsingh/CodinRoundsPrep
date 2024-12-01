package designpatterns.questions.snakegame.rungame;

import designpatterns.questions.snakegame.board.GameBoard;
import designpatterns.questions.snakegame.controller.GameController;
import designpatterns.questions.snakegame.enums.*;
import designpatterns.questions.snakegame.snake.Snake;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SnakeGame {

    public static void main(String[] args) throws InterruptedException {
        GameBoard gameBoard = new GameBoard(10, 10);
        Snake snake = new Snake(new Point(4, 4), 4, gameBoard.getHeight(), gameBoard.getWidth());
        GameController gameController = new GameController(gameBoard, snake);
        gameController.startGame();

        Map<String, DirectionStrategy> directionMap = new HashMap<>();
        directionMap.put("W", new MoveUp());
        directionMap.put("S", new MoveDown());
        directionMap.put("A", new MoveLeft());
        directionMap.put("D", new MoveRight());

        Scanner scanner = new Scanner(System.in);


        while (!gameController.isGameOver()) {
            System.out.println("Enter direction ((up)W-(left)A-(down)S-(right)D) or Q to quit:");
            long startTime = System.currentTimeMillis();

            while ((System.currentTimeMillis() - startTime) < 5000  && !scanner.hasNextLine()) {
                // Wait for user input or timeout
            }

            if(scanner.hasNextLine()) {
                String input = scanner.nextLine().trim().toUpperCase();
                if("Q".equals(input)) {
                    System.out.println("Exiting game.");
                    scanner.close();
                    gameController.endGame();
                } else if (directionMap.containsKey(input)) {
                gameController.moveSnake(directionMap.get(input));
            } else {
                System.out.println("Invalid input. Please enter W, A, S, D, or Q.");
            }
            // Reset the timer whenever input is received
            if (!gameController.isGameOver()) {
                gameController.resetTimer();
            }

            }
        }
        scanner.close();
    }
}
