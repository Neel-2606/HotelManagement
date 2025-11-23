package hotelmanagement;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

class Invoice {
    String customer;
    String mobile;
    int nights;
    double rate;
    double addons;
    double cgst;
    double sgst;
    double total;

    public Invoice(String customer, String mobile, int nights, double rate, double addons) {
        this.customer = customer;
        this.mobile = mobile;
        this.nights = nights;
        this.rate = rate;
        this.addons = addons;

        double roomAmount = rate * nights;
        double gross = roomAmount + addons;

        this.cgst = gross * 0.09;
        this.sgst = gross * 0.09;
        this.total = gross + cgst + sgst;
    }

    public String getInvoiceDetails() {
        return  "******** HOTEL INVOICE ********\n" +
                "Customer Name  : " + customer +
                "\nMobile Number  : " + mobile +
                "\nNights         : " + nights +
                "\nRoom Rate      : ₹" + rate +
                "\nAdd-Ons        : ₹" + addons +
                "\nCGST (9%)      : ₹" + cgst +
                "\nSGST (9%)      : ₹" + sgst +
                "\n--------------------------------" +
                "\nTOTAL AMOUNT   : ₹" + total +
                "\n**\n";
    }
}

public class BillingFrame extends JFrame implements ActionListener {

    JTextField txtName, txtMobile, txtNights, txtRate;
    JCheckBox cbBeverage, cbLaundry, cbFood, cbWifi, cbExtraBed;
    JButton btnGenerate, btnSave;
    JTextArea area;

    public BillingFrame() {

        super("🏨 Hotel Billing & Reports");
        setLayout(null);
        setSize(950, 750);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Background Panel same as CustomerModule
        JPanel bg = new JPanel();
        bg.setBackground(new Color(230, 250, 255));
        bg.setBounds(0, 0, 950, 750);
        bg.setLayout(null);
        add(bg);

        // Heading (same style as CustomerModule)
        JLabel heading = new JLabel("✨  Billing & Invoice Generator ✨");
        heading.setBounds(180, 10, 600, 40);
        heading.setFont(new Font("Serif", Font.BOLD, 26));
        heading.setForeground(new Color(0, 102, 204));
        bg.add(heading);

        // LABELS
        JLabel nameLbl = new JLabel("Customer Name:");
        JLabel mobileLbl = new JLabel("Mobile Number:");
        JLabel nightsLbl = new JLabel("Nights:");
        JLabel rateLbl = new JLabel("Room Rate (₹):");
        JLabel addonsLbl = new JLabel("Add-On Services:");

        nameLbl.setBounds(50, 70, 200, 30);
        mobileLbl.setBounds(50, 120, 200, 30);
        nightsLbl.setBounds(50, 170, 200, 30);
        rateLbl.setBounds(50, 220, 200, 30);
        addonsLbl.setBounds(50, 270, 200, 30);

        txtName = new JTextField();
        txtMobile = new JTextField();
        txtNights = new JTextField();
        txtRate = new JTextField();

        txtName.setBounds(220, 70, 200, 30);
        txtMobile.setBounds(220, 120, 200, 30);
        txtNights.setBounds(220, 170, 200, 30);
        txtRate.setBounds(220, 220, 200, 30);

        bg.add(nameLbl); bg.add(txtName);
        bg.add(mobileLbl); bg.add(txtMobile);
        bg.add(nightsLbl); bg.add(txtNights);
        bg.add(rateLbl); bg.add(txtRate);
        bg.add(addonsLbl);

        // ADD-ON CHECKBOXES (aligned like Customer UI style)
        cbBeverage = new JCheckBox("Beverages (₹150)");
        cbLaundry = new JCheckBox("Laundry (₹300)");
        cbFood = new JCheckBox("Food Service (₹500)");
        cbWifi = new JCheckBox("WiFi (₹100)");
        cbExtraBed = new JCheckBox("Extra Bed (₹400)");

        cbBeverage.setBounds(50, 310, 200, 25);
        cbLaundry.setBounds(50, 340, 200, 25);
        cbFood.setBounds(50, 370, 200, 25);
        cbWifi.setBounds(50, 400, 200, 25);
        cbExtraBed.setBounds(50, 430, 200, 25);

        bg.add(cbBeverage);
        bg.add(cbLaundry);
        bg.add(cbFood);
        bg.add(cbWifi);
        bg.add(cbExtraBed);

        // BUTTONS (same theme as CustomerModule)
        btnGenerate = new JButton("🧾 Generate Invoice");
        btnSave = new JButton("💾 Export Invoice");

        btnGenerate.setBounds(300, 350, 200, 35);
        btnSave.setBounds(300, 400, 200, 35);

        btnGenerate.setBackground(new Color(0, 153, 76));  // green
        btnSave.setBackground(new Color(51, 153, 255));    // blue

        btnGenerate.setForeground(Color.WHITE);
        btnSave.setForeground(Color.WHITE);

        btnGenerate.addActionListener(this);
        btnSave.addActionListener(this);

        bg.add(btnGenerate);
        bg.add(btnSave);

        // TEXT AREA (same theme as Customer)
        area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.BOLD, 16));
        area.setBackground(new Color(255, 255, 230));
        area.setForeground(new Color(0, 51, 102));
        area.setEditable(false);
        area.setBorder(BorderFactory.createTitledBorder("📄 Invoice Details"));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(50, 480, 850, 200);
        bg.add(scroll);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == btnGenerate) {
            
            if (txtName.getText().trim().isEmpty() ||
                txtMobile.getText().trim().isEmpty() ||
                txtNights.getText().trim().isEmpty() ||
                txtRate.getText().trim().isEmpty()) {

                area.setText("❌ ERROR: Please fill all fields before generating invoice!");
                return;
            }

            try {
                String name = txtName.getText().trim();
                String mobile = txtMobile.getText().trim();
                int nights = Integer.parseInt(txtNights.getText().trim());
                double rate = Double.parseDouble(txtRate.getText().trim());

                if (mobile.length() != 10) {
                    area.setText("❌ ERROR: Mobile number must be 10 digits!");
                    return;
                }

                double addons = 0;
                if (cbBeverage.isSelected()) addons += 150;
                if (cbLaundry.isSelected()) addons += 300;
                if (cbFood.isSelected()) addons += 500;
                if (cbWifi.isSelected()) addons += 100;
                if (cbExtraBed.isSelected()) addons += 400;

                Invoice inv = new Invoice(name, mobile, nights, rate, addons);
                area.setText(inv.getInvoiceDetails());

            } catch (Exception ex) {
                area.setText("❌ ERROR: Invalid values entered!");
            }
        }

        if (ae.getSource() == btnSave) {
            try {
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(new FileNameExtensionFilter("Text File", "txt"));

                if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

                    String file = fc.getSelectedFile().getAbsolutePath();
                    if (!file.endsWith(".txt")) file += ".txt";

                    FileWriter fw = new FileWriter(file);
                    fw.write(area.getText());
                    fw.close();

                    JOptionPane.showMessageDialog(this, "Invoice Saved Successfully!");
                }

            } catch (IOException ex) {
                area.setText("❌ ERROR: Unable to save invoice!");
            }
        }
    }
}
