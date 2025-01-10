
package model;

import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {
    private boolean destroyed = false;
    
    public Brick(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    
    public boolean isDestroyed() {
        return destroyed;
    }
    
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
