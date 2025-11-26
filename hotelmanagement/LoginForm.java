package hotelmanagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginForm extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField t1, t2;
    JButton loginBtn, regBtn;
    JTextArea area;

    public LoginForm() {

        setTitle("🏨 Hotel Management System - Login 🔐");
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
        JLabel heading = new JLabel("✨ User Login ✨");
        heading.setBounds(100, 20, 300, 40);
        heading.setFont(new Font("Serif", Font.BOLD, 28));
        heading.setForeground(new Color(0, 102, 204));
        bg.add(heading);

        l1 = new JLabel("Username:");
        l2 = new JLabel("Password:");

        l1.setFont(new Font("Arial", Font.BOLD, 14));
        l2.setFont(new Font("Arial", Font.BOLD, 14));
        l1.setForeground(new Color(0, 51, 102));
        l2.setForeground(new Color(0, 51, 102));

        t1 = new JTextField();
        t2 = new JPasswordField();

        l1.setBounds(80, 90, 120, 30);
        t1.setBounds(200, 90, 180, 35);

        l2.setBounds(80, 150, 120, 30);
        t2.setBounds(200, 150, 180, 35);

        t1.setFont(new Font("Arial", Font.PLAIN, 12));
        t2.setFont(new Font("Arial", Font.PLAIN, 12));

        loginBtn = new JButton("🔓 Login");
        regBtn = new JButton("📝 Register");

        loginBtn.setBackground(new Color(51, 153, 255));
        regBtn.setBackground(new Color(102, 205, 170));
        loginBtn.setForeground(Color.WHITE);
        regBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 13));
        regBtn.setFont(new Font("Arial", Font.BOLD, 13));

        loginBtn.setBounds(80, 230, 140, 40);
        regBtn.setBounds(260, 230, 140, 40);

        area = new JTextArea();
        area.setBounds(50, 290, 400, 120);
        area.setFont(new Font("Monospaced", Font.BOLD, 12));
        area.setBackground(new Color(255, 255, 230));
        area.setForeground(new Color(0, 51, 102));
        area.setEditable(false);
        area.setBorder(BorderFactory.createTitledBorder("📋 Login Status"));

        bg.add(l1); bg.add(t1);
        bg.add(l2); bg.add(t2);
        bg.add(loginBtn); bg.add(regBtn);
        bg.add(area);

        loginBtn.addActionListener(this);
        regBtn.addActionListener(this);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == loginBtn) {
            String u = t1.getText().trim();
            String p = t2.getText().trim();

            if(u.isEmpty() || p.isEmpty()) {
                area.append("❌ Please enter username and password!\n");
                return;
            }

           
            if(RegistrationForm.savedUser.isEmpty() || RegistrationForm.savedPass.isEmpty()) {
                area.append("❌ No user registered! Please register first.\n");
                return;
            }

            
            if(u.equals(RegistrationForm.savedUser) &&
               p.equals(RegistrationForm.savedPass)) {

                area.append("✅ Login Successful!\n");
                area.append("🏨 Opening Dashboard...\n");
                new Dashboard();
                dispose();
            }
            else {
                area.append("❌ Invalid username or password!\n");
            }
        }

        if(e.getSource() == regBtn) {
            new RegistrationForm();
            dispose();
        }
    }
}
