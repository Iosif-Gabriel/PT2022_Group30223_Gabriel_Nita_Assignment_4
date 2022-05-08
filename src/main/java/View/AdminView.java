package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class AdminView extends JFrame {
    private JButton importButton;
    private JButton addButton;
    private JButton delButton;
    private JButton modifyButton;

    private JButton rap1button;
    private JButton rap2Button;
    private JButton rap3button;
    private JButton rap4Button;

    public AdminView() {

        JFrame frame = new JFrame ("AdminView");
        //frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setBounds(300, 200, 312, 370);
        this.setResizable(false);

        importButton = new JButton ("ImportProd");
        addButton = new JButton ("AddProd");
        delButton = new JButton ("DelProd");
        modifyButton = new JButton ("ModifyProd");
        rap1button = new JButton ("GenRap1");
        rap2Button = new JButton ("GenRap2");
        rap3button = new JButton ("GenRap3");
        rap4Button = new JButton ("GenRap4");

        setPreferredSize (new Dimension (300, 267));
        setLayout (null);

        this.add(importButton);
        this.add(addButton);
        this.add(delButton);
        this.add(modifyButton);

        this.add(rap1button);
        this.add(rap2Button);
        this.add(rap3button);
        this.add(rap4Button);

        importButton.setBounds (100, 15, 101, 25);
        addButton.setBounds (100, 40, 101, 25);
        delButton.setBounds (100, 65, 101, 25);
        modifyButton.setBounds (100, 90, 101, 25);

        rap1button.setBounds (100, 140, 101, 25);
        rap2Button.setBounds (100, 165, 101, 25);
        rap3button.setBounds (100, 190, 101, 25);
        rap4Button.setBounds (100, 215, 101, 25);
    }

    public JButton getImportButton() {
        return importButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDelButton() {
        return delButton;
    }

    public JButton getModifyButton() {
        return modifyButton;
    }

    public JButton getRap1button() {
        return rap1button;
    }

    public JButton getRap2Button() {
        return rap2Button;
    }

    public JButton getRap3button() {
        return rap3button;
    }

    public JButton getRap4Button() {
        return rap4Button;
    }

    public void addImportProdListener(ActionListener actionListener){
        this.importButton.addActionListener(actionListener);
    }

    public void addAddProdListener(ActionListener actionListener){
        this.addButton.addActionListener(actionListener);
    }

    public void addDelProdListener(ActionListener actionListener){
        this.delButton.addActionListener(actionListener);
    }

    public void addModifyProdListener(ActionListener actionListener){
        this.modifyButton.addActionListener(actionListener);
    }


    public void addGetRap1Listener(ActionListener actionListener){
        this.rap1button.addActionListener(actionListener);
    }

    public void addGetRap2Listener(ActionListener actionListener){
        this.rap2Button.addActionListener(actionListener);
    }

    public void addGetRap3Listener(ActionListener actionListener){
        this.rap3button.addActionListener(actionListener);
    }

    public void addGetRap4Listener(ActionListener actionListener){
        this.rap4Button.addActionListener(actionListener);
    }


}

