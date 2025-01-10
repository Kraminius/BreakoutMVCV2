
package model;

import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {
    private static final double PADDLE_WIDTH = 100;
    private static final double PADDLE_HEIGHT = 15;
    
    public Paddle(double x, double y) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }
    
    public void moveLeft(double speed, double minX) {
        setX(Math.max(getX() - speed, minX));
    }
    
    public void moveRight(double speed, double maxX) {
        setX(Math.min(getX() + speed, maxX - getWidth()));
    }
}
