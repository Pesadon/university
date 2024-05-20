/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package snake;

/**
 *
 * @author Perczel-Szabó Dániel
 */
public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);
    
    private final int deltaX;
    private final int deltaY;
    
    Direction(int deltaX,int deltaY){
        this.deltaX=deltaX;
        this.deltaY=deltaY;
    }
    
    public int getDeltaX(){
        return deltaX;
    }
    
    public int getDeltaY(){
        return deltaY;
    }
}
