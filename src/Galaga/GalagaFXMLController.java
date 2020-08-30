
package Galaga;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GalagaFXMLController implements Initializable {
    
    Ship ship;
    Text text;
    ArrayList<Enemy> enemies;
    
    @FXML
    private AnchorPane AnchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ship = new Ship(275,750,50,50,Color.BROWN,5);
        enemies = new ArrayList<>();
        AnchorPane.getChildren().add(ship);
        ship.setFocusTraversable(true);
        text = new Text("Lives: "+ ship.getHealth());
        text.setLayoutX(20);
        text.setLayoutY(20);
        AnchorPane.getChildren().add(text);
        ArrayList<KeyCode> keys = new ArrayList<>();
        ArrayList<Projectile> projs = new ArrayList<>();
        ship.setOnKeyPressed((KeyEvent event) -> {
            if(!keys.contains(event.getCode())) {
                keys.add(event.getCode());
            }
            if(keys.contains(KeyCode.A)) {
                ship.moveLeft();
            }else if(keys.contains(KeyCode.D)){
                ship.moveRight();
            }
        });
        ship.setOnKeyReleased((KeyEvent event) -> {
            if(keys.contains(event.getCode())) {
                keys.remove(event.getCode());
            }
            if(event.getCode().equals(KeyCode.SPACE)) {
                Projectile next = ship.fire();
                if(projs.isEmpty()||projs.get(projs.size()-1).ready(next)) {
                    projs.add(next);
                    AnchorPane.getChildren().add(projs.get(projs.size()-1));
                    
                }
            }
        });
        AnimationTimer action;
        action = new AnimationTimer() {
            int wave = 30;
            @Override
            public void handle(long l) {
                //spawns in new enemies
                if(enemies.isEmpty() ){
                    wave++;
                    for(int x = 0; x<wave;x++){
                        Enemy next = new Enemy(25,25,25,10,Color.BROWN);
                        if(x!=0){
                            while(!enemies.get(enemies.size()-1).ready(next)){
                                enemies.stream().forEach((enemy) -> {
                                    enemy.move(1);
                                });
                            }
                            AnchorPane.getChildren().add(next);
                            
                        }else{
                            AnchorPane.getChildren().add(next);
                        }
                        enemies.add(next);
                    }
                }
                
                //moves enemies and projectiles as well as checking if they hit each other or the player
                for(int i = 0; i<projs.size();i++){
                    projs.get(i).move();
                    
                    if(ship.isHit(projs.get(i))){
                        ship.setHealth(ship.getHealth()-1);
                        AnchorPane.getChildren().remove(projs.remove(i));
                        i--;
                    }
                    if(i>-1){
                       for(int j = 0; j<enemies.size();j++){
                           enemies.get(j).move(projs.size()+1);
                            if(j>-1&&i>-1){
                                if(enemies.get(j).isHit(projs.get(i))){
                                    AnchorPane.getChildren().remove(enemies.remove(j));
                                    j--;
                                    AnchorPane.getChildren().remove(projs.remove(i));
                                    i--;
                                }
                                
                           }  
                       }
                       if(i>-1&&(projs.get(i).getLayoutY()<0||projs.get(i).getLayoutY()>800)){
                            AnchorPane.getChildren().remove(projs.remove(i));
                            i--;
                        }
                    }
                }
                for(Enemy enemy:enemies){
                    enemy.move(projs.size()+1);
                      if(enemy.getLayoutX()>ship.getLayoutX()&&enemy.getLayoutX()<ship.getLayoutX()+ship.getWidth()&&Math.random()>.99){
                          Projectile next = enemy.fire();
                          projs.add(next);
                          AnchorPane.getChildren().add(projs.get(projs.size()-1));
                      } 
                }
                
                if(ship.getHealth()<=0){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameOver/GameOverFXML.fxml"));
                    Stage stage = (Stage)AnchorPane.getScene().getWindow();
                    try{
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);
                    }catch(IOException e){}
                    
                }
                text.setText("Lives: "+ ship.getHealth());
            }
        };
    action.start();
    }    
       
}
