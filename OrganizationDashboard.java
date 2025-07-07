import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class OrganizationDashboard extends JFrame {
    JTextArea textArea;

    public OrganizationDashboard() {
        setTitle("Organization - View Donations");
        setSize(500, 400);
        textArea = new JTextArea();
        add(new JScrollPane(textArea));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT u.name, d.food_desc, d.quantity, d.photo_path FROM donations d JOIN users u ON d.user_id = u.id");

            while (rs.next()) {
                textArea.append("Donor: " + rs.getString("name") +
                                ", Food: " + rs.getString("food_desc") +
                                ", Quantity: " + rs.getString("quantity") +
                                ", Photo: " + rs.getString("photo_path") + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}