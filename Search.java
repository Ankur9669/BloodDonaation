
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.MessageFormat;

import javax.swing.table.*;
public class Search extends JFrame
{
	JPanel JMainPanel, JTablePanel, JCenterPanel, JInputPanel;
	JLabel JCity, JBloodGrp;
	JButton JSearch, JCancel, JPrintButton;
	JComboBox JComboBloodGrp, JComboCity;
	String BloodGrp[] = {"A+", "A-", "B+", "B-" ,"AB+", "AB-", "O+", "O-"};
	String city[] = {"Jabalpur", "Bhopal", "Patna", "Nagpur", "Mumbai", "Indore"};
	JTable JTable1;
	DefaultTableModel tableModel;
	
	public Search()
	{
		this.setVisible(true);
		this.setBounds(100, 100, 500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTable1 = new JTable();
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Name");
		tableModel.addColumn("Contact");
		tableModel.addColumn("Email");
		tableModel.addColumn("BloodGroup");
		tableModel.addColumn("Gender");
		tableModel.addColumn("City");
		JTable1.setModel(tableModel);
		
		JTableHeader header = JTable1.getTableHeader();
		
		JScrollPane scroll = new JScrollPane(JTable1);
		
		JMainPanel = new JPanel();
		JInputPanel = new JPanel();
		JTablePanel = new JPanel();
		JCenterPanel = new JPanel();
		JMainPanel.setLayout(new BorderLayout());
		JTablePanel.setLayout(new BorderLayout());
		//JCenterPanel.setLayout(new GridLayout(3, 1));
		JCenterPanel.setLayout(new BorderLayout());
		
		JCity = new JLabel("City: ");
		JBloodGrp = new JLabel("Blood Grp: ");
		
		JSearch = new JButton("Search");
		JCancel = new JButton("Cancel");
		JPrintButton = new JButton("Print");
		JSearch.addActionListener(e -> actionOnClickingSearch(e));
		JPrintButton.addActionListener(e -> actionOnClickingPrint(e));
		
		JComboBloodGrp = new JComboBox(BloodGrp);
		JComboCity = new JComboBox(city);
		
		JInputPanel.add(JCity);
		JInputPanel.add(JComboCity);
		JInputPanel.add(JBloodGrp);
		JInputPanel.add(JComboBloodGrp);
		JInputPanel.add(JSearch);
		JInputPanel.add(JCancel);
		
		JTablePanel.add(header, BorderLayout.NORTH);
		JTablePanel.add(scroll, BorderLayout.CENTER);
		JTablePanel.add("Center", JTable1);
		JTablePanel.add("South", JPrintButton);
		
		
		JMainPanel.add("North", JInputPanel);
		JMainPanel.add("Center", JTablePanel);

		JTable1.setFont(new Font("Calibri", Font.BOLD, 15));
		getContentPane().add(JMainPanel);
	}
	
	public void actionOnClickingPrint(ActionEvent e)
	{
		try
		{
			MessageFormat headerFormat = new MessageFormat("List of Donor: ");
			MessageFormat footerFormat = new MessageFormat("- page (0) -");
			JTable1.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
		}
		catch(Exception ex)
		{
			System.err.println(ex);
		}
	}
	public void actionOnClickingSearch(ActionEvent e)
	{
		DatabaseConnection connect = new DatabaseConnection();
		try
		{
			connect.dataBaseConnection();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString());
		}
		tableModel.setRowCount(0);
		try
		{
			ResultSet rs = connect.executeAndReturn("select * from database where city = '"
			+ JComboCity.getSelectedItem() + "' and bloodgrp = '" + JComboBloodGrp.getSelectedItem() + "'");		
			
			while(rs.next())
			{
				tableModel.addRow(new Object[]
						{rs.getString(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)}
				);
			}
			if(tableModel.getRowCount() == 0)
			{
				JOptionPane.showMessageDialog(null, "Sorrry no donors available");
				System.exit(0);
			}
			JTable1.setModel(tableModel);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}
}
