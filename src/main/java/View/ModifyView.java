package View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ModifyView extends JFrame {
    private JTextField titleJText;
    private JTextField ratingJText;
    private JTextField caloriesJText;
    private JTextField proteinJText;
    private JTextField fatJText;
    private JTextField sodiumJText;
    private JTextField priceJText;
    private JButton modifyButton;
    private JLabel modifylabel;
    private JTextField oldtitleJtext;
    private JLabel newTitlelabel;
    private JLabel oldtitleLabel;

    public ModifyView() {

        this.setBounds(300, 200, 450, 370);
        this.setResizable(false);

        titleJText = new JTextField (5);
        ratingJText = new JTextField (5);
        caloriesJText = new JTextField (5);
        proteinJText = new JTextField (5);
        fatJText = new JTextField (5);
        sodiumJText = new JTextField (5);
        priceJText = new JTextField (5);
        modifyButton = new JButton ("Modify");
        modifylabel = new JLabel ("Modify Product:");
        oldtitleJtext = new JTextField (5);
        newTitlelabel = new JLabel ("New title:");
        oldtitleLabel = new JLabel ("Old title:");


        titleJText.setToolTipText ("title");
        ratingJText.setToolTipText ("rating");
        proteinJText.setToolTipText ("protein");
        fatJText.setToolTipText ("fat");
        sodiumJText.setToolTipText ("sodium");
        priceJText.setToolTipText ("price");


        setPreferredSize(new Dimension(426, 320));
        setLayout(null);


        add (titleJText);
        add (ratingJText);
        add (caloriesJText);
        add (proteinJText);
        add (fatJText);
        add (sodiumJText);
        add (priceJText);
        add (modifyButton);
        add (modifylabel);
        add (oldtitleJtext);
        add (newTitlelabel);
        add (oldtitleLabel);


        titleJText.setBounds (105, 35, 100, 25);
        ratingJText.setBounds (105, 65, 100, 25);
        caloriesJText.setBounds (105, 95, 100, 25);
        proteinJText.setBounds (105, 125, 100, 25);
        fatJText.setBounds (105, 155, 100, 25);
        sodiumJText.setBounds (105, 185, 100, 25);
        priceJText.setBounds (105, 215, 100, 25);
        modifyButton.setBounds (105, 260, 100, 25);
        modifylabel.setBounds (110, 10, 102, 25);
        oldtitleJtext.setBounds (315, 40, 100, 25);
        newTitlelabel.setBounds (45, 35, 60, 25);
        oldtitleLabel.setBounds (255, 40, 60, 25);
    }
    public String getTitleJText() {
        return titleJText.getText();
    }

    public String getRatingJText() {
        return ratingJText.getText();
    }

    public String getCaloriesJText() {
        return caloriesJText.getText();
    }

    public String getProteinJText() {
        return proteinJText.getText();
    }

    public String getFatJText() {
        return fatJText.getText();
    }

    public String getSodiumJText() {
        return sodiumJText.getText();
    }

    public String getPriceJText() {
        return priceJText.getText();
    }

    public String getOldNameJText(){
        return oldtitleJtext.getText();
    }

    public JButton getModifyButton() {
        return modifyButton;
    }

    public void addModifyButtonListener(ActionListener actionListener){
        this.modifyButton.addActionListener(actionListener);
    }

}
