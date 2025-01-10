
package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import model.*;
import view.GameView;

public class GameController {
    private GameView gameView;
    private Scene scene;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private static final double PADDLE_SPEED = 5;
    private static final double INITIAL_BALL_SPEED = 3;
    
    public GameController(GameView gameView, Scene scene) {
        this.gameView = gameView;
        this.scene = scene;
        setupControls();
        startGameLoop();
    }
    
    private void setupControls() {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) leftPressed = true;
            if (e.getCode() == KeyCode.RIGHT) rightPressed = true;
        });
        
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) leftPressed = false;
            if (e.getCode() == KeyCode.RIGHT) rightPressed = false;
        });
    }
    
    private void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        }.start();
    }
    
    private void update() {
        if (leftPressed) {
            gameView.getPaddle().moveLeft(PADDLE_SPEED, 0);
        }
        if (rightPressed) {
            gameView.getPaddle().moveRight(PADDLE_SPEED, scene.getWidth());
        }
        
        Ball ball = gameView.getBall();
        GameModel gameModel = gameView.getGameModel();
        
        // Apply speed multiplier to ball movement
        double currentSpeed = INITIAL_BALL_SPEED * gameModel.getBallSpeedMultiplier();
        ball.setSpeed(currentSpeed);
        ball.move();
        
        // Check wall collisions
        if (ball.getCenterX() <= ball.getRadius() || 
            ball.getCenterX() >= scene.getWidth() - ball.getRadius()) {
            ball.reverseX();
        }
        if (ball.getCenterY() <= ball.getRadius()) {
            ball.reverseY();
        }
        
        // Check paddle collision
        if (ball.getBoundsInParent().intersects(gameView.getPaddle().getBoundsInParent())) {
            ball.reverseY();
        }
        
        // Check brick collisions
        boolean allBricksDestroyed = true;
        for (Brick brick : gameView.getBricks()) {
            if (!brick.isDestroyed() && ball.getBoundsInParent().intersects(brick.getBoundsInParent())) {
                brick.setDestroyed(true);
                brick.setVisible(false);
                ball.reverseY();
                gameModel.incrementScore(10);
                gameView.updateHUD();
            }
            if (!brick.isDestroyed()) {
                allBricksDestroyed = false;
            }
        }
        
        // Next level if all bricks are destroyed
        if (allBricksDestroyed) {
            gameView.nextLevel();
            resetBall();
        }
        
        // Ball out of bounds
        if (ball.getCenterY() >= scene.getHeight()) {
            if (gameModel.loseLife()) {
                // Game over
                gameModel.reset();
                gameView.createBricks(scene.getWidth());
            }
            resetBall();
            gameView.updateHUD();
        }
    }
    
    private void resetBall() {
        Ball ball = gameView.getBall();
        ball.setCenterX(scene.getWidth() / 2);
        ball.setCenterY(scene.getHeight() - 100);
        ball.setSpeed(INITIAL_BALL_SPEED);
    }
}
