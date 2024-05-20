/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planefigures;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Perczel-Szabó Dániel
 */
public class Database {
    
    private final ArrayList<PlaneFigure> planeFigures;
    
    /**
     * Constructor to create the Database
     */
    public Database(){
        planeFigures=new ArrayList();
    }
    
    /**
     * Reads plane figures data from a file and populates the planeFigures ArrayList
     * 
     * @param filename Name of the file containing the data
     * @throws FileNotFoundException Thrown if the file cannot be found
     * @throws InvalidInputException Thrown if the input data is invalid or does not match the expected format
     */
    public void read(String filename) throws FileNotFoundException, InvalidInputException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int numPlaneFigures = sc.nextInt();
        sc.nextLine();

        while (sc.hasNext()) {
            PlaneFigure planeFigure;
            String line = sc.next();
            String[] parts = line.split(",");
            String figureType = parts[0];

            switch (figureType) {
                case "C":
                    double[] centerPoint = { Double.parseDouble(parts[1]), Double.parseDouble(parts[2]) };
                    planeFigure = new Circle(centerPoint, Double.parseDouble(parts[3]));
                    break;
                case "T":
                    double[] centerPoint1 = { Double.parseDouble(parts[1]), Double.parseDouble(parts[2]) };
                    planeFigure = new Triangle(centerPoint1, Double.parseDouble(parts[3]));
                    break;
                case "S":
                    double[] centerPoint2 = { Double.parseDouble(parts[1]), Double.parseDouble(parts[2]) };
                    planeFigure = new Square(centerPoint2, Double.parseDouble(parts[3]));
                    break;
                case "H":
                    double[] centerPoint3 = { Double.parseDouble(parts[1]), Double.parseDouble(parts[2]) };
                    planeFigure = new Hexagon(centerPoint3, Double.parseDouble(parts[3]));
                    break;
                default:
                    throw new InvalidInputException();
            }

            planeFigures.add(planeFigure);
        }
    }
    
    /**
     * Calculates and prints the coordinates of the smallest rectangle that encloses all the plane figures in the planeFigures list
     */
    public void smallestRectangle(){
        ArrayList<double[]> rightmostPoints=new ArrayList<double[]>();
        ArrayList<double[]> leftmostPoints=new ArrayList<double[]>();
        ArrayList<double[]> topPoints=new ArrayList<double[]>();
        ArrayList<double[]> bottomPoints=new ArrayList<double[]>();
        
        for(PlaneFigure pf : planeFigures){            
            rightmostPoints.add(pf.rightmostPoint());
            leftmostPoints.add(pf.leftmostPoint());
            topPoints.add(pf.topPoint());
            bottomPoints.add(pf.bottomPoint());
        }
        
        double[] rm=maxPoint(rightmostPoints,0);
        double[] lm=minPoint(leftmostPoints,0);
        double[] t=maxPoint(topPoints,1);
        double[] b=minPoint(bottomPoints,1);
        
        String rectangle="Top right: ("+rm[0]+";"+t[1]+"), bottom right: ("+rm[0]+";"+b[1]+"), bottom left: ("+lm[0]+";"+b[1]+"), top left: ("+lm[0]+";"+t[1]+")";
        System.out.println(rectangle);
    }
    
    /**
     * Returns with the point with the maximum coordinate value along the specified axis
     * 
     * @param points List of points to search
     * @param whichCo The coordinate axis to compare (0 for x, 1 for y)
     * @return The point with the maximum coordinate value along the specified axis
     */
    public double[] maxPoint(ArrayList<double[]> points, int whichCo){
        double max=points.get(0)[whichCo];
        double[] maxPoint=points.get(0);
        
        for(double[] point : points){
            if(point[whichCo]>max){
                max=point[whichCo];
                maxPoint=point;
            }
        }
        
        maxPoint[0]=Math.round(maxPoint[0]*100.00)/100.00;
        maxPoint[1]=Math.round(maxPoint[1]*100.00)/100.00;
        return maxPoint;
    }
    
    /**
     * Returns with the point with the minimum coordinate value along the specified axis
     * 
     * @param points List of points to search
     * @param whichCo The coordinate axis to compare (0 for x, 1 for y)
     * @return The point with the minimum coordinate value along the specified axis
     */
    public double[] minPoint(ArrayList<double[]> points, int whichCo){
        double min=points.get(0)[whichCo];
        double[] minPoint=points.get(0);
        
        for(double[] point : points){
            if(point[whichCo]<min){
                min=point[whichCo];
                minPoint=point;
            }
        }
        
        minPoint[0]=Math.round(minPoint[0]*100.00)/100.00;
        minPoint[1]=Math.round(minPoint[1]*100.00)/100.00;
        return minPoint;
    }
}
