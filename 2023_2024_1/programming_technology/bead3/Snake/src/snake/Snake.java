/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Perczel-Szabó Dániel
 */
public class Snake {
    private LinkedList<Point> body;
    private Direction direction;
    private boolean isGrowing;
    
    public Snake(int boardWidth, int boardHeight,boolean isGrowing) {
        this.body = new LinkedList<>();
        this.isGrowing = isGrowing;

        Random random = new Random();
        int startX = random.nextInt(boardWidth);
        int startY = random.nextInt(boardHeight);
        Point initialPosition = new Point(startX, startY);

        this.direction = Direction.values()[random.nextInt(Direction.values().length)];

        this.body.add(initialPosition);
        Point secondBodyPart = new Point(initialPosition.x - this.direction.getDeltaX(),
                                         initialPosition.y - this.direction.getDeltaY());
        this.body.add(secondBodyPart);
    }
    
    public void move(){
        Point head=body.getFirst();
        Point newHead=new Point(head.x + direction.getDeltaX(), head.y + direction.getDeltaY());
        body.addFirst(newHead);
        if(!isGrowing){
            body.removeLast();
        }
        else{
            isGrowing=false;
        }
    }
    
    public boolean checkCollisionWithItself() {
        Point head = body.getFirst();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkCollisionWithWall(int boardWidth, int boardHeight) {
        Point head = body.getFirst();
        return head.x < 0 || head.x >= boardWidth || head.y < 0 || head.y >= boardHeight;
    }
    
    public boolean checkCollisionWithObstacle(List<FoodOrObstacle> obstacles) {
        for (FoodOrObstacle obstacle : obstacles) {
            if (body.getFirst().getLocation()==obstacle.getLocation() && !obstacle.isFood()) {
                return true;
            }
        }
        return false;
    }
}
