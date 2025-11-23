package hotelmanagement;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class RoomManagement extends JFrame implements ActionListener {

    JTextField txtRoomNo, txtPrice, txtFloor;
    JComboBox<String> cmbType, cmbStatus;
    JCheckBox cbAC, cbWiFi, cbTV;
    JTextArea displayArea;
    JButton btnAdd, btnView, btnUpdate, btnDelete;

    ArrayList<String> roomList = new ArrayList<>();

    RoomManagement() {
        super("🏨 Hotel Management System - Room Management 🛏️");
        setLayout(null);
        setSize(950, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel bg = new JPanel();
        bg.setBackground(new Color(230, 250, 255));
        bg.setBounds(0, 0, 950, 700);
        bg.setLayout(null);
        add(bg);

        JLabel heading = new JLabel("✨  Room Management ✨");
        heading.setBounds(180, 10, 600, 40);
        heading.setFont(new Font("Serif", Font.BOLD, 26));
        heading.setForeground(new Color(0, 102, 204));
        bg.add(heading);

        // -------- Room Input Fields --------
        JLabel lblRoomNo = new JLabel("Room Number:");
        JLabel lblType = new JLabel("Room Type:");
        JLabel lblPrice = new JLabel("Price:");
        JLabel lblStatus = new JLabel("Status:");
        JLabel lblFloor = new JLabel("Floor:");
        JLabel lblAmenities = new JLabel("Amenities:");

        lblRoomNo.setBounds(50, 70, 120, 30);
        lblType.setBounds(50, 110, 120, 30);
        lblPrice.setBounds(50, 150, 120, 30);
        lblStatus.setBounds(450, 70, 120, 30);
        lblFloor.setBounds(450, 110, 120, 30);
        lblAmenities.setBounds(450, 150, 120, 30);

        txtRoomNo = new JTextField();
        txtPrice = new JTextField();
        txtFloor = new JTextField();

        cmbType = new JComboBox<>(new String[]{"Single", "Double", "Deluxe", "Suite", "Presidential"});
        cmbStatus = new JComboBox<>(new String[]{"Available", "Occupied", "Maintenance"});

        txtRoomNo.setBounds(170, 70, 120, 30);
        cmbType.setBounds(170, 110, 120, 30);
        txtPrice.setBounds(170, 150, 120, 30);
        cmbStatus.setBounds(550, 70, 120, 30);
        txtFloor.setBounds(550, 110, 120, 30);

        cbAC = new JCheckBox("AC");
        cbWiFi = new JCheckBox("WiFi");
        cbTV = new JCheckBox("TV");
        cbAC.setBackground(new Color(230, 250, 255));
        cbWiFi.setBackground(new Color(230, 250, 255));
        cbTV.setBackground(new Color(230, 250, 255));

        cbAC.setBounds(550, 150, 60, 30);
        cbWiFi.setBounds(620, 150, 70, 30);
        cbTV.setBounds(700, 150, 60, 30);

        bg.add(lblRoomNo); bg.add(lblType); bg.add(lblPrice);
        bg.add(lblStatus); bg.add(lblFloor); bg.add(lblAmenities);
        bg.add(txtRoomNo); bg.add(cmbType); bg.add(txtPrice);
        bg.add(cmbStatus); bg.add(txtFloor);
        bg.add(cbAC); bg.add(cbWiFi); bg.add(cbTV);

        // -------- Buttons --------
        btnAdd = new JButton("➕ Add Room");
        btnView = new JButton("👁️ View All");
        btnUpdate = new JButton("✏️ Update Room");
        btnDelete = new JButton("🗑️ Delete Room");

        btnAdd.setBackground(new Color(0, 153, 76));
        btnView.setBackground(new Color(51, 153, 255));
        btnUpdate.setBackground(new Color(255, 153, 51));
        btnDelete.setBackground(new Color(204, 0, 0));

        btnAdd.setForeground(Color.WHITE);
        btnView.setForeground(Color.WHITE);
        btnUpdate.setForeground(Color.WHITE);
        btnDelete.setForeground(Color.WHITE);

        btnAdd.setBounds(50, 200, 150, 35);
        btnView.setBounds(220, 200, 150, 35);
        btnUpdate.setBounds(390, 200, 150, 35);
        btnDelete.setBounds(560, 200, 150, 35);

        btnAdd.addActionListener(this);
        btnView.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);

        bg.add(btnAdd); bg.add(btnView); bg.add(btnUpdate); bg.add(btnDelete);

        // -------- Display Area --------
        displayArea = new JTextArea();
        displayArea.setBounds(50, 250, 850, 380);
        displayArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        displayArea.setBackground(new Color(255, 255, 230));
        displayArea.setForeground(new Color(0, 51, 102));
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createTitledBorder("📋 Room Details"));
        bg.add(displayArea);

        JLabel note = new JLabel("🌟 Demo Version: Manages rooms in memory with sample data.");
        note.setBounds(100, 640, 800, 30);
        note.setForeground(new Color(102, 102, 102));
        bg.add(note);

        // Load fake data
        loadFakeData();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    void loadFakeData() {
        roomList.add("101 | Single | ₹1500 | Available | Floor-1 | AC, WiFi");
        roomList.add("201 | Double | ₹2500 | Available | Floor-2 | AC, WiFi, TV");
        roomList.add("301 | Deluxe | ₹4000 | Occupied | Floor-3 | AC, WiFi, TV");
        roomList.add("401 | Suite | ₹7000 | Available | Floor-4 | AC, WiFi, TV");
        roomList.add("501 | Presidential | ₹12000 | Maintenance | Floor-5 | AC, WiFi, TV");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {
            if (txtRoomNo.getText().isEmpty() || txtPrice.getText().isEmpty() || txtFloor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            String amenities = "";
            if (cbAC.isSelected()) amenities += "AC, ";
            if (cbWiFi.isSelected()) amenities += "WiFi, ";
            if (cbTV.isSelected()) amenities += "TV, ";
            if (amenities.endsWith(", ")) amenities = amenities.substring(0, amenities.length() - 2);

            String room = txtRoomNo.getText() + " | " + cmbType.getSelectedItem() + " | ₹" 
                    + txtPrice.getText() + " | " + cmbStatus.getSelectedItem() 
                    + " | Floor-" + txtFloor.getText() + " | " + amenities;

            roomList.add(room);
            displayArea.setText("✅ Room Added Successfully!\n" + room);
            clearFields();

        } else if (e.getSource() == btnView) {
            displayArea.setText("🛏️ All Rooms:\n\n");
            if (roomList.isEmpty()) {
                displayArea.append("❌ No rooms available!\n");
            } else {
                for (String r : roomList) {
                    displayArea.append("🏨 " + r + "\n");
                }
            }

        } else if (e.getSource() == btnUpdate) {
            try {
                int roomNo = Integer.parseInt(txtRoomNo.getText());
                boolean found = false;

                for (int i = 0; i < roomList.size(); i++) {
                    String room = roomList.get(i);
                    if (room.startsWith(roomNo + " |")) {
                        String amenities = "";
                        if (cbAC.isSelected()) amenities += "AC, ";
                        if (cbWiFi.isSelected()) amenities += "WiFi, ";
                        if (cbTV.isSelected()) amenities += "TV, ";
                        if (amenities.endsWith(", ")) amenities = amenities.substring(0, amenities.length() - 2);

                        String updatedRoom = txtRoomNo.getText() + " | " + cmbType.getSelectedItem() + " | ₹" 
                                + txtPrice.getText() + " | " + cmbStatus.getSelectedItem() 
                                + " | Floor-" + txtFloor.getText() + " | " + amenities;

                        roomList.set(i, updatedRoom);
                        displayArea.setText("✅ Room Updated Successfully!\n" + updatedRoom);
                        found = true;
                        clearFields();
                        break;
                    }
                }

                if (!found) {
                    displayArea.setText("❌ Room not found!");
                }

            } catch (Exception ex) {
                displayArea.setText("⚠️ Please enter a valid room number!");
            }

        } else if (e.getSource() == btnDelete) {
            try {
                int roomNo = Integer.parseInt(txtRoomNo.getText());
                boolean found = false;

                for (int i = 0; i < roomList.size(); i++) {
                    String room = roomList.get(i);
                    if (room.startsWith(roomNo + " |")) {
                        roomList.remove(i);
                        displayArea.setText("🗑️ Room " + roomNo + " deleted successfully!");
                        found = true;
                        clearFields();
                        break;
                    }
                }

                if (!found) {
                    displayArea.setText("❌ Room not found!");
                }

            } catch (Exception ex) {
                displayArea.setText("⚠️ Please enter a valid room number!");
            }
        }
    }

    void clearFields() {
        txtRoomNo.setText("");
        txtPrice.setText("");
        txtFloor.setText("");
        cbAC.setSelected(false);
        cbWiFi.setSelected(false);
        cbTV.setSelected(false);
        cmbType.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new RoomManagement();
    }
}