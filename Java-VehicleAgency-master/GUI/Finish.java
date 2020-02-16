package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Finish implements Runnable, ActionListener{
	
	static JButton buyButton = new JButton("Buy Vehicle");
	static JButton testButton = new JButton("Test Vehicle");
	
	JButton addButton = new JButton("Add Vehicle");
	JButton flagButton = new JButton("Change Flag");
	JButton resetButton = new JButton("Reset Ditance");
	JButton exitButton = new JButton("Exit");
	
	JPanel Panel2 = new JPanel();
	
	public Finish() {
  		addButton.addActionListener(this);
  		resetButton.addActionListener(this);
  		exitButton.addActionListener(this);
  		flagButton.addActionListener(this);
  		
  		buyButton.addActionListener(this);
  		testButton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(GUI.carB != null) {
			for(int i = 0; i < GUI.carB.size(); i++) {
		  		if (e.getSource() == GUI.carB.get(i)) {
		  			CarB carb = new CarB();
					GUI.carIndex = i;
				}
			}
			
	  		if (e.getSource() == buyButton){
	  			Thread buyVehicle = new Thread(new BuyVehicle());
	  			buyVehicle.start();
	  		}
	  		
	  		if (e.getSource() == testButton){
	  			Thread testVehicle = new Thread(new TestVehicle());
	  			testVehicle.start();
	  		}
		}
		
		if (e.getSource() == addButton) {
			
			GUI.frame.setVisible(true);
		}
		
		if (e.getSource() == resetButton){
  			Thread reset = new Thread(new Reset());
  			reset.start();
		}
		
		if (e.getSource() == exitButton){
			if(GUI.vehicle.get(GUI.carIndex).isLocked() || GUI.vehicle.get(GUI.carIndex).isLocked2())
				JOptionPane.showMessageDialog(null, "Cannot exit until all process finished.", "Error!",JOptionPane.ERROR_MESSAGE);
			else
				System.exit(0);
		}
		
		if (e.getSource() == flagButton){
			Flag flag = new Flag();
		}
	}
	
	public void run() {
		GUI.carFrame.setVisible(true);
  		
		Panel2.setLayout(new GridLayout());
		GUI.Panel3.setLayout(new GridLayout(5 , 5, 20, 20));
		GUI.carFrame.add(Panel2, BorderLayout.PAGE_END);
		GUI.carFrame.add(GUI.Panel3,BorderLayout.CENTER);

  		for(int i = GUI.s; i < GUI.vehicle.size(); i++, GUI.s++) {
  			int j = i;
  			GUI.Panel3.add(GUI.carB.get(i),BorderLayout.WEST);
  			GUI.carB.get(i).setPreferredSize(new Dimension(110, 110));
  			GUI.carB.get(i).addActionListener(this);
  			GUI.carB.get(i).addMouseListener(new MouseAdapter()
  			{public void mouseEntered(MouseEvent evt){GUI.carB.get(j).setToolTipText("<html><font color = 'red'>Car Info:</font><br>" + "<b>" + GUI.vehicle.get(j).toString() + "</b></html>");}}
  			);
  		}
  		
  		GUI.carFrame.add(GUI.Panel3);

  		Panel2.add(addButton);
  		Panel2.add(flagButton);
  		Panel2.add(resetButton);
  		Panel2.add(exitButton);
  		
  		GUI.frame.repaint();
  		GUI.frame.validate();
  		GUI.carFrame.setVisible(true);
  		GUI.carFrame.setLocation(387, 0);
  		GUI.carFrame.pack();
	}

}
