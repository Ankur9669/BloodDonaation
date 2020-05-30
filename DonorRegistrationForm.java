import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DonorRegistrationForm extends JFrame
{
	JPanel registrationPanel;
	JLabel patientName, patientContact, patientEmail, patientBloodGrp, patientGender, patientCity;
	JTextField fieldName, fieldContact, fieldEmail;
	JComboBox comboBloodGrp, comboCity;
	JButton submitButton, cancelButton;
	JRadioButton JMale, JFemale;
	ButtonGroup Grp1;
	String bloodGrp[] = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
	String city[] = {"Jabalpur", "Bhopal", "Patna", "Nagpur", "Mumbai", "Indore"};
	public DonorRegistrationForm()
	{
		this.setVisible(true);
		this.setTitle("Donor Registration Form");
		this.setBounds(500, 300, 500, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		registrationPanel = new JPanel();
		
		patientName = new JLabel("Name:");
		patientGender = new JLabel("Gender");
		patientContact = new JLabel("Contact:");
		patientEmail = new JLabel("Email:");
		patientBloodGrp = new JLabel("BloodGrp:");
		patientCity = new JLabel("City");
		patientName.setFont(new Font("Consolas", Font.BOLD, 30));
		patientContact.setFont(new Font("Consolas", Font.BOLD, 30));
		patientEmail.setFont(new Font("Consolas", Font.BOLD, 30));
		patientBloodGrp.setFont(new Font("Consolas", Font.BOLD, 30));
		patientCity.setFont(new Font("Consolas", Font.BOLD, 30));
		
		comboBloodGrp = new JComboBox(bloodGrp);
		comboCity = new JComboBox(city);
		
		fieldName = new JTextField(20);
		fieldContact = new JTextField(20);
		fieldEmail = new JTextField(20);
		
		JMale = new JRadioButton("Male");
		JFemale = new JRadioButton("Female");
		JMale.setFont(new Font("Calibri", Font.BOLD, 15));
		JFemale.setFont(new Font("Calibri", Font.BOLD, 15));
		
		Grp1 = new ButtonGroup();
		Grp1.add(JMale);
		Grp1.add(JFemale);
		
		fieldName.setFont(new Font("Consolas", Font.BOLD, 30));
		fieldContact.setFont(new Font("Consolas", Font.BOLD, 30)); 
		fieldEmail.setFont(new Font("Consolas", Font.BOLD, 30)); 
		
		submitButton = new JButton("Submit");
		cancelButton = new JButton("Cancel");
		submitButton.addActionListener(e -> actionOnClickingSubmit(e));
		cancelButton.addActionListener(e -> actionOnClickingCancel(e));
		
		
		registrationPanel.setLayout(new GridLayout(7, 2));
		registrationPanel.add(patientName);
		registrationPanel.add(fieldName);
		/*registrationPanel.add(patientGender);*/
		registrationPanel.add(JMale);
		registrationPanel.add(JFemale);
		registrationPanel.add(patientContact);
		registrationPanel.add(fieldContact);
		registrationPanel.add(patientEmail);
		registrationPanel.add(fieldEmail);
		registrationPanel.add(patientCity);
		registrationPanel.add(comboCity);
		registrationPanel.add(patientBloodGrp);
		registrationPanel.add(comboBloodGrp);
		registrationPanel.add(submitButton);
		registrationPanel.add(cancelButton);
		
		
		
		getContentPane().add(registrationPanel);
		
	}
	
	public void actionOnClickingCancel(ActionEvent e)
	{
		this.dispose();
	}
	
	public void actionOnClickingSubmit(ActionEvent e)
	{
		boolean check1 = false;
		boolean check2 = false;
		String temporary = fieldEmail.getText();
		check1 = checkEmail(temporary);
		temporary = fieldContact.getText();
		check2 = checkNumber(temporary);
		if(check1 == true && check2 == true)
		{
			DatabaseConnection obj = new DatabaseConnection();
			try
			{
				obj.dataBaseConnection();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.toString());
			}
			
			try
			{
				String gender1 = "";
				if(JMale.isSelected())
				{
					gender1 = "male";
				}
				else if(JFemale.isSelected())
				{
					gender1 = "female";
				}
				obj.executeQuery("insert into database values('" + fieldName.getText() + 
						"' ,'" + fieldContact.getText() + "' , '" + fieldEmail.getText() +
						"' , '" + comboBloodGrp.getSelectedItem() + "', '" 
						+ gender1 + "' , '" + comboCity.getSelectedItem() + "')"
						);
				JOptionPane.showMessageDialog(null, "Congrats Your Registration is Complete");
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.toString());
			}
		}
	}
	
	public boolean checkNumber(String temporary)
	{
		boolean check2 = false;
		if(temporary.length() == 10)
		{
			check2 = true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid Number");
		}
		return check2;
	}
	
	public boolean checkEmail(String temporary)
	{
		int i, count = 0;
		boolean check = false;
		if(temporary.contains("@") == true) 
		{
			count = count + 1;
		}
		if(temporary.endsWith(".com") == true)
		{
			count = count + 1 ;
		}
		if(count == 2)
		{
			check = true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid Email");
			this.setVisible(false);
		}
		return check;
	}
}
