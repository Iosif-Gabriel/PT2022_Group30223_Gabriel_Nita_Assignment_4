package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OrderView extends JFrame {
    private JTextField customerID;
    private JButton placeOrderButton;
    private JLabel placeOrderLabel;
    private JLabel nameLabel;

    public OrderView() {

        this.setBounds(300, 200, 312, 370);
        this.setResizable(false);

        customerID = new JTextField(5);
        placeOrderButton = new JButton("Place Order");
        placeOrderLabel = new JLabel("Place new Order:");
        nameLabel = new JLabel("Customer ID:");


        setPreferredSize(new Dimension(426, 320));
        setLayout(null);


        add(customerID);
        add(placeOrderButton);
        add(placeOrderLabel);
        add(nameLabel);


        customerID.setBounds(150, 100, 100, 25);
        placeOrderButton.setBounds(150, 130, 105, 25);
        placeOrderLabel.setBounds(150, 75, 100, 25);
        nameLabel.setBounds(50, 100, 100, 25);
    }

    public void addPlaceOrderListener(ActionListener actionListener){
        this.placeOrderButton.addActionListener(actionListener);
    }

    public String getCustomerID() {
        return customerID.getText();
    }

    public JButton getPlaceOrderButton() {
        return placeOrderButton;
    }
}
