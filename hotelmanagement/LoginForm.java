package hotelmanagement;

import java.awt.event.*;
import javax.swing.*;

public class LoginForm extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField t1, t2;
    JButton loginBtn, regBtn;
    JTextArea area;

    public LoginForm() {

        setLayout(null);

        l1 = new JLabel("Username:");
        l2 = new JLabel("Password:");

        t1 = new JTextField();
        t2 = new JTextField();

        loginBtn = new JButton("Login");
        regBtn = new JButton("Register");

        area = new JTextArea();

        l1.setBounds(30,30,120,30);
        t1.setBounds(150,30,120,30);

        l2.setBounds(30,80,120,30);
        t2.setBounds(150,80,120,30);

        loginBtn.setBounds(30,130,100,30);
        regBtn.setBounds(150,130,120,30);

        area.setBounds(30,180,240,120);

        add(l1); add(t1);
        add(l2); add(t2);
        add(loginBtn); add(regBtn);
        add(area);

        loginBtn.addActionListener(this);
        regBtn.addActionListener(this);

        setSize(320,350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == loginBtn) {
            String u = t1.getText();
            String p = t2.getText();

            if(u.equals(RegistrationForm.savedUser) &&
               p.equals(RegistrationForm.savedPass)) {

                area.append("Login Successful!\n");
                new Dashboard();   // ✅ ADDED
                dispose();         // ✅ ADDED
            }
            else {
                area.append("Invalid Login!\n");
            }
        }

        if(e.getSource() == regBtn) {
            new RegistrationForm();
            dispose();
        }
    }
}
