package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Report4View extends JFrame {
    private JTextField dayJText;
    private JButton generateButton;
    private JLabel report4Label;
    private JLabel nrLabel;

    public Report4View() {

        this.setBounds(300, 200, 312, 370);
        this.setResizable(false);

        dayJText = new JTextField(5);
        generateButton = new JButton("Generate");
        report4Label = new JLabel("Report4:");
        nrLabel = new JLabel("Day:");


        setPreferredSize(new Dimension(426, 320));
        setLayout(null);


        add(dayJText);
        add(generateButton);
        add(report4Label);
        add(nrLabel);


        dayJText.setBounds(145, 105, 100, 25);
        generateButton.setBounds(145, 145, 100, 25);
        report4Label.setBounds(150, 75, 100, 25);
        nrLabel.setBounds(115, 105, 30, 25);
    }

    public void addGenerateListener(ActionListener actionListener){
        this.generateButton.addActionListener(actionListener);
    }

    public String getDayJText() {
        return dayJText.getText();
    }

    public JButton getGenerateButton() {
        return generateButton;
    }
}

