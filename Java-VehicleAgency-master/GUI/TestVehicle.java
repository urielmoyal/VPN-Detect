package GUI;

import javax.swing.JOptionPane;

import Vehicles.Vehicle;

public class TestVehicle implements Runnable{
	int tempIndex = GUI.carIndex;

	public void run() {
		try {
			GUI.vehicle.get(tempIndex).Lock();
			if (GUI.vehicle.get(tempIndex).isLocked2())
				JOptionPane.showMessageDialog(null, "Cannot test vehicle that in buy process.", "Error!",JOptionPane.ERROR_MESSAGE);
			
			else {
				GUI.vehicle.get(tempIndex).Lock2();
				int wait=1;
				while(wait==1) {
					try{
							String newDistance = JOptionPane.showInputDialog("Enter New Distance");
							if (newDistance == null) {
								GUI.vehicle.get(tempIndex).move(Float.valueOf(0));
								wait=2;
							}
							
							else {
								GUI.vehicle.get(tempIndex).move(Float.valueOf(newDistance));
								wait=2;
								JOptionPane.showMessageDialog(null, "Successfully Test Vehicle!", "Success!", JOptionPane.DEFAULT_OPTION);
								Thread.sleep(Integer.valueOf(newDistance)*100);
							}
							
					}catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Input Error!", "Error!",JOptionPane.ERROR_MESSAGE);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		finally {
			if(GUI.vehicle.get(tempIndex).isLocked()) {
				GUI.vehicle.get(tempIndex).unLock();
			}
			
			if(GUI.vehicle.get(tempIndex).isLocked2() && GUI.vehicle.get(tempIndex).currentThread()) {
				GUI.vehicle.get(tempIndex).unLock2();
			}
		}
	}
}
