import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.MessageFormat;
import javax.swing.table.*;
 
public class TotalRegistrations extends JFrame
{
	JPanel JTablePanel, JMainPanel, JInputPanel;
	JTable JTable1;
	DefaultTableModel tableModel;
	JButton JShow, JPrint;
	public TotalRegistrations()
	{
		this.setVisible(true);
		this.setBounds(100, 100, 500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMainPanel = new JPanel();
		JTablePanel = new JPanel();
		JInputPanel = new JPanel();
		JMainPanel.setLayout(new BorderLayout());
		JTablePanel.setLayout(new BorderLayout());
		
		JShow = new JButton("Show");
		JPrint = new JButton("Print");
		JShow.addActionListener(e -> actionOnClickingShow(e));
		
		JTable1 = new JTable();
		tableModel = new DefaultTableModel();

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Name");
		tableModel.addColumn("Contact");
		tableModel.addColumn("Email");
		tableModel.addColumn("BloodGroup");
		tableModel.addColumn("Gender");
		tableModel.addColumn("City");
		JTable1.setModel(tableModel);
		JTablePanel.setBorder(BorderFactory.createTitledBorder("List of Donors: "));
		JPrint.setBorder(BorderFactory.createTitledBorder(""));
	
		JPrint.addActionListener(e -> actionOnClickingPrint(e));
		
		JTableHeader header = JTable1.getTableHeader();
		
		JInputPanel.add(JShow);
		
		JTablePanel.add(header, BorderLayout.NORTH);
		JTablePanel.add("Center", JTable1);
		JTablePanel.add("South", JPrint);
		
		JMainPanel.add("North", JInputPanel);
		JMainPanel.add("Center", JTablePanel);
		
		JTable1.setFont(new Font("Calibri", Font.BOLD, 15));
		
		getContentPane().add(JMainPanel);
	}
	
	public void actionOnClickingShow(ActionEvent e)
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
			ResultSet rs = connect.executeAndReturn("select * from database");
			while(rs.next())
			{
				tableModel.addRow(new Object[]
						{rs.getString(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6),
						}
				);
				if(tableModel.getRowCount() == 0)
				{
					JOptionPane.showMessageDialog(null, "Sorrry no donors available");
					System.exit(0);
				}
				JTable1.setModel(tableModel);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString());
		}
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
}
