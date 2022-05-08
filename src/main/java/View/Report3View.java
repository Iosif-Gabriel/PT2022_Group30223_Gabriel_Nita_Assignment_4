package View;


import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Report3View extends JFrame {
    private JTextField nrtimesjText;
    private JTextField amountJText;
    private JButton generateButton;
    private JLabel raport1Label;
    private JLabel nrTimesLabel;
    private JLabel amountLabel;

    public Report3View() {

        this.setBounds(300, 200, 312, 370);
        this.setResizable(false);

        nrtimesjText = new JTextField(5);
        amountJText = new JTextField(5);
        generateButton = new JButton("Generate");
        raport1Label = new JLabel("Raport3:");
        nrTimesLabel = new JLabel("Nr times:");
        amountLabel = new JLabel("Amount:");


        setPreferredSize(new Dimension(426, 320));
        setLayout(null);


        add(nrtimesjText);
        add(amountJText);
        add(generateButton);
        add(raport1Label);
        add(nrTimesLabel);
        add(amountLabel);

        nrtimesjText.setBounds(100, 80, 100, 25);
        amountJText.setBounds(100, 120, 100, 25);
        generateButton.setBounds(105, 175, 100, 25);
        raport1Label.setBounds(95, 45, 100, 25);
        nrTimesLabel.setBounds(30, 80, 70, 25);
        amountLabel.setBounds(30, 120, 70, 25);
    }

    public void addGenerateListener(ActionListener actionListener){
        this.generateButton.addActionListener(actionListener);
    }

    public String getNrtimesjText() {
        return nrtimesjText.getText();
    }

    public String getAmountJText() {
        return amountJText.getText();
    }

    public JButton getGenerateButton() {
        return generateButton;
    }
}



