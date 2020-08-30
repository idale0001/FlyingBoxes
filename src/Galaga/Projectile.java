package Galaga;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Projectile extends Rectangle{
     
    private double vel;
    public Projectile(double xPos, double yPos, double width, double height,Color color,double v){
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
        this.setWidth(width);
        this.setHeight(height);
        this.setFill(color);
        vel = v;
    }
    
    public boolean ready(Projectile proj){
       return proj.getLayoutY()-proj.getWidth()-100>this.getLayoutY();
    }
    public void move() {
        this.setLayoutY(this.getLayoutY()+vel);
    }
}
