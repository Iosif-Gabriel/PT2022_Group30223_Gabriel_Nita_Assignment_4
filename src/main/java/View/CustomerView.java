package View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CustomerView extends JFrame {
    private JButton searchButton;
    private JButton viewButton;
    private JButton createOrderButton;

    public CustomerView() {

        //this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setBounds(300, 200, 315, 370);
        this.setResizable(false);

        searchButton = new JButton("Search");
        viewButton = new JButton("ViewAll");
        createOrderButton = new JButton("Create Order");


        setPreferredSize(new Dimension(335, 269));
        setLayout(null);


        add(searchButton);
        add(viewButton);
        add(createOrderButton);


        searchButton.setBounds(115, 135, 110, 25);
        viewButton.setBounds(115, 105, 110, 25);
        createOrderButton.setBounds(115, 165, 110, 25);
    }

    public void addViewAllButtonListener(ActionListener actionListener){
        this.viewButton.addActionListener(actionListener);
    }

    public void addSearchButtonListener(ActionListener actionListener){
        this.searchButton.addActionListener(actionListener);
    }

    public void addOrderButtonListener(ActionListener actionListener){
        this.createOrderButton.addActionListener(actionListener);
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getCreateOrderButton() {
        return createOrderButton;
    }
}
