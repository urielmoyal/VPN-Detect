package Agancy;
import java.util.concurrent.locks.ReentrantLock;

import Interfaces.Unmotorized;
import Vehicles.Land_Vehicle;

public class Bike extends Land_Vehicle implements Unmotorized {

	private String power;
	private char energetic;
	private ReentrantLock bikeLock = new ReentrantLock();
	
	public Bike(String model, float distance, int max_pass, float max_speed, int wheel_num, String road, String image) {
	super(model, distance, max_pass, max_speed, 2, road, image);
	power("Manual");
	energetic('A');
	}
	
	public void power(String power) {
		this.power = power;
	}
	
	public void energetic(char energetic) {
		this.energetic = energetic;
	}
	
	public boolean equal(Bike other) {
		if (other instanceof Bike)
			return (super.equal(other));
		return false;
	}
	
	public String toString(){
		return ("Bike: " + super.toString() + ", Power:" + power + ", Energetic:" + energetic);
	}
	
	public void Lock2() {
		bikeLock.lock();
	}
	
	public void unLock2() {
		bikeLock.unlock();
	}
	
	public boolean isLocked2() {
		return bikeLock.isLocked();
	}
	
	public boolean currentThread() {
		return bikeLock.isHeldByCurrentThread();
	}
}
