package hotelmanagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {

    JButton roomBtn, customerBtn, bookingBtn;

    public Dashboard() {

        setTitle("🏨 Hotel Management System - Dashboard 📊");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background panel
        JPanel bg = new JPanel();
        bg.setBackground(new Color(230, 250, 255));
        bg.setBounds(0, 0, 600, 500);
        bg.setLayout(null);
        add(bg);

        // Heading
        JLabel heading = new JLabel("✨ Welcome to Hotel Management Dashboard ✨");
        heading.setBounds(60, 20, 480, 50);
        heading.setFont(new Font("Serif", Font.BOLD, 24));
        heading.setForeground(new Color(0, 102, 204));
        bg.add(heading);

        // Subtitle
        JLabel subtitle = new JLabel("Select an option below to manage your hotel:");
        subtitle.setBounds(120, 75, 360, 25);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(new Color(0, 51, 102));
        bg.add(subtitle);

        // -------- BUTTONS ----------
        roomBtn = new JButton("🛏️  Room Management");
        customerBtn = new JButton("👥 Customer Module");
        bookingBtn = new JButton("📅 Booking System");

        roomBtn.setBackground(new Color(255, 153, 51));
        customerBtn.setBackground(new Color(0, 153, 76));
        bookingBtn.setBackground(new Color(51, 153, 255));

        roomBtn.setForeground(Color.WHITE);
        customerBtn.setForeground(Color.WHITE);
        bookingBtn.setForeground(Color.WHITE);

        roomBtn.setFont(new Font("Arial", Font.BOLD, 16));
        customerBtn.setFont(new Font("Arial", Font.BOLD, 16));
        bookingBtn.setFont(new Font("Arial", Font.BOLD, 16));

        roomBtn.setBounds(100, 150, 400, 60);
        customerBtn.setBounds(100, 250, 400, 60);
        bookingBtn.setBounds(100, 350, 400, 60);

        bg.add(roomBtn);
        bg.add(customerBtn);
        bg.add(bookingBtn);

        // Footer note
        JLabel note = new JLabel("💡 Select an option to continue managing the hotel");
        note.setBounds(120, 440, 360, 25);
        note.setForeground(new Color(102, 102, 102));
        note.setFont(new Font("Arial", Font.ITALIC, 11));
        bg.add(note);

        roomBtn.addActionListener(this);
        customerBtn.addActionListener(this);
        bookingBtn.addActionListener(this);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    if (e.getSource() == roomBtn) {
        new RoomManagement();      
    }

    if (e.getSource() == customerBtn) {
        new CustomerModule();       
    }

    if (e.getSource() == bookingBtn) {
        new AdminBookingSystem();   
    }
}

}
