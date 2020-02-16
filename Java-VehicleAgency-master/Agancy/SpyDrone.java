package Agancy;
import java.util.concurrent.locks.ReentrantLock;

import Interfaces.Unmotorized;
import Vehicles.Air_Vehicle;

public class SpyDrone extends Air_Vehicle implements Unmotorized {
	private String power;
	private char energetic;
	private ReentrantLock spyDroneLock = new ReentrantLock();

	public SpyDrone(float distance, String power, String image) {
	super("Secret", distance, 1, 50, "Military", image);
	power(power);
	energetic('C');
	}
	
	public void power(String power) {
		this.power = power;
		
	}
	
	public void energetic(char energetic) {
		this.energetic = energetic;
	}
	
	public boolean equal(SpyDrone other) {
		if (other instanceof SpyDrone)
			return (super.equal(other) && power.equals(other.power));
		return false;
	}
	
	public String toString(){
		return ("SpyDrone: " + super.toString() + ", Power:" + power + ", Energetic:" + energetic);
	}
	
	public void Lock2() {
		spyDroneLock.lock();
	}
	
	public void unLock2() {
		spyDroneLock.unlock();
	}
	
	public boolean isLocked2() {
		return spyDroneLock.isLocked();
	}
	
	public boolean currentThread() {
		return spyDroneLock.isHeldByCurrentThread();
	}
}
