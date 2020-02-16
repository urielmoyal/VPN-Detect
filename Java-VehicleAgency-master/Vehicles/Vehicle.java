package Vehicles;
import javax.swing.JPanel;

public abstract class Vehicle {
	protected JPanel objPan;
	private String model;
	private float distance = 0;
	private int max_pass;
	private float max_speed;
	private String image;
	
	Vehicle(String model, float distance, int max_pass, float max_speed, String image) {
		this.model = model;
		this.distance = distance;
		this.max_pass = max_pass;
		this.max_speed = max_speed;
		this.image = image;
	}
	
	public void move(float x){
		this.distance += x;
	}
	
	public String getModel(){
		return model;
	}
	
	public float getDistance(){
		return distance;
	}
	
	public int getMaxSass(){
		return max_pass;
	}
	
	public float getMaxSpeed(){
		return max_speed;
	}
	
	public boolean equal(Vehicle other) {
		if (other instanceof Vehicle)
			return (model.equals(other.model) && distance == other.distance && max_pass == other.max_pass && max_speed == other.max_speed);
		return false;
	}
	
	public String toString(){
		return ("Model:" + model + ", traveled:" + distance + "Km, Max speed of " + max_speed + " Mph,"
				+ " Can carry max of " + max_pass + " people");
	}
	
	public String getImage(){
		return image;
	}
	
	public abstract void Lock();
	public abstract void Lock2();
	public abstract void unLock();
	public abstract void unLock2();
	public abstract boolean isLocked();
	public abstract boolean isLocked2();
	public abstract boolean currentThread();
}
