import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class UserDashboard extends JFrame implements ActionListener {
    JTextField foodField, quantityField, photoField;
    JButton submitButton, browseButton;
    int userId;

    public UserDashboard(int userId) {
        this.userId = userId;

        setTitle("User - Donate Food");
        setSize(400, 300);
        setLayout(new GridLayout(4, 2));

        foodField = new JTextField();
        quantityField = new JTextField();
        photoField = new JTextField();
        photoField.setEditable(false);
        submitButton = new JButton("Submit");
        browseButton = new JButton("Browse");

        add(new JLabel("Food Description:"));
        add(foodField);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(new JLabel("Photo Path:"));
        add(photoField);
        add(browseButton);
        add(submitButton);

        browseButton.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int option = fc.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                photoField.setText(fc.getSelectedFile().getAbsolutePath());
            }
        });

        submitButton.addActionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String food = foodField.getText();
        String qty = quantityField.getText();
        String photo = photoField.getText();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO donations(user_id, food_desc, quantity, photo_path) VALUES (?, ?, ?, ?)");
            ps.setInt(1, userId);
            ps.setString(2, food);
            ps.setString(3, qty);
            ps.setString(4, photo);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Donation submitted!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}