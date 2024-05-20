/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rubikora;

import javax.swing.*;
/**
 *
 * @author Perczel-Szabó Dániel
 */
public class RubikOra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        RubikOraModel model = new RubikOraModel(3);
        RubikOraView view = new RubikOraView(model);
        view.setVisible(true);
    });
    }
    
}
