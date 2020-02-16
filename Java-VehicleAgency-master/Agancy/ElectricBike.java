package Agancy;
import java.util.concurrent.locks.ReentrantLock;

import Interfaces.Motorized;

public class ElectricBike extends Bike implements Motorized {
	
	private float fuel;
	private float lifetime;
	private ReentrantLock electricBikeLock = new ReentrantLock();
	
	public ElectricBike(String model, float distance, int max_pass, float max_speed, String road, float lifetime, String image) {
		super(model, distance, max_pass, max_speed, 2, road, image);
		lifetime(lifetime);
		setfuel(20);
	}
	
	public boolean equal(ElectricBike other) {
		if (other instanceof ElectricBike)
			return (super.equal(other) && lifetime == other.lifetime);
		return false;
	}
	
	public String toString(){
		return ("ElectricBike: " + super.toString() + ", Engine: " + fuel + "L, lifetime of " + lifetime + " years");
	}
	
	public final void lifetime(float lifetime) {
		this.lifetime = lifetime;
	}
	
	public final void setfuel(float fuel){
		this.fuel = fuel;
	}
	
	public float getfuel(){	
		return fuel;
	}
	
	public void Lock2() {
		electricBikeLock.lock();
	}
	
	public void unLock2() {
		electricBikeLock.unlock();
	}
	
	public boolean isLocked2() {
		return electricBikeLock.isLocked();
	}
	
	public boolean currentThread() {
		return electricBikeLock.isHeldByCurrentThread();
	}
}