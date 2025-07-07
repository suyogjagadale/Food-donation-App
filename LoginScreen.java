import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginScreen extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JComboBox<String> roleBox;
    JButton loginButton;

    public LoginScreen() {
        setTitle("Login");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        roleBox = new JComboBox<>(new String[]{"User", "Organization", "Admin"});
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        add(new JLabel("Role:"));
        add(roleBox);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);

        loginButton.addActionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String role = (String) roleBox.getSelectedItem();
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=? AND role=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");
                if (role.equals("User")) new UserDashboard(userId);
                else if (role.equals("Organization")) new OrganizationDashboard();
                else new AdminDashboard();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login failed!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}