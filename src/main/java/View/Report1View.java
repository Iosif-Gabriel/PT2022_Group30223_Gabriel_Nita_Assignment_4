package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Report1View extends JFrame {
    private JTextField startHourjText;
    private JTextField endHourJText;
    private JButton generateButton;
    private JLabel raport1Label;
    private JLabel startLabel;
    private JLabel endLabel;

    public Report1View() {

        this.setBounds(300, 200, 312, 370);
        this.setResizable(false);

        startHourjText = new JTextField(5);
        endHourJText = new JTextField(5);
        generateButton = new JButton("Generate");
        raport1Label = new JLabel("Raport1:");
        startLabel = new JLabel("Start Hour:");
        endLabel = new JLabel("End Hour:");


        setPreferredSize(new Dimension(426, 320));
        setLayout(null);


        add(startHourjText);
        add(endHourJText);
        add(generateButton);
        add(raport1Label);
        add(startLabel);
        add(endLabel);


        startHourjText.setBounds(100, 80, 100, 25);
        endHourJText.setBounds(100, 120, 100, 25);
        generateButton.setBounds(105, 175, 100, 25);
        raport1Label.setBounds(95, 45, 100, 25);
        startLabel.setBounds(30, 80, 70, 25);
        endLabel.setBounds(30, 120, 70, 25);
    }

    public void addGenerateListener(ActionListener actionListener){
        this.generateButton.addActionListener(actionListener);
    }

    public String getStartHourjText() {
        return startHourjText.getText();
    }

    public String getEndHourJText() {
        return endHourJText.getText();
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

}
