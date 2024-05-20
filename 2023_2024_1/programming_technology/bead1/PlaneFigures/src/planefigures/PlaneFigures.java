/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package planefigures;

import java.io.FileNotFoundException;

/**
 *
 * @author Perczel-Szabó Dániel
 */
public class PlaneFigures {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Database database = new Database();
        try {
            database.read("data3.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        database.smallestRectangle();
    }
}
