package Agancy;

import java.util.concurrent.locks.ReentrantLock;

import Interfaces.Commercial_Vehicles;
import Interfaces.Motorized;
import Vehicles.Sea_Vehicle;

public class CruiseShip extends Sea_Vehicle implements Motorized, Commercial_Vehicles{
	
	private String license = "Unlimited";
	private float fuel;
	private float lifetime;
	private ReentrantLock cruiseShipLock = new ReentrantLock();
	
	
	public CruiseShip(String model, float distance, int max_pass, float max_speed, String flag, float lifetime, float fuel, String image) {
	super(model, distance, max_pass, max_speed, "With the wind", flag, image);
	setfuel(fuel);
	lifetime(lifetime);
	
	}
	
	public boolean equal(CruiseShip other) {
		if (other instanceof CruiseShip)
			return (super.equal(other) && fuel == other.fuel && lifetime == other.lifetime);
		return false;
	}
	
	
	public String toString(){
		return ("CruiseShip: " + super.toString() + ", Engine:" + fuel + "L, lifetime of " + lifetime + " years" + ", License: " + license);
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
	
	public String getLicense() {
		return license;
	}
	
	public void Lock2() {
		cruiseShipLock.lock();
	}
	
	public void unLock2() {
		cruiseShipLock.unlock();
	}
	
	public boolean isLocked2() {
		return cruiseShipLock.isLocked();
	}
	
	public boolean currentThread() {
		return cruiseShipLock.isHeldByCurrentThread();
	}
}
