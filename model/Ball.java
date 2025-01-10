
package model;

import javafx.scene.shape.Circle;

public class Ball extends Circle {
    private double deltaX = 3;
    private double deltaY = -3;
    private double speed = 3;
    
    public Ball(double x, double y, double radius) {
        super(x, y, radius);
    }
    
    public void setSpeed(double speed) {
        this.speed = speed;
        double angle = Math.atan2(deltaY, deltaX);
        deltaX = Math.cos(angle) * speed;
        deltaY = Math.sin(angle) * speed;
    }
    
    public void move() {
        setCenterX(getCenterX() + deltaX);
        setCenterY(getCenterY() + deltaY);
    }
    
    public void reverseX() {
        deltaX *= -1;
    }
    
    public void reverseY() {
        deltaY *= -1;
    }
}
