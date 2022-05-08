package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SearchView extends JFrame {
    private JTextField titleJtext;
    private JTextField ratingJText;
    private JTextField caloriesJtext;
    private JTextField proteinJText;
    private JTextField fatJText;
    private JTextField sodiumJText;
    private JTextField priceJText;
    private JButton searchButton;

    public SearchView() {

        //this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setBounds(300, 200, 312, 370);
        this.setResizable(false);

        titleJtext = new JTextField(5);
        ratingJText = new JTextField(5);
        caloriesJtext = new JTextField(5);
        proteinJText = new JTextField(5);
        fatJText = new JTextField(5);
        sodiumJText = new JTextField(5);
        priceJText = new JTextField(5);
        searchButton = new JButton("Search");


        titleJtext.setToolTipText("title");
        ratingJText.setToolTipText("rating");
        proteinJText.setToolTipText("protein");
        fatJText.setToolTipText("fat");
        sodiumJText.setToolTipText("sodium");
        priceJText.setToolTipText("price");


        setPreferredSize(new Dimension(426, 320));
        setLayout(null);

        add(titleJtext);
        add(ratingJText);
        add(caloriesJtext);
        add(proteinJText);
        add(fatJText);
        add(sodiumJText);
        add(priceJText);
        add(searchButton);

        titleJtext.setBounds(140, 35, 100, 25);
        ratingJText.setBounds(140, 65, 100, 25);
        caloriesJtext.setBounds(140, 95, 100, 25);
        proteinJText.setBounds(140, 125, 100, 25);
        fatJText.setBounds(140, 155, 100, 25);
        sodiumJText.setBounds(140, 190, 100, 25);
        priceJText.setBounds(140, 225, 100, 25);
        searchButton.setBounds(140, 265, 100, 25);
    }

    public String getTitleJtext() {
        return titleJtext.getText();
    }

    public String getRatingJText() {
        return ratingJText.getText();
    }

    public String getCaloriesJtext() {
        return caloriesJtext.getText();
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

    public JButton getSearchButton() {
        return searchButton;
    }

    public void addSearchListener(ActionListener actionListener){
        this.searchButton.addActionListener(actionListener);
    }
}
