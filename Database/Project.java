import java.sql.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Project {
    private static JPasswordField passwordField;
    private static String Username = null;
    private static String Password = null;

    public static void main(String[] args) throws SQLException {

        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        Statement myStmt = myConn.createStatement();
        ResultSet resultSet = myStmt.executeQuery("SELECT username, password FROM login");

        // Create the login frame outside the loop
        JFrame frame = new JFrame("Pharmacy Database");
        JPanel panel = new JPanel();
        frame.setSize(915, 650);
        frame.setLocationRelativeTo(null);

        JLabel lblNewLabel = new JLabel("Pharmacy XYZ Login");
        lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
        frame.setVisible(true);

        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setToolTipText("Password");
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);

        JFormattedTextField formattedTextField = new JFormattedTextField();
        formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Inside the actionPerformed method
                char[] passwordChars = passwordField.getPassword(); // Get the password input as char array
                String userPassword = new String(passwordChars).trim();
                String userInput = formattedTextField.getText().trim();

                // Check each row in the result set for the correct credentials
                try {
                    while (resultSet.next()) {
                        String currentUsername = resultSet.getString("username");
                        String currentPassword = resultSet.getString("password");

                        if (currentUsername.equals(userInput) && currentPassword.equals(userPassword)) {
                            frame.dispose();
                            try {
                                Database data1 = new Database();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            return; // Exit the method if credentials match
                        }
                    }
                    // If no match is found, show error
                    Error error = new Error();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JLabel lblNewLabel_1_1 = new JLabel("Password");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        JSeparator separator = new JSeparator();
        separator.setBackground(SystemColor.textText);
        separator.setForeground(SystemColor.textText);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(SystemColor.textText);
        separator_1.setBackground(SystemColor.textText);
        
        JSeparator separator_2 = new JSeparator();

        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap(10, Short.MAX_VALUE)
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 899, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 874, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(25, Short.MAX_VALUE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(separator, GroupLayout.PREFERRED_SIZE, 874, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(18, Short.MAX_VALUE))
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addContainerGap(420, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
        						.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
        					.addGroup(groupLayout.createSequentialGroup()
        						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
        						.addGap(23)))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(28)
        					.addComponent(lblNewLabel_1)
        					.addPreferredGap(ComponentPlacement.RELATED, 25, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(30)
        					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 23, GroupLayout.PREFERRED_SIZE)))
        			.addGap(346))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.CENTER)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
        				.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(87)
        			.addComponent(lblNewLabel)
        			.addGap(42)
        			.addComponent(lblNewLabel_1)
        			.addGap(18)
        			.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(16)
        			.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(34)
        			.addComponent(btnNewButton)
        			.addGap(214)
        			.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        frame.getContentPane().setLayout(groupLayout);
    }
}

