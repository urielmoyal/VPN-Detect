package Agancy;
import java.util.concurrent.locks.ReentrantLock;

import Interfaces.Motorized;
import Vehicles.Sea_Vehicle;

public class Frigate extends Sea_Vehicle implements Motorized{

	private float fuel;
	private float lifetime;
	private ReentrantLock frigateLock = new ReentrantLock();
	
	public Frigate(String model, float distance, int max_pass, float max_speed, String wind, String flag, String image) {
	super(model, distance, max_pass, max_speed, wind, flag, image);
	setfuel(500);
	lifetime(4);
	}
	
	public final void lifetime(float lifetime) {
		this.lifetime = lifetime;
	}
	
	public boolean equal(Frigate other) {
		if (other instanceof Frigate)
			return (super.equal(other));
		return false;
	}
	
	public String toString(){
		return ("Frigate: " + super.toString() + ", Engine:" + fuel + "L, lifetime of " + lifetime + " years");
	}
	
	public final void setfuel(float fuel){
		this.fuel = fuel;
	}
	
	public float getfuel(){
		return fuel;
	}
	
	public void Lock2() {
		frigateLock.lock();
	}
	
	public void unLock2() {
		frigateLock.unlock();
	}
	
	public boolean isLocked2() {
		return frigateLock.isLocked();
	}
	
	public boolean currentThread() {
		return frigateLock.isHeldByCurrentThread();
	}
}
