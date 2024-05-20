/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rubikora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Perczel-Szabó Dániel
 */
public class RubikOraView extends JFrame{
    private RubikOraModel model;
    private JButton[][] buttons;
    private JTextField[][] textFieldsWithNum;
    private JTextField[][] textFieldsEmpty;

    public RubikOraView(RubikOraModel model) {
        this.model = model;
        setTitle("Rubik Óra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

        buttons = new JButton[5][5];
        textFieldsWithNum = new JTextField[5][5];
        
        textFieldsEmpty = new JTextField[5][5];

        JPanel panel = new JPanel(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i % 2 == 0 && j % 2 == 0)) {
                    textFieldsWithNum[i][j]=new JTextField(String.valueOf(model.getClockValue(i / 2, j / 2)));
                    textFieldsWithNum[i][j].setHorizontalAlignment(JTextField.CENTER);
                    textFieldsWithNum[i][j].setFont(new Font("Cascadia Code",1,20));
                    textFieldsWithNum[i][j].setEditable(false);
                    panel.add(textFieldsWithNum[i][j]);
                }else if((i%2==0 && j%2!=0) || (i%2!=0 && j%2==0)){
                    textFieldsEmpty[i][j] = new JTextField("");
                    textFieldsEmpty[i][j].setEditable(false);
                    panel.add(textFieldsEmpty[i][j]);
                } else{
                    buttons[i][j] = new JButton("");
                    buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                    panel.add(buttons[i][j]);
                }
            }
        }

        JButton newGameButton = new JButton("Új játék");
        newGameButton.addActionListener(e -> {
            model.initializeBoard();
            updateBoard();
        });

        add(panel, BorderLayout.CENTER);
        add(newGameButton, BorderLayout.SOUTH);
    }

    public void updateBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i % 2 == 0 && j % 2 == 0)) {
                    textFieldsWithNum[i][j].setText(String.valueOf(model.getClockValue(i/2, j/2)));
                }
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int modelRow = row / 2;
            int modelCol = col / 2;

            model.updateClock(modelRow, modelCol);
            updateBoard();

            if (model.isGameWon()) {
                JOptionPane.showMessageDialog(RubikOraView.this, "Gratulálunk! Nyertél " + model.getSteps() + " lépéssel!");
                model.initializeBoard();
                updateBoard();
            }
        }
    }
}
