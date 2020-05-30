import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainInterface extends JFrame 
{
	JPanel JMainPanel, JCenterPanel;
	JMenuBar Jmb;
	JMenu JMaster, JTransaction, JReport;
	JMenuItem JDonorRegistrationForm, JSearch, JExit;
	JMenuItem JBooking;
	JMenuItem JTotalRegistrations, JTotalBookings;
	JLabel displayLabel;
	JButton b1;
	
	public MainInterface()
	{
		this.setBounds(500, 300, 500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		JMainPanel = new JPanel();
		JCenterPanel = new JPanel();
		
		Jmb = new JMenuBar();
		JMaster = new JMenu("Master");
		JTransaction = new JMenu("Transaction");
		JReport = new JMenu("Report");
		
		JSearch = new JMenuItem("Search");
		JDonorRegistrationForm = new JMenuItem("New Registration of Donor");
		JExit = new JMenuItem("Exit");
		JBooking = new JMenuItem("Booking");
		JTotalRegistrations = new JMenuItem("Total no. of Registrations");
		JTotalBookings = new JMenuItem("Total no. of bookings");
		
		displayLabel = new JLabel("Blood Donation");
		displayLabel.setFont(new Font("Consolas", Font.BOLD, 80));
		displayLabel.setForeground(Color.red);
		
		JMaster.add(JDonorRegistrationForm);
		JMaster.add(JSearch);
		JMaster.add(JExit);
		
		JTransaction.add(JBooking);
		JReport.add(JTotalRegistrations);
		JReport.add(JTotalBookings);
		
		//b1 = new JButton(new ImageIcon("icons8-image-100.png"));
		b1 = new JButton("Hello");
		JBooking.addActionListener(e -> actionOnClickingBooking(e));
		JDonorRegistrationForm.addActionListener(e -> actionOnClickingRegistrationForm(e));
		JSearch.addActionListener(e -> actionOnClickingSearch(e));
		JExit.addActionListener(e -> actionOnClickingExit(e));
		JTotalRegistrations.addActionListener(e -> actionOnClickingTotalRegistrations(e));
		JTotalBookings.addActionListener(e -> actionOnClickingTotalBookings(e));
		
		Jmb.add(JMaster);
		Jmb.add(JTransaction);
		Jmb.add(JReport);
		
		JCenterPanel.add(b1);
		setJMenuBar(Jmb);
		this.setTitle("Blood Donation");
		//this.add(JCenterPanel);
		getContentPane().add(JCenterPanel);
		getContentPane().setBackground(Color.black);
		this.setBounds(250, 150, 1000, 600);
		this.add(displayLabel);
		
	}
	
	void actionOnClickingTotalRegistrations(ActionEvent e)
	{
		new TotalRegistrations();
	}
	
	void actionOnClickingTotalBookings(ActionEvent e)
	{
		new TotalBooking();
	}
	
	void actionOnClickingSearch(ActionEvent e)
	{
		new Search();
	}
	
	void actionOnClickingRegistrationForm(ActionEvent e)
	{
		new DonorRegistrationForm();
	}
	
	void actionOnClickingExit(ActionEvent e)
	{
		System.exit(0);
	}
	
	void actionOnClickingBooking(ActionEvent e)
	{
		new Booking();
	}
	public static void main(String[] args)
	{
		new MainInterface();
	}
}
