
package model;

public class GameModel {
    private int score = 0;
    private int lives = 3;
    private int level = 1;
    private int blocksDestroyed = 0;
    private double ballSpeedMultiplier = 1.0;
    
    public void incrementScore(int points) {
        score += points;
        blocksDestroyed++;
        if (blocksDestroyed % 4 == 0) {
            ballSpeedMultiplier += 0.1;
        }
    }
    
    public boolean loseLife() {
        lives--;
        return lives <= 0;
    }
    
    public void nextLevel() {
        level++;
        blocksDestroyed = 0;
    }
    
    public void reset() {
        score = 0;
        lives = 3;
        level = 1;
        blocksDestroyed = 0;
        ballSpeedMultiplier = 1.0;
    }
    
    // Getters
    public int getScore() { return score; }
    public int getLives() { return lives; }
    public int getLevel() { return level; }
    public double getBallSpeedMultiplier() { return ballSpeedMultiplier; }
}
