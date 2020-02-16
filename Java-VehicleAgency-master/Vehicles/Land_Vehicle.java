package Vehicles;

import java.util.concurrent.locks.ReentrantLock;

public class Land_Vehicle extends Vehicle {
	private int wheel_num;
	private String road;
	private static ReentrantLock landLock = new ReentrantLock();
	
	public Land_Vehicle(String model, float distance, int max_pass, float max_speed, int wheel_num, String road, String image) {
		super(model, distance, max_pass, max_speed, image);
		this.wheel_num = wheel_num;
		this.road = road;
	}
	
	public boolean equal(Land_Vehicle other) {
		if (other instanceof Land_Vehicle)
			return (super.equal(other) && wheel_num == other.wheel_num && road.equals(other.road));
		return false;
	}
	
	public String toString(){
		return (super.toString() + ", Road: " +  road + ", Wheel_num: " + wheel_num);
	}
	
	public int getWheelNum(){
		return wheel_num;
	}
	
	public String getRoad(){
		return road;
	}
	
	public void Lock2() {
	}
	
	public void unLock2() {
	}
	
	public void Lock() {
		landLock.lock();
	}
	
	public void unLock() {
		landLock.unlock();
	}
	
	public boolean isLocked() {
		return landLock.isLocked();
	}
	
	public boolean isLocked2() {
		return false;
	}
	
	public boolean currentThread() {
		return false;
	}
	
}
