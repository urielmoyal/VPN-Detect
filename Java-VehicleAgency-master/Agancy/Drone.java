package Agancy;
import java.util.concurrent.locks.ReentrantLock;

import Interfaces.Unmotorized;
import Vehicles.Air_Vehicle;

public class Drone extends Air_Vehicle implements Unmotorized {
	private String power;
	private char energetic;
	private ReentrantLock droneLock = new ReentrantLock();

	public Drone(float distance, String image) {
	super("Toy", distance, 0, 10, "Civilian", image);
	power("Manual");
	energetic('A');
	}

	public void power(String power) {
		this.power = power;
	}
	
	public void energetic(char energetic) {
		this.energetic = energetic;
	}
	
	public boolean equal(Drone other) {
		if (other instanceof Drone)
			return (super.equal(other));
		return false;
	}
	
	public String toString(){
		return ("Drone: " + super.toString() + ", Power:" + power + ", Energetic:" + energetic);
	}

	public void Lock2() {
		droneLock.lock();
	}
	
	public void unLock2() {
		droneLock.unlock();
	}
	
	public boolean isLocked2() {
		return droneLock.isLocked();
	}
	
	public boolean currentThread() {
		return droneLock.isHeldByCurrentThread();
	}
}
