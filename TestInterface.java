import java.awt.BorderLayout;

import javax.swing.*;

public class TestInterface extends JFrame
{
	
	JPanel JMainPanel, JCenterPanel, JNorthPanel;
	JButton b1, b2, b3;
	public TestInterface()
	{
		this.setBounds(500, 300, 500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMainPanel = new JPanel();
		JCenterPanel = new JPanel();
		JNorthPanel = new JPanel();
		JMainPanel.setLayout(new BorderLayout());		
		
		b1 = new JButton(new ImageIcon("C:\\Users\\Ankur gupta\\Downloads"));
		b2 = new JButton();
		b3 = new JButton();
		
		JCenterPanel.add(b1);
		JMainPanel.add("Center", b1);
		getContentPane().add(JMainPanel);
	}
	public static void main(String args[])
	{
		new TestInterface();
	}
}
