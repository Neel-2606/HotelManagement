package hotelmanagement;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class AdminBookingSystem extends JFrame implements ActionListener {

    JTextField txtBookingId, txtCustomerName, txtCheckIn, txtCheckOut;
    JComboBox<String> cmbStatus;
    JTextArea displayArea;
    JButton btnAdd, btnView, btnUpdate, btnCancel, btnAvailability;

    ArrayList<String> bookingList = new ArrayList<>();

    AdminBookingSystem() {
        super("🏨 Hotel Management System - Admin Booking System 📅");
        setLayout(null);
        setSize(950, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel bg = new JPanel();
        bg.setBackground(new Color(230, 250, 255));
        bg.setBounds(0, 0, 950, 700);
        bg.setLayout(null);
        add(bg);

        JLabel heading = new JLabel("✨  Admin Booking System ✨");
        heading.setBounds(180, 10, 600, 40);
        heading.setFont(new Font("Serif", Font.BOLD, 26));
        heading.setForeground(new Color(0, 102, 204));
        bg.add(heading);

        // -------- Booking Input Fields --------
        JLabel lblBookingId = new JLabel("Booking ID:");
        JLabel lblCustomer = new JLabel("Customer Name:");
        JLabel lblCheckIn = new JLabel("Check-In Date:");
        JLabel lblCheckOut = new JLabel("Check-Out Date:");
        JLabel lblStatus = new JLabel("Status:");

        lblBookingId.setBounds(50, 70, 120, 30);
        lblCustomer.setBounds(50, 110, 120, 30);
        lblCheckIn.setBounds(50, 150, 120, 30);
        lblCheckOut.setBounds(450, 70, 120, 30);
        lblStatus.setBounds(450, 110, 120, 30);

        txtBookingId = new JTextField();
        txtCustomerName = new JTextField();
        txtCheckIn = new JTextField("2025-11-23");
        txtCheckOut = new JTextField("2025-11-25");

        cmbStatus = new JComboBox<>(new String[]{"Booked", "Checked-In", "Checked-Out", "Cancelled"});

        txtBookingId.setBounds(170, 70, 120, 30);
        txtCustomerName.setBounds(170, 110, 120, 30);
        txtCheckIn.setBounds(170, 150, 120, 30);
        txtCheckOut.setBounds(570, 70, 120, 30);
        cmbStatus.setBounds(570, 110, 120, 30);

        bg.add(lblBookingId); bg.add(lblCustomer); bg.add(lblCheckIn);
        bg.add(lblCheckOut); bg.add(lblStatus);
        bg.add(txtBookingId); bg.add(txtCustomerName); bg.add(txtCheckIn);
        bg.add(txtCheckOut); bg.add(cmbStatus);

        // -------- Buttons --------
        btnAdd = new JButton("➕ Add Booking");
        btnView = new JButton("👁️ View All");
        btnUpdate = new JButton("✏️ Update Status");
        btnCancel = new JButton("❌ Cancel Booking");
        btnAvailability = new JButton("📊 Availability");

        btnAdd.setBackground(new Color(0, 153, 76));
        btnView.setBackground(new Color(51, 153, 255));
        btnUpdate.setBackground(new Color(255, 153, 51));
        btnCancel.setBackground(new Color(204, 0, 0));
        btnAvailability.setBackground(new Color(102, 51, 153));

        btnAdd.setForeground(Color.WHITE);
        btnView.setForeground(Color.WHITE);
        btnUpdate.setForeground(Color.WHITE);
        btnCancel.setForeground(Color.WHITE);
        btnAvailability.setForeground(Color.WHITE);

        btnAdd.setBounds(50, 200, 160, 35);
        btnView.setBounds(230, 200, 160, 35);
        btnUpdate.setBounds(410, 200, 160, 35);
        btnCancel.setBounds(590, 200, 160, 35);
        btnAvailability.setBounds(350, 250, 200, 35);

        btnAdd.addActionListener(this);
        btnView.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnCancel.addActionListener(this);
        btnAvailability.addActionListener(this);

        bg.add(btnAdd); bg.add(btnView); bg.add(btnUpdate);
        bg.add(btnCancel); bg.add(btnAvailability);

        // -------- Display Area --------
        displayArea = new JTextArea();
        displayArea.setBounds(50, 300, 850, 350);
        displayArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        displayArea.setBackground(new Color(255, 255, 230));
        displayArea.setForeground(new Color(0, 51, 102));
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createTitledBorder("📋 Booking Details"));
        bg.add(displayArea);

        JLabel note = new JLabel("🌟 Demo Version: Manages bookings in memory with sample data.");
        note.setBounds(100, 660, 800, 30);
        note.setForeground(new Color(102, 102, 102));
        bg.add(note);

        // Load fake data
        loadFakeData();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    void loadFakeData() {
        bookingList.add("1 | Amit Kumar | 2025-11-23 | 2025-11-25 | Booked");
        bookingList.add("2 | Riya Sharma | 2025-11-24 | 2025-11-26 | Booked");
        bookingList.add("3 | Priya Singh | 2025-11-25 | 2025-11-27 | Checked-In");
        bookingList.add("4 | Rahul Verma | 2025-11-26 | 2025-11-28 | Booked");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {
            if (txtBookingId.getText().isEmpty() || txtCustomerName.getText().isEmpty() 
                || txtCheckIn.getText().isEmpty() || txtCheckOut.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            String booking = txtBookingId.getText() + " | " + txtCustomerName.getText() 
                    + " | " + txtCheckIn.getText() + " | " + txtCheckOut.getText() 
                    + " | " + cmbStatus.getSelectedItem();

            bookingList.add(booking);
            displayArea.setText("✅ Booking Added Successfully!\n" + booking);
            clearFields();

        } else if (e.getSource() == btnView) {
            displayArea.setText("📅 All Bookings:\n\n");
            if (bookingList.isEmpty()) {
                displayArea.append("❌ No bookings available!\n");
            } else {
                for (String b : bookingList) {
                    displayArea.append("🏨 " + b + "\n");
                }
            }

        } else if (e.getSource() == btnUpdate) {
            try {
                int bookingId = Integer.parseInt(txtBookingId.getText());
                boolean found = false;

                for (int i = 0; i < bookingList.size(); i++) {
                    String booking = bookingList.get(i);
                    if (booking.startsWith(bookingId + " |")) {
                        String[] parts = booking.split(" \\| ");
                        String updatedBooking = parts[0] + " | " + parts[1] + " | " 
                                + parts[2] + " | " + parts[3] + " | " + cmbStatus.getSelectedItem();

                        bookingList.set(i, updatedBooking);
                        displayArea.setText("✅ Booking Status Updated!\n" + updatedBooking);
                        found = true;
                        clearFields();
                        break;
                    }
                }

                if (!found) {
                    displayArea.setText("❌ Booking not found!");
                }

            } catch (Exception ex) {
                displayArea.setText("⚠️ Please enter a valid booking ID!");
            }

        } else if (e.getSource() == btnCancel) {
            try {
                int bookingId = Integer.parseInt(txtBookingId.getText());
                boolean found = false;

                for (int i = 0; i < bookingList.size(); i++) {
                    String booking = bookingList.get(i);
                    if (booking.startsWith(bookingId + " |")) {
                        String[] parts = booking.split(" \\| ");
                        String cancelledBooking = parts[0] + " | " + parts[1] + " | " 
                                + parts[2] + " | " + parts[3] + " | Cancelled";

                        bookingList.set(i, cancelledBooking);
                        displayArea.setText("🚫 Booking Cancelled!\n" + cancelledBooking);
                        found = true;
                        clearFields();
                        break;
                    }
                }

                if (!found) {
                    displayArea.setText("❌ Booking not found!");
                }

            } catch (Exception ex) {
                displayArea.setText("⚠️ Please enter a valid booking ID!");
            }

        } else if (e.getSource() == btnAvailability) {
            displayArea.setText("📊 Room Availability (Next 30 Days):\n\n");
            displayArea.append("Date Range: 2025-11-23 to 2025-12-23\n");
            displayArea.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");
            
            displayArea.append("Week 1 (Nov 23-29): ✅ Available\n");
            displayArea.append("Week 2 (Nov 30-Dec 6): ✅ Available\n");
            displayArea.append("Week 3 (Dec 7-13): ⚠️ Partially Booked\n");
            displayArea.append("Week 4 (Dec 14-20): ✅ Available\n");
            displayArea.append("Week 5 (Dec 21-23): ✅ Available\n\n");
            
            displayArea.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
            displayArea.append("Total Rooms: 50 | Booked: " + bookingList.size() + " | Available: " + (50 - bookingList.size()));
        }
    }

    void clearFields() {
        txtBookingId.setText("");
        txtCustomerName.setText("");
        txtCheckIn.setText("2025-11-23");
        txtCheckOut.setText("2025-11-25");
        cmbStatus.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new AdminBookingSystem();
    }
}
