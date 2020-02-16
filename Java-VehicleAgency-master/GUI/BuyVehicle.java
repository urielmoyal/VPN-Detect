package GUI;

import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

import Vehicles.Air_Vehicle;

class BuyVehicle implements Runnable{
	
	int dialogButton = JOptionPane.YES_NO_OPTION;
	int randomNum = ThreadLocalRandom.current().nextInt(5, 10);
	int randomNum2 = ThreadLocalRandom.current().nextInt(3, 8);
	int tempIndex = GUI.carIndex;
	
	public void run() {
		try {
			if (GUI.vehicle.get(tempIndex).isLocked2())
				JOptionPane.showMessageDialog(null, "Cannot buy vehicle in test.", "Error!",JOptionPane.ERROR_MESSAGE);
				
			else {
				GUI.vehicle.get(tempIndex).Lock2();
				try {
					Thread.sleep(randomNum * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to buy?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					try {
					GUI.loadingFrame.setVisible(true);
					Thread.sleep(randomNum2 * 1000);
					GUI.loadingFrame.setVisible(false);
			  		CarB.vehicleOptionFrame.setVisible(false);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
					GUI.Panel3.remove(GUI.carB.get(tempIndex));
					Stock.stockPanel.remove(GUI.carL.get(tempIndex));
					GUI.s--;
					GUI.t--;
					GUI.Panel3.repaint();
					GUI.Panel3.validate();
			}
		}
	}		
		finally {
			if (GUI.vehicle.get(tempIndex).isLocked2() && GUI.vehicle.get(tempIndex).currentThread()) {
				GUI.vehicle.get(tempIndex).unLock2();
			}
		}
	}
}


