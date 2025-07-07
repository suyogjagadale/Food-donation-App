import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminDashboard extends JFrame {
    JTextArea textArea;

    public AdminDashboard() {
        setTitle("Admin - All Data");
        setSize(500, 400);
        textArea = new JTextArea();
        add(new JScrollPane(textArea));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM donations");

            while (rs.next()) {
                textArea.append("User ID: " + rs.getInt("user_id") +
                                ", Food: " + rs.getString("food_desc") +
                                ", Quantity: " + rs.getString("quantity") +
                                ", Photo: " + rs.getString("photo_path") + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}