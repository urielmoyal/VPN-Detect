package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Flag implements Runnable, ActionListener{
	
	JFrame flagFrame = new JFrame("Flag Option:");
	JPanel flagPane1 = new JPanel(new GridLayout());
  	JPanel flagPane2 = new JPanel(new GridLayout());
	
  	JRadioButton radioButton1 = new JRadioButton();
  	JRadioButton radioButton2 = new JRadioButton();
  	JRadioButton radioButton3 = new JRadioButton();
  	JRadioButton radioButton4 = new JRadioButton();
  	JRadioButton radioButton5 = new JRadioButton();
  	JRadioButton radioButton6 = new JRadioButton();
  	JRadioButton radioButton7 = new JRadioButton();
  	
	JButton changeFlagButton = new JButton("Change Flag");
  	
  	JLabel label1 = new JLabel(GUI.createImageIcon("images/Israel.jpg"));
  	JLabel label2 = new JLabel(GUI.createImageIcon("images/United States.jpg"));
  	JLabel label3 = new JLabel(GUI.createImageIcon("images/Germany.jpg"));
  	JLabel label4 = new JLabel(GUI.createImageIcon("images/Italy.jpg"));
  	JLabel label5 = new JLabel(GUI.createImageIcon("images/Greece.jpg"));
  	JLabel label6 = new JLabel(GUI.createImageIcon("images/Somalia.jpg"));
  	JLabel label7 = new JLabel(GUI.createImageIcon("images/Pirates.jpg"));
	
	ButtonGroup group = new ButtonGroup();
  	
	public Flag() {
  	  	radioButton1.setActionCommand("Israel");
  	  	radioButton1.addActionListener(this);
  	  	radioButton1.setSelected(true);
  	  	
  	  	radioButton2.setActionCommand("United States");
  	  	radioButton2.addActionListener(this);
  	  	
  	  	radioButton3.setActionCommand("Germany");
  	  	radioButton3.addActionListener(this);
  	  	
  	  	radioButton4.setActionCommand("Italy");
  	  	radioButton4.addActionListener(this);
  	  	
  	  	radioButton5.setActionCommand("Greece");
  	  	radioButton5.addActionListener(this);
  	  	
  	  	radioButton6.setActionCommand("Somalia");
  	  	radioButton6.addActionListener(this);
  	  	
  	  	radioButton7.setActionCommand("Pirates");
  	  	radioButton7.addActionListener(this);
  	  	
  		changeFlagButton.addActionListener(this);
  		
		flagPane1.add(radioButton1);
		flagPane2.add(label1);
		flagPane1.add(radioButton2);
		flagPane2.add(label2);
		flagPane1.add(radioButton3);
		flagPane2.add(label3);
		flagPane1.add(radioButton4);
		flagPane2.add(label4);
		flagPane1.add(radioButton5);
		flagPane2.add(label5);
		flagPane1.add(radioButton6);
		flagPane2.add(label6);
		flagPane1.add(radioButton7);
		flagPane2.add(label7);
		flagPane2.add(changeFlagButton);

        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);
        group.add(radioButton4);
        group.add(radioButton5);
        group.add(radioButton6);
        group.add(radioButton7);

        flagFrame.add(flagPane1,BorderLayout.CENTER);
        flagFrame.add(flagPane2,BorderLayout.PAGE_END);
		
        flagPane1.setPreferredSize(new Dimension(0, 50));
        flagPane2.setPreferredSize(new Dimension(900, 150));
			
        flagPane1.setBorder(BorderFactory.createEmptyBorder(10,50,10,50));
        flagFrame.setResizable(false);
        flagFrame.setVisible(true);
        flagFrame.setLocation(841, 242);
        flagFrame.pack();
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == changeFlagButton){
  			Thread flag = new Thread(this);
  			flag.start();
		}
	}
	
	public void run() {
		if (GUI.s == 0)
			JOptionPane.showMessageDialog(null, "No Car In Agancy!", "Error!",JOptionPane.ERROR_MESSAGE);
		else{
			for(int i = 0 ; i < GUI.seaVehicle.size(); i++){
				GUI.seaVehicle.get(i).setflag(group.getSelection().getActionCommand());
			}
			JOptionPane.showMessageDialog(null, "Successfully Change Flag!", "Success!", JOptionPane.DEFAULT_OPTION);
			flagFrame.setVisible(false);
		}
	}
}
