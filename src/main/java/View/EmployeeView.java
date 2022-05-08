package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Observable;
import java.util.Observer;

public class EmployeeView extends JFrame implements Observer {
    private JPanel contentthing;
    private JLabel jthing;
    private JTextArea area;
    private JScrollPane scrollPane;

    public EmployeeView(){
        setTitle("EmployeeView");
        setResizable(false);
        setBounds(360, 110, 410, 310);
        contentthing = new JPanel();
        contentthing.setBorder(new EmptyBorder(6, 6, 6, 6));
        setContentPane(contentthing);
        contentthing.setLayout(null);

        jthing = new JLabel("No orders...");
        jthing.setBounds(30, 30, 260, 14);
        contentthing.add(jthing);

        area = new JTextArea();
        area.setEditable(false);

        scrollPane = new JScrollPane(area);
        scrollPane.setBounds(10, 55, 350, 170);
        scrollPane.setVisible(false);
        contentthing.add(scrollPane);
    }
    @Override
    public void update(Observable o, Object arg) {
        jthing.setText("New Orders: ");
        scrollPane.setVisible(true);
        if (area.getText().isBlank()) {
            area.setText(arg + "\n");
        } else {
            area.setText(area.getText() + arg + "\n");
        }
    }
}
