package Vehicles;

import java.util.concurrent.locks.ReentrantLock;

public abstract class Sea_Vehicle extends Vehicle {
	private String wind;
	private String flag;
	private static ReentrantLock seaLock = new ReentrantLock();
	
	public Sea_Vehicle(String model, float distance, int max_pass, float max_speed, String wind, String flag, String image) {
		super(model, distance, max_pass, max_speed, image);
		this.wind = wind;
		this.flag = flag;
	}
	
	public boolean equal(Sea_Vehicle other) {
		if (other instanceof Sea_Vehicle)
			return (super.equal(other) && wind.equals(other.wind)  && flag.equals(other.flag));
		return false;
	}
	
	public String toString(){
		return (super.toString() + ", Under " +  flag + " flag" + ", " + wind);
	}
	
	public void setwind(String wind){
		this.wind = wind;
	}
	
	public String getwind(){
		return wind;
	}
	
	public void setflag(String flag){
		this.flag = flag;
	}
	
	public String getflag(){
		return flag;
	}
	
	public void Lock() {
		seaLock.lock();
	}
	
	public void unLock() {
		seaLock.unlock();
	}
	
	public boolean isLocked() {
		return seaLock.isLocked();
	}
}
