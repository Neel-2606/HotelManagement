package hotelmanagement;

import java.awt.*;
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

        setTitle("🏨 Hotel Management System - Register 📝");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Background panel
        JPanel bg = new JPanel();
        bg.setBackground(new Color(230, 250, 255));
        bg.setBounds(0, 0, 500, 450);
        bg.setLayout(null);
        add(bg);

        // Heading
        JLabel heading = new JLabel("✨ Create New Account ✨");
        heading.setBounds(80, 20, 340, 40);
        heading.setFont(new Font("Serif", Font.BOLD, 28));
        heading.setForeground(new Color(0, 102, 204));
        bg.add(heading);

        l1 = new JLabel("New Username:");
        l2 = new JLabel("New Password:");

        l1.setFont(new Font("Arial", Font.BOLD, 14));
        l2.setFont(new Font("Arial", Font.BOLD, 14));
        l1.setForeground(new Color(0, 51, 102));
        l2.setForeground(new Color(0, 51, 102));

        t1 = new JTextField();
        t2 = new JPasswordField();

        l1.setBounds(80, 90, 140, 30);
        t1.setBounds(220, 90, 180, 35);

        l2.setBounds(80, 150, 140, 30);
        t2.setBounds(220, 150, 180, 35);

        t1.setFont(new Font("Arial", Font.PLAIN, 12));
        t2.setFont(new Font("Arial", Font.PLAIN, 12));

        regBtn = new JButton("✅ Register");
        loginBtn = new JButton("🔐 Go to Login");

        regBtn.setBackground(new Color(0, 153, 76));
        loginBtn.setBackground(new Color(51, 153, 255));
        regBtn.setForeground(Color.WHITE);
        loginBtn.setForeground(Color.WHITE);
        regBtn.setFont(new Font("Arial", Font.BOLD, 13));
        loginBtn.setFont(new Font("Arial", Font.BOLD, 13));

        regBtn.setBounds(80, 230, 140, 40);
        loginBtn.setBounds(260, 230, 140, 40);

        area = new JTextArea();
        area.setBounds(50, 290, 400, 120);
        area.setFont(new Font("Monospaced", Font.BOLD, 12));
        area.setBackground(new Color(255, 255, 230));
        area.setForeground(new Color(0, 51, 102));
        area.setEditable(false);
        area.setBorder(BorderFactory.createTitledBorder("📋 Registration Status"));

        bg.add(l1); bg.add(t1);
        bg.add(l2); bg.add(t2);
        bg.add(regBtn); bg.add(loginBtn);
        bg.add(area);

        regBtn.addActionListener(this);
        loginBtn.addActionListener(this);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == regBtn) {
            savedUser = t1.getText();
            savedPass = t2.getText();

            area.append("✅ User Registered!\n");
            t1.setText("");
            t2.setText("");
        }

        if(e.getSource() == loginBtn) {
            new LoginForm();
            dispose();
        }
    }
}
