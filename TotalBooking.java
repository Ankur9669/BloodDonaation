import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TotalBooking extends JFrame
{
	JPanel JTablePanel, JMainPanel, JInputPanel;
	JTable JTable1;
	DefaultTableModel tableModel;
	JButton JShow, JPrint;
	public TotalBooking()
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

		//tableModel = new DefaultTableModel();
		tableModel.addColumn("Patient Name");
		tableModel.addColumn("Donor Name");
		tableModel.addColumn("City");
		tableModel.addColumn("BloodGroup");
		tableModel.addColumn("Date");
		tableModel.addColumn("No Of Unit");
		tableModel.addColumn("Contact");
		tableModel.addColumn("Email");
		
		JTable1.setModel(tableModel);
		JTablePanel.setBorder(BorderFactory.createTitledBorder("List of Donors: "));
		JPrint.setBorder(BorderFactory.createTitledBorder(""));
		
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
			ResultSet rs = connect.executeAndReturn("select * from database join boooking on "
					+ "database.name = boooking.donorname");
			while(rs.next())
			{
				tableModel.addRow(new Object[]
						{rs.getString(7),
								rs.getString(8),
								rs.getString(9),
								rs.getString(10),
								rs.getString(11),
								rs.getString(12),
								rs.getString(2),
								rs.getString(3),
						//		rs.getString(12),
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
}



