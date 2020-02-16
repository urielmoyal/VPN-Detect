package Agancy;
import java.util.concurrent.locks.ReentrantLock;

import Interfaces.Motorized;
import Vehicles.Air_Vehicle;
import Vehicles.Land_Vehicle;
import Vehicles.Sea_Vehicle;

public class HybridPlane extends Sea_Vehicle implements Motorized{

	private Air_Vehicle air;
	private Land_Vehicle land;
	private float fuel;
	private float lifetime;
	private ReentrantLock hybridPlaneLock = new ReentrantLock();
	
	public HybridPlane(String model, float distance, int max_pass, float max_speed, String wind, String flag, float lifetime, float fuel, int wheel_num, String image) {
		super(model, distance, max_pass, max_speed, wind, flag, image);
		land = new Land_Vehicle(model, distance, max_pass, max_speed, wheel_num, "Paved path", image);
		air = new Air_Vehicle(model, distance, max_pass, max_speed, "Military", image);
		lifetime(lifetime);
		setfuel(fuel);
	}
	
	public boolean equal(HybridPlane other) {
		if (other instanceof HybridPlane)
			return (super.equal(other) && fuel == other.fuel && lifetime == other.lifetime && land.getWheelNum() == other.land.getWheelNum());
		return false;
	}
	
	public String toString(){
		return ("HybridPlane: " + super.toString() + ", Road: " +  land.getRoad() + ", Usage: " +  air.getusage() + ", Wheel_num: " + land.getWheelNum() + ", Engine: " + fuel + "L, lifetime of " + lifetime + " years");
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
		hybridPlaneLock.lock();
	}
	
	public void unLock2() {
		hybridPlaneLock.unlock();
	}
	
	public boolean isLocked2() {
		return hybridPlaneLock.isLocked();
	}
	
	public void Lock() {
		air.Lock();
		land.Lock();
		super.Lock();
	}
	
	public void unLock() {
		air.unLock();
		land.unLock();
		super.unLock();
	}
	
	public boolean isLocked() {
		return air.isLocked() && land.isLocked() && super.isLocked();
	}
	
	public boolean currentThread() {
		return hybridPlaneLock.isHeldByCurrentThread();
	}
}
