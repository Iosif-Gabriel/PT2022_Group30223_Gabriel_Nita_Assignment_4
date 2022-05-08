package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Report2View extends JFrame {
    private JTextField nrJText;
    private JButton generateButton;
    private JLabel raport1Label;
    private JLabel nrLabel;

    public Report2View() {

        this.setBounds(300, 200, 312, 370);
        this.setResizable(false);

        nrJText = new JTextField(5);
        generateButton = new JButton("Generate");
        raport1Label = new JLabel("Raport2:");
        nrLabel = new JLabel("Number of times:");


        setPreferredSize(new Dimension(426, 320));
        setLayout(null);


        add(nrJText);
        add(generateButton);
        add(raport1Label);
        add(nrLabel);


        nrJText.setBounds(145, 105, 100, 25);
        generateButton.setBounds(145, 145, 100, 25);
        raport1Label.setBounds(150, 75, 100, 25);
        nrLabel.setBounds(40, 105, 105, 25);
    }

    public void addGenerateListener(ActionListener actionListener){
        this.generateButton.addActionListener(actionListener);
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public String getNrJText() {
        return nrJText.getText();
    }
}

