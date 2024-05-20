/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planefigures;

/**
 *
 * @author Perczel-Szabó Dániel
 */
public class Circle extends PlaneFigure{
    
    /**
     * Constructor to create the circle with given center point and length of radius
     * 
     * @param centerPoint Center point of the circle [x,y]
     * @param lengthOfR Length of radius
     */
    public Circle(double[] centerPoint,double lengthOfR){
        super('C',centerPoint,lengthOfR);
    }
    
    /**
     * Returns with the leftmost point of the circle (leftmost x, given y)
     * 
     * @return Coordinates of the leftmost point [x,y]
     */
    @Override
    public double[] leftmostPoint(){
        
        double[] point = new double[2];
        point[0] = centerPoint[0] - lengthOfR;
        point[1] = centerPoint[1];
        
        return point;
    }
    
    /**
     * Returns with the rightmost point of the circle (rightmost x, given y)
     * 
     * @return Coordinates of the rightmost point [x,y]
     */
    @Override
    public double[] rightmostPoint(){
        
        double[] point = new double[2];
        point[0] = centerPoint[0] + lengthOfR;
        point[1] = centerPoint[1];
        
        return point;
    }
    
    /**
     * Returns with the top point of the circle (given x, highest y)
     * 
     * @return Coordinates of the top point [x,y]
     */
    @Override
    public double[] topPoint(){
        
        double[] point=new double[2];
        point[0]=centerPoint[0];
        point[1]=centerPoint[1]+lengthOfR;
        
        return point;
    }
    
    /**
     * Returns with the bottom point of the circle (given x, lowest y)
     * 
     * @return Coordinates of the bottom point [x,y]
     */
    @Override
    public double[] bottomPoint(){
        
        double[] point=new double[2];
        point[0]=centerPoint[0];
        point[1]=centerPoint[1]-lengthOfR;
        
        return point;
    }
}
