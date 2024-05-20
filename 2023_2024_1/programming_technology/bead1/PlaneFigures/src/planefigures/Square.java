/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planefigures;

/**
 *
 * @author Perczel-Szabó Dániel
 */
public class Square extends PlaneFigure{
    
    /**
     * Constructor to create the square with given center point and length or side
     * 
     * @param centerPoint Center point of the square [x,y]
     * @param lengthOfR Length of side
     */
    public Square(double[] centerPoint,double lengthOfR){
        super('S',centerPoint,lengthOfR);
    }
    
    /**
     * Returns with the leftmost point of the square (leftmost x, given y)
     * 
     * @return Coordinates of the leftmost point [x,y]
     */
    @Override
    public double[] leftmostPoint(){
        
        double[] point = new double[2];
        point[0] = centerPoint[0] - (lengthOfR / 2.0);
        point[1] = centerPoint[1];
        
        return point;
    }
    
    /**
     * Returns with the rightmost point of the square (rightmost x, given y)
     * 
     * @return Coordinates of the rightmost point [x,y]
     */
    @Override
    public double[] rightmostPoint(){
        
        double[] point = new double[2];
        point[0] = centerPoint[0] + (lengthOfR / 2.0);
        point[1] = centerPoint[1];
        
        return point;
    }
    
    /**
     * Returns with the top point of the square (given x, highest y)
     * 
     * @return Coordinates of the top point [x,y]
     */
    @Override
    public double[] topPoint(){
        
        double[] point = new double[2];
        point[0] = centerPoint[0];
        point[1] = centerPoint[1] + (lengthOfR / 2.0);
        
        return point;
    }
    
    /**
     * Returns with the bottom point of the square (given x, lowest y)
     * 
     * @return Coordinates of the bottom point [x,y]
     */
    @Override
    public double[] bottomPoint(){
        
        double[] point=new double[2];
        point[0] = centerPoint[0];
        point[1] = centerPoint[1] - (lengthOfR / 2.0);
        
        return point;
    }
}
