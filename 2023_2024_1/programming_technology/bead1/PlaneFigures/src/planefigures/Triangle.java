/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planefigures;

/**
 *
 * @author Perczel-Szabó Dániel
 */
public class Triangle extends PlaneFigure{
    
    /**
     * Constructor to create the triangle with given center point and length or side
     * 
     * @param centerPoint Center point of the triangle [x,y]
     * @param lengthOfR Length of side
     */
    public Triangle(double[] centerPoint,double lengthOfR){
        super('T',centerPoint,lengthOfR);
    }
    
    /**
     * Returns with the leftmost point of the triangle (leftmost x, lowest y)
     * 
     * @return Coordinates of the leftmost point [x,y]
     */
    @Override
    public double[] leftmostPoint(){
        
        double[] point=new double[2];
        point[0] = centerPoint[0] - (lengthOfR / 2);
        point[1] = centerPoint[1] - ((lengthOfR * (Math.sin(Math.toRadians(60))))*(1.0/3.0));
        
        return point;
    }
    
    /**
     * Returns with the rightmost point of the triangle (rightmost x, lowest y)
     * 
     * @return Coordinates of the rightmost point [x,y]
     */
    @Override
    public double[] rightmostPoint(){
        
        double[] point=new double[2];
        point[0] = centerPoint[0] + (lengthOfR / 2);
        point[1] = centerPoint[1] - ((lengthOfR * (Math.sin(Math.toRadians(60))))*(1.0/3.0));
        
        return point;
    }
    
    /**
     * Returns with the top point of the triangle (given x, highest y)
     * 
     * @return Coordinates of the top point [x,y]
     */
    @Override
    public double[] topPoint(){
        
        double[] point=new double[2];
        point[0] = centerPoint[0];
        point[1] = centerPoint[1] + ((lengthOfR * (Math.sin(Math.toRadians(60))))*(2.0/3.0));
        
        return point;
    }
    
    /**
     * Returns with the bottom point of the triangle (given x, lowest y)
     * 
     * @return Coordinates of the bottom point [x,y]
     */
    @Override
    public double[] bottomPoint(){
        
        double[] point=new double[2];
        point[0] = centerPoint[0];
        point[1] = centerPoint[1] - ((lengthOfR * (Math.sin(Math.toRadians(60))))*(1.0/3.0));
        
        return point;
    }
}
