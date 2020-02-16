package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CarB{
	
  	static JFrame vehicleOptionFrame = new JFrame("Option:");
  	JPanel vehicleOptionPanel = new JPanel();
  	JPanel vehicleOptionPane2 = new JPanel();
  	JLabel optionChoose = new JLabel("Choose Your Option:");    

	
  	public CarB() {
  		vehicleOptionPanel.removeAll();
  		vehicleOptionPane2.removeAll();
  		vehicleOptionPanel.add(optionChoose);
  		vehicleOptionPane2.add(Finish.buyButton);
  		vehicleOptionPane2.add(Finish.testButton);
  		vehicleOptionFrame.add(vehicleOptionPanel,BorderLayout.CENTER);
  		vehicleOptionFrame.add(vehicleOptionPane2,BorderLayout.PAGE_END);	

  		
  		vehicleOptionFrame.setResizable(false);
  		vehicleOptionFrame.setVisible(true);
  		vehicleOptionFrame.setLocation(841, 0);
  		vehicleOptionFrame.pack();
  	}
}
