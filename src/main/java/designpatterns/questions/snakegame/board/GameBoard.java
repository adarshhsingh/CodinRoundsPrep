package designpatterns.questions.snakegame.board;

import designpatterns.questions.snakegame.snake.Snake;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Getter
public class GameBoard {
    private int width;
    private int height;
    private boolean [][] board;
    private Set<Point> obstacles;
    private Point food;
    private Random random;

    public GameBoard(int height, int width) {
        this.width = width;
        this.height = height;
        this.random = new Random();
        this.board = new boolean[width][height];
        this.obstacles = new HashSet<>();
        initializeBoundaryWalls();
        placeNewFood();
    }

    public void addObstacle(Point obstacle) {
        obstacles.add(obstacle);
        setPosition(obstacle.x, obstacle.y, true);
    }

    private void initializeBoundaryWalls() {
        for (int x = 0; x < width; x++) {
            if(x==width/2) continue;
            addObstacle(new Point(x, 0)); // Top wall
            addObstacle(new Point(x, height - 1)); // Bottom wall
        }
        for (int y = 0; y < height; y++) {
            if(y==height/2) continue;
            addObstacle(new Point(0, y)); // Left wall
            addObstacle(new Point(width - 1, y)); // Right wall
        }
    }

    public boolean isObstacle(Point point) {
        return obstacles.contains(point);
    }

    public boolean isPositionOccupied(int x, int y) {
        return board[x][y];
    }

    public void setPosition(int x, int y, boolean occupied) {
        board[x][y] = occupied;
    }

    public void placeNewFood() {
        if(food != null) {
            setPosition(food.x, food.y, false); // Remove food position
        }
        int x, y;
        do {
            x = random.nextInt(width);
            y = random.nextInt(height);
        } while (isPositionOccupied(x, y) || isObstacle(new Point(x, y)));
        food = new Point(x, y);
        setPosition(food.x, food.y, true); // Mark food position
    }

    public boolean isFood(Point point) {
        return food.equals(point);
    }

    public void clearBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!isObstacle(new Point(i, j)) && !isFood(new Point(i, j))) {
                    board[i][j] = false;
                }
            }
        }
    }

    public void printBoard(Snake snake) {
        clearBoard();
        for (Point p : snake.getBody()) {
            setPosition(p.x, p.y, true);
        }
        for (Point obstacle : obstacles) {
            setPosition(obstacle.x, obstacle.y, true);
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isPositionOccupied(x, y)) {
                    if(isObstacle(new Point(x,y))) {
                        System.out.print("X ");
                    } else if (isFood(new Point(x, y))){
                        System.out.print("F ");
                    } else {
                        System.out.print("0 ");
                    }
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
