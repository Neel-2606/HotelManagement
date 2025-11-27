package hotelmanagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegistrationForm extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField t1;
    JPasswordField t2;
    JButton regBtn, loginBtn, clearBtn, resetBtn, togglePassBtn;
    JTextArea area;

    static String savedUser = "";
    static String savedPass = "";

    boolean passVisible = false;

    public RegistrationForm() {

        setTitle("🏨 Hotel Management System - Register 📝");
        setSize(520, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Background
        JPanel bg = new JPanel();
        bg.setBackground(new Color(230, 250, 255));
        bg.setBounds(0, 0, 520, 520);
        bg.setLayout(null);
        add(bg);

        // Heading
        JLabel heading = new JLabel("✨ Create New Account ✨");
        heading.setBounds(90, 20, 350, 40);
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

        // Password toggle button
        togglePassBtn = new JButton("👁️");
        togglePassBtn.setBounds(405, 150, 50, 35);
        togglePassBtn.addActionListener(this);
        bg.add(togglePassBtn);

        // Buttons
        regBtn = new JButton("✅ Register");
        loginBtn = new JButton("🔐 Go to Login");
        clearBtn = new JButton("🧹 Clear Fields");
        resetBtn = new JButton("⚠️ Reset System");

        regBtn.setBackground(new Color(0, 153, 76));
        loginBtn.setBackground(new Color(51, 153, 255));
        clearBtn.setBackground(new Color(255, 153, 51));
        resetBtn.setBackground(new Color(204, 0, 0));

        regBtn.setForeground(Color.WHITE);
        loginBtn.setForeground(Color.WHITE);
        clearBtn.setForeground(Color.WHITE);
        resetBtn.setForeground(Color.WHITE);

        regBtn.setFont(new Font("Arial", Font.BOLD, 13));
        loginBtn.setFont(new Font("Arial", Font.BOLD, 13));
        clearBtn.setFont(new Font("Arial", Font.BOLD, 13));
        resetBtn.setFont(new Font("Arial", Font.BOLD, 13));

        regBtn.setBounds(80, 230, 140, 40);
        loginBtn.setBounds(260, 230, 140, 40);
        clearBtn.setBounds(80, 280, 140, 40);
        resetBtn.setBounds(260, 280, 140, 40);

        // Text area
        area = new JTextArea();
        area.setBounds(50, 340, 420, 150);
        area.setFont(new Font("Monospaced", Font.BOLD, 12));
        area.setBackground(new Color(255, 255, 230));
        area.setForeground(new Color(0, 51, 102));
        area.setEditable(false);
        area.setBorder(BorderFactory.createTitledBorder("📋 Registration Status"));

        // Add components
        bg.add(l1); bg.add(t1);
        bg.add(l2); bg.add(t2);
        bg.add(regBtn); bg.add(loginBtn);
        bg.add(clearBtn); bg.add(resetBtn);
        bg.add(area);

        // Listeners
        regBtn.addActionListener(this);
        loginBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        resetBtn.addActionListener(this);

        setVisible(true);
        setLocationRelativeTo(null);
    }


    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == regBtn) {
            String username = t1.getText().trim();
            String password = new String(t2.getPassword()).trim();

            if(username.isEmpty() || password.isEmpty()) {
                area.append("❌ Please fill all fields!\n");
                return;
            }

            savedUser = username;
            savedPass = password;

            area.append("✅ User Registered Successfully!\n");
            area.append("📝 Redirecting to Login...\n");

            Timer timer = new Timer(1000, evt -> {
                new LoginForm();
                dispose();
            });
            timer.setRepeats(false);
            timer.start();
        }

        if(e.getSource() == loginBtn) {
            new LoginForm();
            dispose();
        }

        if(e.getSource() == clearBtn) {
            t1.setText("");
            t2.setText("");
            area.append("🧹 Cleared all fields!\n");
        }

        if(e.getSource() == resetBtn) {
            savedUser = "";
            savedPass = "";
            area.append("⚠️ System Reset: All saved accounts cleared!\n");
        }

        if(e.getSource() == togglePassBtn) {
            passVisible = !passVisible;
            t2.setEchoChar(passVisible ? (char)0 : '•');
        }
    }
}
