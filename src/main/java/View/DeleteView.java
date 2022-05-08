package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class DeleteView extends JFrame {
    private JTextField delTextField;
    private JButton deleteButton;
    private JLabel titlelabel;

    public DeleteView() {

        //this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setBounds(300, 200, 312, 370);
        this.setResizable(false);

        delTextField = new JTextField(5);
        deleteButton = new JButton("Delete");
        titlelabel = new JLabel("Title:");

        setPreferredSize(new Dimension(335, 269));
        setLayout(null);

        add(delTextField);
        add(deleteButton);
        add(titlelabel);

        delTextField.setBounds(115, 105, 100, 25);
        deleteButton.setBounds(115, 135, 100, 25);
        titlelabel.setBounds(80, 105, 30, 25);
    }

    public void addDelProdListener(ActionListener actionListener){
        this.deleteButton.addActionListener(actionListener);
    }

    public String getDelTextField() {
        return delTextField.getText();
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

}
