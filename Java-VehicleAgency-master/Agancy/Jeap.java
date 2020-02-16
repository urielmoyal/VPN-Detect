package Agancy;
import java.util.concurrent.locks.ReentrantLock;

import Interfaces.Commercial_Vehicles;
import Interfaces.Motorized;
import Vehicles.Land_Vehicle;

public class Jeap extends Land_Vehicle implements Motorized, Commercial_Vehicles{
	private String license = "Mini";
	private float fuel;
	private float lifetime;
	private ReentrantLock jeapLock = new ReentrantLock();
	
	public Jeap(String model, float distance, float max_speed ,float fuel, float lifetime, String image) {
	super(model, distance, 5, max_speed, 4, "Dust", image);
	setfuel(fuel);
	lifetime(lifetime);
	}

	public final void lifetime(float lifetime) {
		this.lifetime = lifetime;
	}
	
	public String getLicense() {
		return license;
	}
	
	public boolean equal(Jeap other) {
		if (other instanceof Jeap)
			return (super.equal(other) && fuel == other.fuel && lifetime == other.lifetime);
		return false;
	}
	
	public String toString(){
		return ("Jeap: " + super.toString() + ", Engine: " + fuel + "L, lifetime of " + lifetime + " years" + ", License: " + license);
	}
	
	public final void setfuel(float fuel){
		this.fuel = fuel;
	}
	
	public float getfuel(){
		return fuel;
	}
	
	public void Lock2() {
		jeapLock.lock();
	}
	
	public void unLock2() {
		jeapLock.unlock();
	}
	
	public boolean isLocked2() {
		return jeapLock.isLocked();
	}
	
	public boolean currentThread() {
		return jeapLock.isHeldByCurrentThread();
	}

}
