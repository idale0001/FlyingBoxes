package Galaga;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ship extends Rectangle{
    
    private int health;
    
    public Ship(double xPos, double yPos, double width, double height,Color color, int h){
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
        this.setWidth(width);
        this.setHeight(height);
        this.setFill(color);
        health = h;
    }
    
    public int getHealth(){return health;}
    
    public void setHealth(int h){health = h;}
    
    public void moveLeft(){
        if(this.getLayoutX()>0)
            this.setLayoutX(this.getLayoutX()-10);
    }
    public void moveRight(){
        if(this.getLayoutX()+this.getWidth()<600)
            this.setLayoutX(this.getLayoutX()+10);
    }
   
    public Projectile fire() {
    	return new Projectile(this.getLayoutX()+this.getWidth()/2,this.getLayoutY()-30,10,30,Color.AQUA,-30.0);
    }
    
    public boolean isHit(Projectile proj){
        return this.getBoundsInParent().intersects(proj.getBoundsInParent());
    }
}
