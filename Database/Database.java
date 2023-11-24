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

public class Database extends JFrame {
    private JTextField textField;
    private JTextField textField_2;
    private JList<String> list;
    private JList<String> list1;
    private JList<String> list_1;
    private JList<String> list_2;
    private DefaultListModel<String> listModel;
    private JTable table;
    private JTextField textField_1;
    private JTable table_1;
    private JTextField textField_3;
    private JTextField textField_5;
    private JTextField textField_7;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;
    private JTable table_2;
    private JCheckBox chckbxNewCheckBox;
    private JTextField textField_4;
    private JTextField textField_6;

    public Database() throws SQLException {
        super("Pharmacy XYZ Database");

        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        Statement myStmt = myConn.createStatement();

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);
        JLabel lblNewLabel = new JLabel("Pharmacy XYZ Database");
        lblNewLabel.setBounds(607, 5, 269, 27);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Search by ID");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(22, 60, 102, 14);
        panel.add(lblNewLabel_1);
        
        //Search by Patient ID
        
        JButton btnNewButton = new JButton("Go");
        btnNewButton.addActionListener(e -> {
            try {
                // Retrieve user input from textField
                String inputId = textField.getText();
                clearTextLine();
                // Call a method to update the table based on the user input
                fetchDataAndUpdateTableById(inputId);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        
        btnNewButton.setText("Go");
        btnNewButton.setBounds(118, 84, 44, 23);
        panel.add(btnNewButton);

        textField = new JTextField();
        textField.setBounds(22, 85, 86, 22);
        panel.add(textField);
        textField.setColumns(10);
        
        chckbxNewCheckBox = new JCheckBox("\r\n");
        chckbxNewCheckBox.setBounds(119, 509, 25, 23);
        panel.add(chckbxNewCheckBox);
        
        

        JLabel lblNewLabel_2 = new JLabel("Search by Insurance");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(22, 116, 175, 14);
        panel.add(lblNewLabel_2);

        JSeparator separator = new JSeparator();
        separator.setBackground(UIManager.getColor("CheckBox.foreground"));
        separator.setForeground(UIManager.getColor("CheckBox.foreground"));
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(181, 21, 5, 929);
        panel.add(separator);

        JLabel lblNewLabel_3 = new JLabel("Search by Name");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(22, 172, 151, 14);
        panel.add(lblNewLabel_3);

        textField_2 = new JTextField();
        textField_2.setBounds(22, 197, 86, 22);
        panel.add(textField_2);
        textField_2.setColumns(10);

        //Search by Insurance

        JButton btnNewButton_1 = new JButton("Go");
        btnNewButton_1.setBounds(118, 141, 44, 23);
        panel.add(btnNewButton_1);
        btnNewButton_1.addActionListener(e -> {
            try {
                // Retrieve user input from insurance list
                String selectedInsurance = list.getSelectedValue();
                
                // Call a method to update the table based on the selected insurance
                fetchDataAndUpdateTableByInsurance(selectedInsurance);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        //Search by name
        
        JButton btnNewButton_1_1 = new JButton("Go");
        btnNewButton_1_1.setBounds(118, 196, 44, 23);
        panel.add(btnNewButton_1_1);
        btnNewButton_1_1.addActionListener(e -> {
            try {
                // Retrieve user input from insurance list
            	String selectedName = textField_2.getText();
            	 clearTextLine1(); 
                // Call a method to update the table based on the selected insurance
                fetchDataAndUpdateTableByName(selectedName);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Execute the query and add items to the DefaultListModel
        listModel = new DefaultListModel<>();
        ResultSet resultSet = myStmt.executeQuery("SELECT Company_Name FROM insurance");
        while (resultSet.next()) {
            String companyName = resultSet.getString("Company_Name");
            listModel.addElement(companyName);
        }
        resultSet.close();

        // Create the JList with the populated DefaultListModel
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(22, 141, 86, 20);
        panel.add(scrollPane);

        // Add a MouseListener to the JList to toggle the JScrollPane
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleScrollPane();
            }
        });

        JButton btnNewButton_2 = new JButton("Add Patient");
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnNewButton_2.setBounds(32, 601, 122, 23);
        panel.add(btnNewButton_2);
        btnNewButton_2.addActionListener(e -> {
            try {
            	addPatient();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        JButton btnNewButton_2_1 = new JButton("Add Medication");
        btnNewButton_2_1.addActionListener(e -> {
            try {
				addMedication add = new addMedication();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        });
        btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnNewButton_2_1.setBounds(530, 888, 120, 23);
        panel.add(btnNewButton_2_1);

        //Add Insurance Company
        
        JButton btnNewButton_2_1_1 = new JButton("Add Insurance Company");
        btnNewButton_2_1_1.setBackground(UIManager.getColor("Button.background"));
        btnNewButton_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnNewButton_2_1_1.setBounds(1142, 589, 175, 23);
        panel.add(btnNewButton_2_1_1);
        btnNewButton_2_1_1.addActionListener(e -> {
            try {
                // Retrieve user input from insurance list
            	String addIns = textField_3.getText();
                clearTextLine3();
                // Call a method to update the table based on the selected insurance
                fetchDataAndUpdateTableAddIns(addIns);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        
        String[] columnNames = {"Patient ID", "Name", "Address", "Phone #", "Insur.Status", "Medication ID", "Insur. Company"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setRowHeight(30);
        table.setEnabled(false);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);

        table.setFont(new Font("Tahoma", Font.PLAIN, 18));
        table.setBounds(238, 60, 1173, 400);
        JScrollPane scrollPane1 = new JScrollPane(table);
        scrollPane1.setBounds(238, 60, 1173, 400);
        panel.add(scrollPane1);


        // Fetch data from the database and populate the table
        fetchDataAndUpdateTable();
        
        JLabel lblNewLabel_3_1 = new JLabel("Search Med. ID");
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_3_1.setBounds(22, 230, 151, 14);
        panel.add(lblNewLabel_3_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(22, 256, 86, 22);
        panel.add(textField_1);
        
        JButton btnNewButton_1_1_1 = new JButton("Go");
        btnNewButton_1_1_1.setBounds(118, 255, 44, 23);
        panel.add(btnNewButton_1_1_1);
        
        //Search by Med. ID
        btnNewButton_1_1_1.addActionListener(e -> {
            try {
                // Retrieve user input from insurance list
            	String selectedID = textField_1.getText();
            	clearTextLine2();
                // Call a method to update the table based on the selected insurance
                fetchDataAndUpdateTableByMedID(selectedID);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        
       
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
        
        JLabel lblNewLabel_2_1 = new JLabel("Add Insurance");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1.setBounds(1167, 530, 124, 14);
        panel.add(lblNewLabel_2_1);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBackground(UIManager.getColor("Button.focus"));
        separator_2.setForeground(UIManager.getColor("CheckBox.foreground"));
        separator_2.setBounds(183, 498, 1256, 9);
        panel.add(separator_2);
        
        JSeparator separator_4 = new JSeparator();
        separator_4.setOrientation(SwingConstants.VERTICAL);
        separator_4.setForeground(Color.BLACK);
        separator_4.setBackground(Color.BLACK);
        separator_4.setBounds(1438, 21, 3, 930);
        panel.add(separator_4);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(1175, 556, 102, 22);
        panel.add(textField_3);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setOrientation(SwingConstants.VERTICAL);
        separator_1.setForeground(Color.BLACK);
        separator_1.setBackground(Color.BLACK);
        separator_1.setBounds(1011, 499, 3, 451);
        panel.add(separator_1);
        
        JButton btnNewButton_2_2 = new JButton("Show All");
        btnNewButton_2_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnNewButton_2_2.setBounds(31, 301, 122, 23);
        panel.add(btnNewButton_2_2);
        btnNewButton_2_2.addActionListener(e -> {
            try {
            	fetchDataAndUpdateTable();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        
        
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(86, 540, 86, 20);
        panel.add(textField_5);
        
        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(86, 478, 86, 20);
        panel.add(textField_7);
        
        textField_9 = new JTextField();
        textField_9.setColumns(10);
        textField_9.setBounds(86, 444, 86, 20);
        panel.add(textField_9);
        
        textField_10 = new JTextField();
        textField_10.setColumns(10);
        textField_10.setBounds(86, 412, 86, 20);
        panel.add(textField_10);
        
        textField_11 = new JTextField();
        textField_11.setColumns(10);
        textField_11.setBounds(86, 381, 86, 20);
        panel.add(textField_11);
        
        JLabel lblNewLabel_4 = new JLabel("Add Patient");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_4.setBounds(44, 358, 103, 14);
        panel.add(lblNewLabel_4);
        
        JLabel lblNewLabel_4_1 = new JLabel("Patient ID");
        lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4_1.setBounds(13, 380, 79, 20);
        panel.add(lblNewLabel_4_1);
        
        JLabel lblNewLabel_4_1_1 = new JLabel("Name");
        lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4_1_1.setBounds(13, 412, 79, 20);
        panel.add(lblNewLabel_4_1_1);
        
        JLabel lblNewLabel_4_1_1_1 = new JLabel("Phone #");
        lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4_1_1_1.setBounds(13, 476, 79, 20);
        panel.add(lblNewLabel_4_1_1_1);
        
        JLabel lblNewLabel_4_1_2 = new JLabel("Address");
        lblNewLabel_4_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4_1_2.setBounds(13, 444, 79, 20);
        panel.add(lblNewLabel_4_1_2);
        
        JLabel lblNewLabel_4_1_1_2 = new JLabel("Insured");
        lblNewLabel_4_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4_1_1_2.setBounds(33, 508, 79, 20);
        panel.add(lblNewLabel_4_1_1_2);
        
        JLabel lblNewLabel_4_1_2_1 = new JLabel("Med. ID");
        lblNewLabel_4_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4_1_2_1.setBounds(14, 538, 79, 20);
        panel.add(lblNewLabel_4_1_2_1);
        
        JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Insurance");
        lblNewLabel_4_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4_1_1_1_1.setBounds(14, 569, 79, 20);
        panel.add(lblNewLabel_4_1_1_1_1);
        
      
        
        DefaultListModel<String> listModel1 = new DefaultListModel<>();
        list1 = new JList<>(listModel1);
        
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane2 = new JScrollPane(list1);
        scrollPane2.setBounds(22, 141, 86, 20);
        panel.add(scrollPane2);
        
        chckbxNewCheckBox = new JCheckBox("\r\n");
        chckbxNewCheckBox.setBounds(119, 509, 25, 23);
        panel.add(chckbxNewCheckBox);
        
        
        //Insurance Companies List Table
        table_2 = new JTable();
        table_2.setEnabled(false);
        table_2.setRowHeight(30);
        table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        table_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        JScrollPane scrollPane1_2 = new JScrollPane(table_2);
        scrollPane1_2.setBounds(1161, 642, 137, 98);
        panel.add(scrollPane1_2);
        String[] columnName = {"Insurance Company"};
        DefaultTableModel model_2 = new DefaultTableModel(columnName, 0);
        table_2.setModel(model_2); // Set the model for table_2
        fetchInsuranceCompanies();
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setBackground(SystemColor.infoText);
        separator_3.setForeground(SystemColor.desktop);
        separator_3.setBounds(6, 340, 171, 10);
        panel.add(separator_3);
        
        JSeparator separator_5 = new JSeparator();
        separator_5.setBackground(SystemColor.menuText);
        separator_5.setForeground(SystemColor.textText);
        separator_5.setBounds(7, 949, 1433, 4);
        panel.add(separator_5);
        
        JSeparator separator_8 = new JSeparator();
        separator_8.setBackground(SystemColor.textText);
        separator_8.setForeground(SystemColor.textText);
        separator_8.setBounds(886, 20, 552, 10);
        panel.add(separator_8);
        
        JSeparator separator_8_1 = new JSeparator();
        separator_8_1.setForeground(SystemColor.textText);
        separator_8_1.setBackground(SystemColor.textText);
        separator_8_1.setBounds(3, 21, 594, 10);
        panel.add(separator_8_1);
        
        list_1 = new JList<>(listModel);
        list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane_4 = new JScrollPane(list_1);
        scrollPane_4.setBounds(86, 572, 85, 18);
        panel.add(scrollPane_4);
        
        JSeparator separator_6 = new JSeparator();
        separator_6.setBackground(SystemColor.menuText);
        separator_6.setForeground(SystemColor.textText);
        separator_6.setBounds(6, 640, 171, 4);
        panel.add(separator_6);
        
        JLabel lblNewLabel_5 = new JLabel("Delete Patient");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_5.setBounds(33, 648, 130, 14);
        panel.add(lblNewLabel_5);
        
        textField_4 = new JTextField();
        textField_4.setBounds(88, 675, 86, 20);
        panel.add(textField_4);
        textField_4.setColumns(10);
        
        JButton btnNewButton_3 = new JButton("Delete Patient");
        btnNewButton_3.addActionListener(e -> {
            try {
            	deletePatient(Integer.parseInt(textField_4.getText()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        btnNewButton_3.setBounds(38, 706, 115, 23);
        panel.add(btnNewButton_3);
        
        JLabel lblNewLabel_4_1_3 = new JLabel("Patient ID");
        lblNewLabel_4_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4_1_3.setBounds(13, 674, 79, 20);
        panel.add(lblNewLabel_4_1_3);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Delete Insurance");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_1.setBounds(1159, 757, 149, 14);
        panel.add(lblNewLabel_2_1_1);
        
        list_2 = new JList<>(listModel);
        list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane_4_1 = new JScrollPane(list_2);
        scrollPane_4_1.setBounds(1191, 788, 85, 18);
        panel.add(scrollPane_4_1);
        
        JButton btnNewButton_4 = new JButton("Delete Company");
        btnNewButton_4.addActionListener(e -> {
            try {
            	deleteCompany(list_2.getSelectedValue());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnNewButton_4.setBounds(1172, 817, 124, 23);
        panel.add(btnNewButton_4);
        
        
        
        
        JLabel lblNewLabel_5_1 = new JLabel("Delete Medication");
        lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_5_1.setBounds(15, 764, 156, 14);
        panel.add(lblNewLabel_5_1);
        
        JLabel lblNewLabel_4_1_3_1 = new JLabel("Med. ID");
        lblNewLabel_4_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4_1_3_1.setBounds(22, 788, 60, 20);
        panel.add(lblNewLabel_4_1_3_1);
        
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(87, 790, 86, 20);
        panel.add(textField_6);
        
        JButton btnNewButton_3_1 = new JButton("Delete Medication");
        btnNewButton_3_1.addActionListener(e -> {
            try {
            	deleteMedication(Integer.parseInt(textField_6.getText()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        
        btnNewButton_3_1.setBounds(29, 817, 133, 23);
        panel.add(btnNewButton_3_1);
   
        JSeparator separator_7 = new JSeparator();
        separator_7.setBackground(SystemColor.textText);
        separator_7.setForeground(SystemColor.textText);
        separator_7.setBounds(5, 746, 173, 6);
        panel.add(separator_7);
        
        JLabel lblNewLabel_4_2 = new JLabel("Search");
        lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_4_2.setBounds(61, 33, 65, 14);
        panel.add(lblNewLabel_4_2);
        
        JLabel lblNewLabel_6 = new JLabel("Medications");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_6.setBounds(552, 513, 122, 14);
        panel.add(lblNewLabel_6);
        
        JButton btnNewButton_4_1 = new JButton("Refresh");
        btnNewButton_4_1.addActionListener(e -> {
            try {
            	fetchInsuranceCompanies();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        btnNewButton_4_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnNewButton_4_1.setBounds(1171, 853, 124, 23);
        panel.add(btnNewButton_4_1);
        
        JButton btnNewButton_5 = new JButton("Refresh");
        btnNewButton_5.addActionListener(e -> {
            try {
            	fetchDataAndUpdateTable1();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        btnNewButton_5.setBounds(869, 889, 71, 23);
        panel.add(btnNewButton_5);
        

        // Add a MouseListener to the JList to toggle the JScrollPane
        list_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleScrollPane();
            }
        });

        setVisible(true);
        setSize(1500, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void clearTextLine2() {
    	textField_1.setText("");	
	}
    
    private void clearTextLine3() {
    	textField_3.setText("");	
	}
    

	private void toggleScrollPane() {
        Container parent = list.getParent();
        if (parent instanceof JScrollPane) {
            // Remove the JScrollPane
            JScrollPane scrollPane = (JScrollPane) parent;
            JViewport viewport = scrollPane.getViewport();
            viewport.remove(list);
            parent.remove(scrollPane);
            parent.add(list);
        } else {
            // Add the JScrollPane
            JScrollPane scrollPane = new JScrollPane(list);
            scrollPane.setBounds(list.getBounds());
            parent.remove(list);
            parent.add(scrollPane);
        }

        // Refresh the panel to update the changes
        revalidate();
        repaint();
    }
    
    private void fetchInsuranceCompanies() throws SQLException {
        DefaultTableModel model_2 = (DefaultTableModel) table_2.getModel();
        model_2.setRowCount(0); // Clear existing data

        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement myStmt = myConn.createStatement()) {

            // Use PreparedStatement to prevent SQL injection
            String query = "SELECT * FROM Insurance";
            try (java.sql.PreparedStatement pstmt = myConn.prepareStatement(query)) {
                ResultSet resultSet = pstmt.executeQuery();

                while (resultSet.next()) {
                    String companyName = resultSet.getString("Company_Name");

                    model_2.addRow(new Object[]{companyName});
                }
            }
        }
    }

    
    private void fetchDataAndUpdateTableAddIns(String addIns) throws SQLException {
        DefaultTableModel model_2 = (DefaultTableModel) table_2.getModel();
        model_2.setRowCount(0); // Clear existing data

        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement myStmt = myConn.createStatement()) {

            // Use PreparedStatement to prevent SQL injection
            String query = "INSERT INTO Insurance (Company_Name) VALUES (?)";
            try (java.sql.PreparedStatement pstmt = myConn.prepareStatement(query)) {
                pstmt.setString(1, addIns);
                pstmt.executeUpdate();
            }

            // Now, fetch the updated list of insurance companies
            fetchInsuranceCompanies();
            updateInsuranceList();
        }
    }
    
    
    private void fetchDataAndUpdateTableById(String patientId) throws SQLException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data

        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement myStmt = myConn.createStatement()) {

            // Use PreparedStatement to prevent SQL injection
            String query = "SELECT * FROM patient WHERE Patient_id = ?";
            try (java.sql.PreparedStatement pstmt = myConn.prepareStatement(query)) {
                pstmt.setString(1, patientId);
                ResultSet resultSet = pstmt.executeQuery();

                while (resultSet.next()) {
                    int patientIdResult = resultSet.getInt("Patient_id");
                    String name = resultSet.getString("Name");
                    String address = resultSet.getString("Address");
                    String phoneNumber = resultSet.getString("Phone_Number");
                    int insuranceStatus = resultSet.getInt("Insurance_Status");
                    int medicationId = resultSet.getInt("Medication_Id");
                    String companyName = resultSet.getString("Company_Name");

                    if (companyName == null) {
                        companyName = ("No Insurance");
                    }

                    model.addRow(new Object[]{patientIdResult, name, address, phoneNumber, insuranceStatus, medicationId, companyName});
                }
            }
        }
    }
    
    private void fetchDataAndUpdateTableByInsurance(String selectedInsurance) throws SQLException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data

        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement myStmt = myConn.createStatement()) {

            // Use PreparedStatement to prevent SQL injection
            String query = "SELECT * FROM patient WHERE Company_Name = ?";
            try (java.sql.PreparedStatement pstmt = myConn.prepareStatement(query)) {
                pstmt.setString(1, selectedInsurance);
                ResultSet resultSet = pstmt.executeQuery();

                while (resultSet.next()) {
                    int patientIdResult = resultSet.getInt("Patient_id");
                    String name = resultSet.getString("Name");
                    String address = resultSet.getString("Address");
                    String phoneNumber = resultSet.getString("Phone_Number");
                    int insuranceStatus = resultSet.getInt("Insurance_Status");
                    int medicationId = resultSet.getInt("Medication_Id");
                    String companyName = resultSet.getString("Company_Name");

                    if (companyName == null) {
                        companyName = ("No Insurance");
                    }

                    model.addRow(new Object[]{patientIdResult, name, address, phoneNumber, insuranceStatus, medicationId, companyName});
                }
            }
        }
    }
    
    private void fetchDataAndUpdateTableByMedID(String selectedID) throws SQLException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data

        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement myStmt = myConn.createStatement()) {

            // Use PreparedStatement to prevent SQL injection
            String query = "SELECT * FROM patient WHERE Medication_Id = ?";
            try (java.sql.PreparedStatement pstmt = myConn.prepareStatement(query)) {
                pstmt.setString(1, selectedID);
                ResultSet resultSet = pstmt.executeQuery();

                while (resultSet.next()) {
                    int patientIdResult = resultSet.getInt("Patient_id");
                    String name = resultSet.getString("Name");
                    String address = resultSet.getString("Address");
                    String phoneNumber = resultSet.getString("Phone_Number");
                    int insuranceStatus = resultSet.getInt("Insurance_Status");
                    int medicationId = resultSet.getInt("Medication_Id");
                    String companyName = resultSet.getString("Company_Name");

                    if (companyName == null) {
                        companyName = ("No Insurance");
                    }

                    model.addRow(new Object[]{patientIdResult, name, address, phoneNumber, insuranceStatus, medicationId, companyName});
                }
            }
        }
    }
    
    private void fetchDataAndUpdateTableByName(String selectedName) throws SQLException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data

        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement myStmt = myConn.createStatement()) {

            // Use PreparedStatement to prevent SQL injection
            String query = "SELECT * FROM patient WHERE Name = ?";
            try (java.sql.PreparedStatement pstmt = myConn.prepareStatement(query)) {
                pstmt.setString(1, selectedName);
                ResultSet resultSet = pstmt.executeQuery();

                while (resultSet.next()) {
                    int patientIdResult = resultSet.getInt("Patient_id");
                    String name = resultSet.getString("Name");
                    String address = resultSet.getString("Address");
                    String phoneNumber = resultSet.getString("Phone_Number");
                    int insuranceStatus = resultSet.getInt("Insurance_Status");
                    int medicationId = resultSet.getInt("Medication_Id");
                    String companyName = resultSet.getString("Company_Name");

                    if (companyName == null) {
                        companyName = ("No Insurance");
                    }

                    model.addRow(new Object[]{patientIdResult, name, address, phoneNumber, insuranceStatus, medicationId, companyName});
                }
            }
        }
    }
    
    private void fetchDataAndUpdateTable() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data
        
        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement myStmt = myConn.createStatement()) {

            ResultSet resultSet = myStmt.executeQuery("SELECT * FROM patient");

            // Add column names to the model
           

            while (resultSet.next()) {
            	
                int patientId = resultSet.getInt("Patient_id");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String phoneNumber = resultSet.getString("Phone_Number");
                int insuranceStatus = resultSet.getInt("Insurance_Status");
                int medicationId = resultSet.getInt("Medication_Id");
                String companyName = resultSet.getString("Company_Name");
                	if (companyName == null) {
                		   companyName = ("No Insurance");
            	}

                model.addRow(new Object[]{patientId, name, address, phoneNumber, insuranceStatus, medicationId, companyName});
            }
            
        }
    }
    
    private void deletePatient(int patientID) throws SQLException {
        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement myStmt = myConn.prepareStatement("DELETE FROM patient WHERE Patient_ID = ?")) {

            // Set the patient ID parameter in the SQL query
            myStmt.setInt(1, patientID);

            // Execute the update
            int rowsAffected = myStmt.executeUpdate();

                fetchDataAndUpdateTable(); 
        }
    }
    
    private void deleteMedication(int medicationId) throws SQLException {
        // Replace these with your actual database connection details
        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement deleteStmt = myConn.prepareStatement("DELETE FROM medication WHERE Medication_ID = ?")) {

            // Set the medication ID as a parameter in the prepared statement
            deleteStmt.setInt(1, medicationId);

            // Execute the deletion
            int rowsAffected = deleteStmt.executeUpdate();
        }
        fetchDataAndUpdateTable1();
    }

   
    private void addPatient() throws SQLException {
        // Retrieve user input
        String patientIDText = textField_11.getText();
        String medidText = textField_5.getText();

        int patientID = Integer.parseInt(patientIDText);
        String name = textField_10.getText();
        String address = textField_9.getText();
        String phone = textField_7.getText();
        String insured;
        if (this.chckbxNewCheckBox.isSelected())
        	insured = "0";
         else 
        	 insured = "1";
        
        int medid = Integer.parseInt(medidText);
        String insurance = list1.getSelectedValue();

        // Call the method to add a patient
        addPatientToDatabase(patientID, name, address, phone, insured, medid, insurance);
    }


    // Replace this with the actual method you have for adding a patient to the database
    private void addPatientToDatabase(int patientID, String name, String address, String phone, String insured, int medid, String insurance) throws SQLException {
        // Your logic for adding a patient to the database goes here
        // You can use JDBC to insert the data into your database
        // Example:
        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement myStmt = myConn.prepareStatement("INSERT INTO patient VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            myStmt.setInt(1, patientID);
            myStmt.setString(2, name);
            myStmt.setString(3, address);
            myStmt.setString(4, phone);
            myStmt.setString(5, insured);
            myStmt.setInt(6, medid);
            myStmt.setString(7, insurance);

            // Execute the update
            myStmt.executeUpdate();
            clearTextFields();
            fetchDataAndUpdateTable();
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

                model1.addRow(new Object[]{medId, name, price, issueDate, RefillCount, Description, Expiration, Quantity});
            }
            
        }
    }
    
    private void deleteCompany(String companyName) throws SQLException {
        final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";

        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement myStmt = myConn.createStatement()) {

            // Execute the DELETE statement
            String deleteQuery = "DELETE FROM insurance WHERE Company_Name = '" + companyName + "'";
            myStmt.executeUpdate(deleteQuery);

            // Optionally, you can update the UI or perform any other actions after deletion
            // For example, update the JList or refresh the table
            updateInsuranceList();
        }
    }

    private void updateInsuranceList() throws SQLException {
    	final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
        final String USERNAME = "root";
        final String PASSWORD = "213213";
        // Clear the existing data in the JList
        listModel.removeAllElements();

        // Retrieve and add the updated list of insurance companies
        try (Connection myConn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement myStmt = myConn.createStatement()) {

            ResultSet resultSet = myStmt.executeQuery("SELECT Company_Name FROM insurance");
            while (resultSet.next()) {
                String companyName = resultSet.getString("Company_Name");
                listModel.addElement(companyName);
            }
            resultSet.close();
        }
    }
    
    private void clearTextFields() {
	    textField_11.setText("");
	    textField_10.setText("");
	    textField_9.setText("");
	    textField_7.setText("");
	    textField_5.setText("");
	    list_1.clearSelection();
	    chckbxNewCheckBox.setText("");
	}
    
    public void clearTextLine() {
    	textField.setText("");
    }
    
    public void clearTextLine1() {
    	textField_2.setText("");
    }
}

