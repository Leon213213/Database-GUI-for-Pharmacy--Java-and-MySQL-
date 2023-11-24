import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class addMedication extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	 private JTable table_1;
	public addMedication() throws SQLException{
		super("Pharmacy XYZ Database");

        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        Statement myStmt = myConn.createStatement();

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setVisible(true);
        setSize(416, 380);
        setTitle("Add Medication");

        // Center the frame on the screen
        setLocationRelativeTo(null);
        
        textField = new JTextField();
        textField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Add Medication");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Med. ID");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JLabel lblNewLabel_1_1 = new JLabel("Name");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JLabel lblNewLabel_1_2 = new JLabel("Price");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JLabel lblNewLabel_1_3 = new JLabel("Iss. Date");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JLabel lblNewLabel_1_4 = new JLabel("Ref. Count");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JLabel lblNewLabel_1_5 = new JLabel("Desc.");
        lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        
        JLabel lblNewLabel_1_5_1 = new JLabel("Expir.");
        lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        textField_7 = new JTextField();
        textField_7.setColumns(10);
        
        JLabel lblNewLabel_1_5_1_1 = new JLabel("Quant.");
        lblNewLabel_1_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        //int medID, String name, int price, int issue, int ref, String description, int expiration, int quantity
        String[] columnNames1 = {"Med. ID", "Name", "Price", "Iss. Date", "Ref.Count", "Desc.", "Expir.", "Quant."};
        DefaultTableModel model1 = new DefaultTableModel(columnNames1, 0);
        table_1 = new JTable(model1);
        table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        table_1.setRowHeight(30);
        table_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        JScrollPane scrollPane11 = new JScrollPane(table_1);
        scrollPane11.setBounds(238, 540, 702, 337);
        panel.add(scrollPane11);
        fetchDataAndUpdateTable1();
        
        JButton btnNewButton = new JButton("Add Medication");
        btnNewButton.addActionListener(e -> {
            int medID = Integer.parseInt(textField.getText());
			String name = textField_1.getText();
			int price = Integer.parseInt(textField_2.getText());
			int issue = Integer.parseInt(textField_3.getText());
			int ref = Integer.parseInt(textField_4.getText());
			String description = textField_5.getText();
			int expiration = Integer.parseInt(textField_6.getText());
			int quantity = Integer.parseInt(textField_7.getText());
			
			try {
				add(medID, name, price, issue, ref, description, expiration, quantity);
				fetchDataAndUpdateTable1();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			       clearTextFields();
        });
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap(119, Short.MAX_VALUE)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
        							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblNewLabel_1, Alignment.TRAILING)
        								.addComponent(lblNewLabel_1_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblNewLabel_1_2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblNewLabel_1_3, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblNewLabel_1_4, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblNewLabel_1_5, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblNewLabel_1_5_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
        							.addGap(18))
        						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
        							.addComponent(lblNewLabel_1_5_1_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
        							.addGap(19)))
        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(129))
        				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
        					.addComponent(lblNewLabel)
        					.addGap(144))))
        		.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
        			.addGap(142)
        			.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(144, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel)
        			.addGap(11)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_1))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
        				.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
        					.addGap(10)
        					.addComponent(lblNewLabel_1_5, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(7)
        					.addComponent(lblNewLabel_1_5_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(6)
        					.addComponent(lblNewLabel_1_5_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
        			.addGap(18)
        			.addComponent(btnNewButton)
        			.addContainerGap(43, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
		
	}
	
	private void add(int medID, String name, int price, int issue, int ref, String description, int expiration, int quantity) throws SQLException {
	    final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
	    final String USERNAME = "root";
	    final String PASSWORD = "213213";

	    try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	         PreparedStatement myStmt = myConn.prepareStatement("INSERT INTO medication VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

	        myStmt.setInt(1, medID);
	        myStmt.setString(2, name);
	        myStmt.setInt(3, price);
	        myStmt.setInt(4, issue);
	        myStmt.setInt(5, ref);
	        myStmt.setString(6, description);
	        myStmt.setInt(7, expiration);
	        myStmt.setInt(8, quantity);

	        // Execute the update
	        int rowsAffected = myStmt.executeUpdate();

	        fetchDataAndUpdateTable1();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private void fetchDataAndUpdateTable1() throws SQLException {
	    DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
	    model1.setRowCount(0); // Clear existing data

	    final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
	    final String USERNAME = "root";
	    final String PASSWORD = "213213";

	    try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	         Statement myStmt = myConn.createStatement()) {

	        ResultSet resultSet = myStmt.executeQuery("SELECT * FROM medication");

	        while (resultSet.next()) {
	            int medId = resultSet.getInt("Medication_id");
	            String name = resultSet.getString("Name");
	            int price = resultSet.getInt("Price");
	            String issueDate = resultSet.getString("issueDate");
	            int RefillCount = resultSet.getInt("RefillCount");
	            String Description = resultSet.getString("Description");
	            int Expiration = resultSet.getInt("Expiration");
	            int Quantity = resultSet.getInt("Quantity");

	            // Update the table on the EDT
	            SwingUtilities.invokeLater(() -> {
	                model1.addRow(new Object[]{medId, name, price, issueDate, RefillCount, Description, Expiration, Quantity});
	            });
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private void clearTextFields() {
	    textField.setText("");
	    textField_1.setText("");
	    textField_2.setText("");
	    textField_3.setText("");
	    textField_4.setText("");
	    textField_5.setText("");
	    textField_6.setText("");
	    textField_7.setText("");
	}
}

