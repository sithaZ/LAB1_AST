import javax.swing.*;
import java.awt.*;
import Lab01.Authentication;

public class App {
    public static void main(String[] args) {
        // Create the main window
        JFrame frame = new JFrame("Web Login System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen

        // Use a panel with padding
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Components
        JLabel titleLabel = new JLabel("System Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        JTextField userField = new JTextField(15);
        JPasswordField passField = new JPasswordField(15);
        JButton loginBtn = new JButton("Login");
        JLabel statusLabel = new JLabel(" ", SwingConstants.CENTER);

        // Layout logic
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1; gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(userField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(loginBtn, gbc);

        gbc.gridy = 4;
        panel.add(statusLabel, gbc);

        // Button Action: Call our Authentication Service
        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            statusLabel.setText("Connecting to NestJS...");
            statusLabel.setForeground(Color.BLUE);

            Authentication auth = new Authentication();
            auth.login(username, password).thenAccept(success -> {
                if (success) {
                    statusLabel.setText("Login Successful!");
                    statusLabel.setForeground(new Color(0, 128, 0)); // Green
                } else {
                    statusLabel.setText("Invalid credentials.");
                    statusLabel.setForeground(Color.RED);
                }
            }).exceptionally(ex -> {
                statusLabel.setText("Error: Backend is offline.");
                statusLabel.setForeground(Color.RED);
                return null;
            });
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}