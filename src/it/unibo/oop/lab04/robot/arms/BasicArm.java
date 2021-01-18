package it.unibo.oop.lab04.robot.arms;

import it.unibo.oop.lab04.robot.base.RobotEnvironment;

public class BasicArm {

    public static final double BATTERY_FULL = 100;
    public static final double MOVEMENT_DELTA_CONSUMPTION = 1.2;
    private static final int MOVEMENT_DELTA = 1;

    private double batteryLevel;
    private final RobotEnvironment environment;
    public boolean isGrabbing;
   // private final String robotName;
    
	public BasicArm(String robotName) {
		super();
		this.environment = null; 
		
		// TODO Auto-generated constructor stub
	}
	
	public boolean isGrabbing() {
		return this.isGrabbing ;
		
	}

	protected void consumeBattery(final double amount) {
        if (batteryLevel >= amount) {
            this.batteryLevel -= amount;
        } else {
            this.batteryLevel = 0;
        }
    }

	private void consumeBatteryForPickUp() {
        consumeBattery(getConsuptionForPickUp());
    }

   

	protected void pickUp() {
		if(this.batteryLevel > getConsuptionForPickUp() && !isGrabbing) {
			isGrabbing = true;
			consumeBatteryForPickUp();
		}
	}
	
	protected void dropDown() {
		if(this.batteryLevel > getConsuptionForDropDown() && isGrabbing) {
			isGrabbing = false;
			consumeBatteryForPickUp();;
		}
	}
	
	private double getConsuptionForPickUp() {
		return MOVEMENT_DELTA;
	}
	
	private double getConsuptionForDropDown() {
		return MOVEMENT_DELTA;}
	
	public String toString(){
		return null;}

}
