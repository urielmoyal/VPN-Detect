package GUI;

import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

public class Reset implements Runnable{
	int randomNum = ThreadLocalRandom.current().nextInt(3, 8);

	public void run() {
		if (GUI.s == 0)
			JOptionPane.showMessageDialog(null, "No Car In Agancy!", "Error!",JOptionPane.ERROR_MESSAGE);
		else{
			for(int i = 0; i < GUI.vehicle.size(); i++) {
				GUI.vehicle.get(i).move(-GUI.vehicle.get(i).getDistance());
		  	}
			try {
			GUI.loadingFrame.setVisible(true);
			Thread.sleep(randomNum * 1000);
			GUI.loadingFrame.setVisible(false);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	   }
	
	}
}

