import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Booking extends JFrame
{
	JPanel JMainPanel, JProceedPanel;
	JButton JSubmit, JCancel;
	JLabel JPatientName, JDonorName, JDate, JCity, JNoOfUnit, JBloodGrp;
	JTextField JFieldPatientName, JFieldDate, JFieldNoOfUnit, JFieldBloodGrp;
	JComboBox JComboCity, JComboBloodGrp, JComboDonorName;
	String BloodGrp[] = {"A+", "A-", "B+", "B-" ,"AB+", "AB-", "O+", "O-"};
	String city[] = {"Jabalpur", "Bhopal", "Patna", "Nagpur", "Mumbai", "Indore"};
	public Booking()
	{
		this.setVisible(true);
		this.setBounds(100, 100, 500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSubmit = new JButton("Submit");
		JCancel = new JButton("Cancel");
		JSubmit.addActionListener(e -> actionOnClickingSubmit(e));
		JCancel.addActionListener(e -> actionOnClickingCancel(e));
		
		JMainPanel = new JPanel();
		JProceedPanel = new JPanel();
		JMainPanel.setLayout(new GridLayout(7, 2));
		//JProceedPanel.setLayout(new GridLayout(1, 2));
		
		JCity = new JLabel("City");
		JPatientName = new JLabel("Patient Name: ");
		JBloodGrp = new JLabel("Blood Group: ");
		JDonorName = new JLabel("Donor Name: ");
		JDate = new JLabel("Date: ");
		JNoOfUnit = new JLabel("No of Unit: ");
		
		JFieldBloodGrp = new JTextField(20);
		JFieldPatientName = new JTextField(20);
		//JFieldDonorName = new JTextField(20);
		JFieldDate = new JTextField(20);
		JFieldNoOfUnit = new JTextField(20);
		
		JComboCity = new JComboBox(city);
		JComboBloodGrp = new JComboBox(BloodGrp);
		JComboDonorName = new JComboBox();
		
		JProceedPanel.add(JSubmit);
		JProceedPanel.add(JCancel);
		
		JMainPanel.add(JPatientName);
		JMainPanel.add(JFieldPatientName);
		JMainPanel.add(JDonorName);
		JMainPanel.add(JComboDonorName);
		JMainPanel.add(JCity);
		JMainPanel.add(JComboCity);
		JMainPanel.add(JBloodGrp);
		JMainPanel.add(JComboBloodGrp);
		JMainPanel.add(JDate);
		JMainPanel.add(JFieldDate);
		JMainPanel.add(JNoOfUnit);
		JMainPanel.add(JFieldNoOfUnit);
		JMainPanel.add(JSubmit);
		JMainPanel.add(JCancel);
		
		
		getContentPane().add(JMainPanel);
		
		DatabaseConnection connect = new DatabaseConnection();
		try
		{
			connect.dataBaseConnection();
			ResultSet rs = connect.executeAndReturn("select * from database");
			while(rs.next())
			{
				JComboDonorName.addItem(rs.getString(1));
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}
	
	public void actionOnClickingSubmit(ActionEvent e) 
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
		try
		{
			connect.executeQuery("insert into boooking values('" + 
			JFieldPatientName.getText() + "' , '" + JComboDonorName.getSelectedItem() + "' , '"
					+ JComboCity.getSelectedItem() +
			"' , '" + JComboBloodGrp.getSelectedItem() + "' , '"
					+ JFieldDate.getText() + "' , "
			+ JFieldNoOfUnit.getText() + ")");
			JOptionPane.showMessageDialog(null, "Submitted");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}
	
	public void actionOnClickingCancel(ActionEvent e)
	{
		JOptionPane.showMessageDialog(null, "Bye Bye");
		System.exit(0);
	}
}
