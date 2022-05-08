package View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LogInRegisterView extends JFrame {
    private JButton registerButton;
    private JPasswordField registerPassText;
    private JTextField registerUserText;
    private JLabel lableSignUp;
    private JLabel loginLabel;
    private JPasswordField loginPassText;
    private JTextField loginUserText;
    private JButton loginButton;
    private JLabel userNamelabel;
    private JLabel paswordLogInLabel;
    private JLabel userNameRegLabel;
    private JLabel passwordRegLabel;

    public LogInRegisterView() {

        JFrame frame = new JFrame ("LogInView");
        //frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setBounds(300, 200, 600, 470);
        this.setResizable(false);

            registerButton = new JButton ("Register");
            registerPassText = new JPasswordField (5);
            registerUserText = new JTextField (5);
            lableSignUp = new JLabel ("Sign up");
            loginLabel = new JLabel ("Log in");
            loginPassText = new JPasswordField (5);
            loginUserText = new JTextField (5);
            loginButton = new JButton ("Log In");
            userNamelabel = new JLabel ("User Name:");
            paswordLogInLabel = new JLabel ("Password:");
            userNameRegLabel = new JLabel ("User Name:");
            passwordRegLabel = new JLabel ("Password:");

            setPreferredSize (new Dimension (654, 487));
            setLayout (null);


            add (registerButton);
            add (registerPassText);
            add (registerUserText);
            add (lableSignUp);
            add (loginLabel);
            add (loginPassText);
            add (loginUserText);
            add (loginButton);
            add (userNamelabel);
            add (paswordLogInLabel);
            add (userNameRegLabel);
            add (passwordRegLabel);


            registerButton.setBounds (120, 250, 100, 25);
            registerPassText.setBounds (120, 215, 100, 25);
            registerUserText.setBounds (120, 180, 100, 25);
            lableSignUp.setBounds (120, 155, 100, 25);
            loginLabel.setBounds (440, 155, 100, 25);
            loginPassText.setBounds (440, 215, 100, 25);
            loginUserText.setBounds (440, 180, 100, 25);
            loginButton.setBounds (440, 250, 100, 25);
            userNamelabel.setBounds (360, 185, 70, 25);
            paswordLogInLabel.setBounds (370, 220, 70, 25);
            userNameRegLabel.setBounds (45, 180, 70, 25);
            passwordRegLabel.setBounds (40, 215, 70, 25);
    }

    public void addLogInButtonListener(ActionListener actionListener){
        this.loginButton.addActionListener(actionListener);
    }

    public void addRegisterButtonListener(ActionListener actionListener){
        this.registerButton.addActionListener(actionListener);
    }
    public JButton getRegisterButton() {
        return registerButton;
    }

    public String getRegisterPassText() {
        return String.valueOf(registerPassText.getPassword());
    }

    public String getRegisterUserText() {
        return registerUserText.getText();
    }

    public String getLoginPassText() {
        return String.valueOf(loginPassText.getPassword());
    }

    public String getLoginUserText() {
        return loginUserText.getText();
    }

    public JButton getLoginButton() {
        return loginButton;
    }


}
