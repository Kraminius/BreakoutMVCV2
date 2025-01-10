
package view;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.*;
import java.util.ArrayList;
import java.util.List;

public class GameView extends Pane {
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks = new ArrayList<>();
    private Text scoreText;
    private Text livesText;
    private Text levelText;
    private GameModel gameModel;
    
    public GameView(double width, double height) {
        gameModel = new GameModel();
        
        // Initialize paddle
        paddle = new Paddle(width/2 - 50, height - 50);
        paddle.setStyle("-fx-fill: blue;");
        
        // Initialize ball
        ball = new Ball(width/2, height - 100, 5);
        ball.setStyle("-fx-fill: red;");
        
        // Initialize HUD
        scoreText = new Text(10, 20, "Score: 0");
        livesText = new Text(width - 100, 20, "Lives: 3");
        levelText = new Text(width/2 - 30, 20, "Level: 1");
        
        // Initialize bricks
        createBricks(width);
        
        // Add all elements to the pane
        getChildren().addAll(paddle, ball, scoreText, livesText, levelText);
        // Bricks are already added in createBricks method
    }
    
    public void createBricks(double width) {
        // First remove existing bricks from both list and children
        getChildren().removeIf(node -> node instanceof Brick);
        bricks.clear();
        
        double brickWidth = 60;
        double brickHeight = 20;
        double gap = 5;
        int rows = 5;
        int cols = (int)(width / (brickWidth + gap));
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Brick brick = new Brick(
                    col * (brickWidth + gap),
                    row * (brickHeight + gap) + 50,
                    brickWidth,
                    brickHeight
                );
                brick.setStyle("-fx-fill: green;");
                bricks.add(brick);
            }
        }
        
        // Add all bricks at once
        getChildren().addAll(bricks);
    }
    
    public void updateHUD() {
        scoreText.setText("Score: " + gameModel.getScore());
        livesText.setText("Lives: " + gameModel.getLives());
        levelText.setText("Level: " + gameModel.getLevel());
    }
    
    public void nextLevel() {
        gameModel.nextLevel();
        createBricks(getWidth());
        updateHUD();
    }
    
    public Paddle getPaddle() { return paddle; }
    public Ball getBall() { return ball; }
    public List<Brick> getBricks() { return bricks; }
    public GameModel getGameModel() { return gameModel; }
}
