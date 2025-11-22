package hotelmanagement;

import java.awt.event.*;
import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {

    JButton roomBtn, customerBtn, bookingBtn;

    public Dashboard() {

        setTitle("Hotel Dashboard");
        setSize(300, 320);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // -------- BUTTONS ----------
        roomBtn = new JButton("Room Management");
        customerBtn = new JButton("Customer Module");
        bookingBtn = new JButton("Booking System");

        roomBtn.setBounds(50, 50, 200, 40);
        customerBtn.setBounds(50, 110, 200, 40);
        bookingBtn.setBounds(50, 170, 200, 40);

        add(roomBtn);
        add(customerBtn);
        add(bookingBtn);

        roomBtn.addActionListener(this);
        customerBtn.addActionListener(this);
        bookingBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    if (e.getSource() == roomBtn) {
        new RoomManagementPanel();      
    }

    if (e.getSource() == customerBtn) {
        new CustomerModule();       
    }

    if (e.getSource() == bookingBtn) {
        new AdminBookingSystem();   
    }
}

}
