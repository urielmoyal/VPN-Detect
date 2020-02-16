package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Stock implements Runnable{
	static JFrame stockFrame = new JFrame("Stock Report:");
	static JPanel stockPanel = new JPanel(new GridLayout());
	
	public void run() {
		
		stockFrame.setVisible(true);
  		
		stockPanel.setLayout(new GridLayout(5 , 5, 20, 20));
		stockFrame.add(stockPanel,BorderLayout.CENTER);
		
		for(int i = GUI.t; i < GUI.vehicle.size(); i++, GUI.t++) {
			int j = i;
			stockPanel.add(GUI.carL.get(i),BorderLayout.WEST);
			GUI.carL.get(i).setPreferredSize(new Dimension(110, 110));
			GUI.carL.get(i).addMouseListener(new MouseAdapter()
			{public void mouseEntered(MouseEvent evt){GUI.carL.get(j).setToolTipText("<html><font color = 'red'>Car Info:</font><br>" + "<b>" + GUI.vehicle.get(j).toString() + "</b></html>");}}
			);
	}
		stockFrame.repaint();
		stockFrame.validate();
		stockFrame.setSize(500, 500);
		stockFrame.setLocation(387, 0);
  }
}
