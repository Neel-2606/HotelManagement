package hotelmanagement;

import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class Booking {
    private int bookingId;
    private String customerName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String status;

    public Booking(int bookingId, String customerName, LocalDate checkIn, LocalDate checkOut, String status) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }

    public int getBookingId() { return bookingId; }
    public String getCustomerName() { return customerName; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}

// ✔ No DB – just sample data
class BookingDAO {

    HashMap<Integer, Booking> store = new HashMap<>();

    public BookingDAO() {
        // sample data
        store.put(1, new Booking(1, "Amit", LocalDate.now(), LocalDate.now().plusDays(2), "Booked"));
        store.put(2, new Booking(2, "Riya", LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), "Booked"));
    }

    public HashMap<Integer, Booking> getAllBookings() {
        return store;
    }

    public void updateStatus(int id, String status) {
        store.get(id).setStatus(status);
    }

    public boolean isOverlapping(LocalDate in, LocalDate out) {
        for (Booking b : store.values()) {
            LocalDate existIn = b.getCheckIn();
            LocalDate existOut = b.getCheckOut();

            if (!(out.isBefore(existIn) || in.isAfter(existOut))) {
                return true;
            }
        }
        return false;
    }
}

public class AdminBookingSystem extends JFrame {

    JTable table;
    DefaultTableModel model;
    BookingDAO dao;

    public AdminBookingSystem() {
        dao = new BookingDAO();

        setTitle("Booking Management (Admin Panel)");
        setSize(850, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "Name", "Check-In", "Check-Out", "Status"}, 0);
        table = new JTable(model);

        loadBookings();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnCheckIn = new JButton("Check-In");
        JButton btnCheckOut = new JButton("Check-Out");
        JButton btnCancel = new JButton("Cancel");
        JButton btnAvail = new JButton("Availability (30 Days)");

        btnPanel.add(btnCheckIn);
        btnPanel.add(btnCheckOut);
        btnPanel.add(btnCancel);
        btnPanel.add(btnAvail);

        add(btnPanel, BorderLayout.SOUTH);

        btnCheckIn.addActionListener(e -> updateBooking("Checked-In"));
        btnCheckOut.addActionListener(e -> updateBooking("Checked-Out"));
        btnCancel.addActionListener(e -> updateBooking("Cancelled"));
        btnAvail.addActionListener(e -> showAvailabilityGrid());

        setVisible(true);
    }

    void loadBookings() {
        model.setRowCount(0);

        for (Booking b : dao.getAllBookings().values()) {
            model.addRow(new Object[]{
                    b.getBookingId(),
                    b.getCustomerName(),
                    b.getCheckIn(),
                    b.getCheckOut(),
                    b.getStatus()
            });
        }
    }

    void updateBooking(String status) {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a booking!");
            return;
        }

        int id = Integer.parseInt(model.getValueAt(row, 0).toString());

        dao.updateStatus(id, status);
        JOptionPane.showMessageDialog(this, "Updated!");
        loadBookings();
    }

    void showAvailabilityGrid() {
        JFrame f = new JFrame("Room Availability (Next 30 Days)");
        f.setSize(750, 200);

        String[] days = new String[30];
        String[][] data = new String[1][30];

        LocalDate today = LocalDate.now();

        for (int i = 0; i < 30; i++) {
            days[i] = today.plusDays(i).toString();
            data[0][i] = "Available";
        }

        JTable availTable = new JTable(data, days);
        f.add(new JScrollPane(availTable));
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new AdminBookingSystem();
    }
}
