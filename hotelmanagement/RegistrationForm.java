package hotelmanagement;

import java.awt.event.*;
import javax.swing.*;

public class RegistrationForm extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField t1, t2;
    JButton regBtn, loginBtn;
    JTextArea area;

    static String savedUser = "";
    static String savedPass = "";

    public RegistrationForm() {

        setLayout(null);

        l1 = new JLabel("New Username:");
        l2 = new JLabel("New Password:");

        t1 = new JTextField();
        t2 = new JTextField();

        regBtn = new JButton("Register");
        loginBtn = new JButton("Go to Login");

        area = new JTextArea();

        l1.setBounds(30,30,120,30);
        t1.setBounds(150,30,120,30);

        l2.setBounds(30,80,120,30);
        t2.setBounds(150,80,120,30);

        regBtn.setBounds(30,130,100,30);
        loginBtn.setBounds(150,130,120,30);

        area.setBounds(30,180,240,120);

        add(l1); add(t1);
        add(l2); add(t2);
        add(regBtn); add(loginBtn);
        add(area);

        regBtn.addActionListener(this);
        loginBtn.addActionListener(this);

        setSize(320,350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == regBtn) {
            savedUser = t1.getText();
            savedPass = t2.getText();

            area.append("User Registered!\n");
            t1.setText("");
            t2.setText("");
        }

        if(e.getSource() == loginBtn) {
            new LoginForm();
            dispose();
        }
    }
}
