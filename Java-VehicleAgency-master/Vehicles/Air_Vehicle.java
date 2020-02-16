package Vehicles;

import java.util.concurrent.locks.ReentrantLock;

public class Air_Vehicle extends Vehicle{
	private String usage;
	private static ReentrantLock airLock = new ReentrantLock();
	
	public Air_Vehicle(String model, float distance, int max_pass, float max_speed, String usage, String image) {
		super(model, distance, max_pass, max_speed, image);
		this.usage = usage;	
	}
	
	public boolean equal(Air_Vehicle other) {
		if (other instanceof Air_Vehicle)
			return (super.equal(other) && usage == other.usage);
		return false;
	}

	public String toString(){
		return (super.toString() + ", Usage:" + usage);
	}
	
	public void setflag(String usage){
		this.usage = usage;
	}
	
	
	public String getusage(){
		return usage;
	}
	
	public void Lock2() {
	}
	
	public void unLock2() {
	}
	
	public void Lock() {
		airLock.lock();
	}
	
	public void unLock() {
		airLock.unlock();
	}
	
	public boolean isLocked() {
		return airLock.isLocked();
	}
	
	public boolean isLocked2() {
		return false;
	}
	
	public boolean currentThread() {
		return false;
	}
}
