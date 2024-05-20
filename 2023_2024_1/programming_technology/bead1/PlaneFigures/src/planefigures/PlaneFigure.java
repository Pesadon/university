/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planefigures;

/**
 *
 * @author Perczel-Szabó Dániel
 */
public abstract class PlaneFigure {
    char type;
    double[] centerPoint;
    double lengthOfR;
    
    public PlaneFigure(char type,double[]centerPoint,double lengthOfR){
        this.type=type;
        this.centerPoint=centerPoint;
        this.lengthOfR=lengthOfR;
    }
    
    public abstract double[] leftmostPoint();
    public abstract double[] rightmostPoint();
    public abstract double[] topPoint();
    public abstract double[] bottomPoint();
}
