import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.util.ArrayList;

public class CustomerModule extends JFrame implements ActionListener {

    JTextField txtCheckIn, txtCheckOut;
    JRadioButton rbDeluxe, rbSuite, rbStandard;
    ButtonGroup roomGroup;
    JTextField txtName, txtEmail, txtPhone;
    JTextField txtBookingId;
    JTextArea displayArea;
    JButton btnSearch, btnBook, btnView, btnCancel;

    ArrayList<String> roomList = new ArrayList<>();
    ArrayList<String> bookingList = new ArrayList<>();

    
    ArrayList<Integer> bookingIds = new ArrayList<>();

    CustomerModule () {

        super("🏨 Hotel Management System - Customer Module 🧳");
        setLayout(null);
        setSize(950, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel bg = new JPanel();
        bg.setBackground(new Color(230, 250, 255));
        bg.setBounds(0, 0, 950, 700);
        bg.setLayout(null);
        add(bg);

        JLabel heading = new JLabel("✨ Customer Module ✨");
        heading.setBounds(180, 10, 600, 40);
        heading.setFont(new Font("Serif", Font.BOLD, 26));
        heading.setForeground(new Color(0, 102, 204));
        bg.add(heading);

        JLabel lbl1 = new JLabel("Check-In Date:");
        JLabel lbl2 = new JLabel("Check-Out Date:");
        JLabel lbl3 = new JLabel("Room Type:");

        lbl1.setBounds(50, 70, 150, 30);
        lbl2.setBounds(300, 70, 150, 30);
        lbl3.setBounds(550, 70, 150, 30);

        txtCheckIn = new JTextField("2025-11-01");
        txtCheckOut = new JTextField("2025-11-03");

        txtCheckIn.setBounds(150, 70, 130, 30);
        txtCheckOut.setBounds(420, 70, 120, 30);

        rbDeluxe = new JRadioButton("Deluxe");
        rbSuite = new JRadioButton("Suite");
        rbStandard = new JRadioButton("Standard");

        rbDeluxe.setBounds(640, 65, 100, 20);
        rbSuite.setBounds(640, 85, 100, 20);
        rbStandard.setBounds(640, 105, 100, 20);

        roomGroup = new ButtonGroup();
        roomGroup.add(rbDeluxe);
        roomGroup.add(rbSuite);
        roomGroup.add(rbStandard);

        btnSearch = new JButton("🔍 Search Rooms");
        btnSearch.setBackground(new Color(51, 153, 255));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBounds(780, 70, 140, 30);
        btnSearch.addActionListener(this);

        bg.add(lbl1); bg.add(lbl2); bg.add(lbl3);
        bg.add(txtCheckIn); bg.add(txtCheckOut);
        bg.add(rbDeluxe); bg.add(rbSuite); bg.add(rbStandard);
        bg.add(btnSearch);

        displayArea = new JTextArea();
        displayArea.setBounds(50, 140, 850, 160);
        displayArea.setFont(new Font("Monospaced", Font.BOLD, 16));
        displayArea.setBackground(new Color(255, 255, 230));
        displayArea.setForeground(new Color(0, 51, 102));
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createTitledBorder("📋 Room / Booking Details"));
        bg.add(displayArea);

        JLabel nameLbl = new JLabel("Name:");
        JLabel emailLbl = new JLabel("Email:");
        JLabel phoneLbl = new JLabel("Phone:");

        nameLbl.setBounds(50, 320, 80, 25);
        emailLbl.setBounds(50, 360, 80, 25);
        phoneLbl.setBounds(50, 400, 80, 25);

        txtName = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();

        txtName.setBounds(120, 320, 200, 25);
        txtEmail.setBounds(120, 360, 200, 25);
        txtPhone.setBounds(120, 400, 200, 25);

        btnBook = new JButton("💾 Book Room");
        btnBook.setBounds(350, 350, 150, 35);
        btnBook.setBackground(new Color(0, 153, 76));
        btnBook.setForeground(Color.WHITE);
        btnBook.addActionListener(this);

        bg.add(nameLbl); bg.add(emailLbl); bg.add(phoneLbl);
        bg.add(txtName); bg.add(txtEmail); bg.add(txtPhone);
        bg.add(btnBook);

        JLabel lbl4 = new JLabel("Booking ID:");
        lbl4.setBounds(50, 460, 100, 25);
        txtBookingId = new JTextField();
        txtBookingId.setBounds(150, 460, 120, 25);

        btnView = new JButton("👁️ View My Bookings");
        btnCancel = new JButton("❌ Cancel Booking");
        btnView.setBounds(300, 460, 200, 30);
        btnCancel.setBounds(520, 460, 200, 30);

        btnView.setBackground(new Color(255, 153, 51));
        btnCancel.setBackground(new Color(204, 0, 0));
        btnView.setForeground(Color.WHITE);
        btnCancel.setForeground(Color.WHITE);

        btnView.addActionListener(this);
        btnCancel.addActionListener(this);

        bg.add(lbl4); bg.add(txtBookingId);
        bg.add(btnView); bg.add(btnCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSearch) {

            displayArea.setText("Available Rooms:\n");
            roomList.clear();

            roomList.add("R101 - Deluxe Room - ₹3000/night");
            roomList.add("R102 - Suite Room - ₹4500/night");
            roomList.add("R103 - Standard Room - ₹2000/night");

            for (String r : roomList)
                displayArea.append("🛏️ " + r + "\n");
        }

        else if (e.getSource() == btnBook) {

            if (!txtPhone.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(this,
                        "Please enter NUMERIC values only in Phone Number!");
                return;
            }

            if (txtName.getText().isEmpty() || txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill your details first!");
                return;
            }

            String type = "";
            if (rbDeluxe.isSelected()) type = "Deluxe";
            else if (rbSuite.isSelected()) type = "Suite";
            else if (rbStandard.isSelected()) type = "Standard";
            else {
                JOptionPane.showMessageDialog(this, "Please select a room type!");
                return;
            }

            
            int newId = bookingIds.size() + 1;
            bookingIds.add(newId);

            String booking = "BookingID#" + newId
                    + " | " + txtName.getText()
                    + " | " + txtEmail.getText()
                    + " | " + type;

            bookingList.add(booking);
            displayArea.setText("✅ Booking Successful!\n" + booking);

            try {
                FileWriter fw = new FileWriter("Booking_" + newId + ".txt");
                fw.write("========= BOOKING CONFIRMATION =========\n");
                fw.write("Booking ID: " + newId + "\n");
                fw.write("Name: " + txtName.getText() + "\n");
                fw.write("Email: " + txtEmail.getText() + "\n");
                fw.write("Phone: " + txtPhone.getText() + "\n");
                fw.write("Room Type: " + type + "\n");
                fw.write("Check-In: " + txtCheckIn.getText() + "\n");
                fw.write("Check-Out: " + txtCheckOut.getText() + "\n");
                fw.write("Status: CONFIRMED\n");
                fw.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "File write error!");
            }
        }

        else if (e.getSource() == btnView) {
            displayArea.setText("Your Bookings:\n");
            if (bookingList.isEmpty()) {
                displayArea.append("❌ No bookings yet!\n");
            } else {
                for (String b : bookingList)
                    displayArea.append("📜 " + b + "\n");
            }
        }

        else if (e.getSource() == btnCancel) {
            try {
                int id = Integer.parseInt(txtBookingId.getText());

                
                if (!bookingIds.contains(id)) {
                    displayArea.setText("❌ Invalid Booking ID!");
                    return;
                }

                int index = bookingIds.indexOf(id);

                bookingList.set(index, bookingList.get(index) + " | CANCELLED");
                displayArea.setText("🚫 Booking ID " + id + " cancelled successfully!");

            } catch (Exception ex) {
                displayArea.setText("⚠️ Please enter a valid number!");
            }
        }
    }

    public static void main(String[] args) {
        new CustomerModule ().setVisible(true);
    }
}
