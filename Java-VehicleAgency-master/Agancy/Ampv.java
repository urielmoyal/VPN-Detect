package Agancy;

import java.util.concurrent.locks.ReentrantLock;

import Interfaces.Motorized;
import Vehicles.Land_Vehicle;
import Vehicles.Sea_Vehicle;

public class Ampv extends Sea_Vehicle implements Motorized{

	private Land_Vehicle land;
	private float fuel;
	private float lifetime;
	private ReentrantLock ampvLock = new ReentrantLock();
	
	public Ampv(String model, float distance, int max_pass, float max_speed, String wind, String flag, float lifetime, float fuel, int wheel_num, String image) {
		super(model, distance, max_pass, max_speed, wind, flag, image);
		land = new Land_Vehicle(model, distance, max_pass, max_speed, wheel_num, "Paved path", image);
		lifetime(lifetime);
		setfuel(fuel);
	}
	
	public boolean equal(Ampv other) {
		if (other instanceof Ampv)
			return (super.equal(other) && fuel == other.fuel && lifetime == other.lifetime && land.getWheelNum() == other.land.getWheelNum());
		return false;
	}
	
	public String toString(){
		return ("Ampv: " + super.toString() + ", Road: " +  land.getRoad() + ", Wheel_num: " + land.getWheelNum() + ", Engine: " + fuel + "L, lifetime of " + lifetime + " years");
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
		ampvLock.lock();
	}
	
	public void unLock2() {
		ampvLock.unlock();
	}
	
	public boolean isLocked2() {
		return ampvLock.isLocked();
	}
	
	public void Lock() {
		land.Lock();
		super.Lock();
	}
	
	public void unLock() {
		land.unLock();
		super.unLock();
	}
	
	public boolean isLocked() {
		return land.isLocked() && super.isLocked();
	}
	
	public boolean currentThread() {
		return ampvLock.isHeldByCurrentThread();
	}
}
