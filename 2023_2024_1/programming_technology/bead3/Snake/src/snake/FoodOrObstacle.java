/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;
import java.awt.Point;

/**
 *
 * @author Perczel-Szabó Dániel
 */
public class FoodOrObstacle {
    private Point location;
    private boolean isFood;
    
    public FoodOrObstacle(Point location, boolean isFood) {
        this.location = location;
        this.isFood = isFood;
    }
    
    public Point getLocation(){
        return location;
    }
    
    public boolean isFood(){
        return isFood;
    }
}
