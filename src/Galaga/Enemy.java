package Galaga;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Enemy extends Rectangle{

    private boolean goRight;

    public Enemy(double xPos, double yPos, double width, double height,Color color){
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
        this.setWidth(width);
        this.setHeight(height);
        this.setFill(color);
        goRight = true;
    }
    public void move(int proj){
        if(this.getLayoutX()+this.getWidth()>=600){
            goRight = false;
            this.setLayoutY(this.getLayoutY()+30.0);
        }
        if(this.getLayoutX()<=0){
            goRight = true;
            this.setLayoutY(this.getLayoutY()+30.0);
        }
        if(goRight){
           this.setLayoutX(this.getLayoutX()+5.0/proj);
           if(this.getLayoutX()>300){
               this.setLayoutY(this.getLayoutY()-1.0/proj);
           }else{
                this.setLayoutY(this.getLayoutY()+1.0/proj);
           }
        }else{
           this.setLayoutX(this.getLayoutX()-5.0/proj);
           if(this.getLayoutX()<300){
               this.setLayoutY(this.getLayoutY()-1.0/proj);
           }else{
                this.setLayoutY(this.getLayoutY()+1.0/proj);
           }
        }
         
    }

    public boolean ready(Enemy enemy){
       return this.getLayoutX()-(enemy.getLayoutX()+enemy.getWidth()+5)>0;
    }
    public Projectile fire() {
    	return new Projectile(this.getLayoutX()+this.getWidth()/2,this.getLayoutY()+30,10,30,Color.AQUA,15);
    }
    public boolean isHit(Projectile proj){
        return this.getBoundsInParent().intersects(proj.getBoundsInParent());
    }
}
