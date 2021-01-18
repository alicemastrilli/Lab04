package it.unibo.oop.lab04.robot.arms;

import it.unibo.oop.lab04.robot.base.*;

public class RobotWithTwoArms extends BaseRobot implements RobotWithArms {
    public static final double BATTERY_FULL = 100;
    public static final double MOVEMENT_DELTA_CONSUMPTION = 1.2;
    private static final int MOVEMENT_DELTA = 1;
    public static final int MAX_ITEMS=2;
    public static final double TRASPORT_OBJECT = 0.1;
	 private double batteryLevel;
	 private  RobotEnvironment environment;
	 private String robotName;
	 private int CarriedItem;
	private BasicArm leftArm;
	 private BasicArm rightArm;




	public RobotWithTwoArms(final String robotName ) {
	
		super(robotName);
		this.leftArm = new BasicArm("Left");
		this.rightArm = new BasicArm("Right");
		this.environment = new RobotEnvironment(new RobotPosition(0, 0));
        
        this.batteryLevel = BATTERY_FULL;
        this.CarriedItem = 0;
		// TODO Auto-generated constructor stub
	}
	private boolean move(final int dx, final int dy) {
        if (isBatteryEnough(getBatteryRequirementForMovement())) {
            if (environment.move(dx, dy)) {
                consumeBatteryForMovement();
                log("Moved to position " + environment.getPosition() + ". Battery: " + getBatteryLevel() + "%.");
                return true;
            }
            log("Can not move of (" + dx + "," + dy
                    + ") the robot is touching the world boundary: current position is " + environment.getPosition());
        } else {
            log("Can not move, not enough battery. Required: " + getBatteryRequirementForMovement()
                + ", available: " + batteryLevel + " (" + getBatteryLevel() + "%)");
        }
        return false;
    }
	 protected void consumeBattery(final double amount) {
	        if (batteryLevel >= amount) {
	            this.batteryLevel -= amount;
	        } else {
	            this.batteryLevel = 0;
	        }
	    }

	    /**
	     * Consume the amount of energy required to move the robot substracting it
	     * from the current battery level
	     */
	    private void consumeBatteryForMovement() {
	        consumeBattery(getBatteryRequirementForMovement());
	    }

	    protected double getBatteryRequirementForMovement() {
	        return MOVEMENT_DELTA_CONSUMPTION + (getCarriedItemsCount() * TRASPORT_OBJECT);
	    }
	    
	    
	@Override
	public boolean pickUp() {
		
		if(!leftArm.isGrabbing() && isBatteryEnough( getBatteryRequirementForMovement())) {
			leftArm.pickUp();
			this.CarriedItem++;
			leftArm.isGrabbing = true;
			consumeBatteryForMovement();
			return true;
		}
		else if(!rightArm.isGrabbing() && isBatteryEnough( getBatteryRequirementForMovement())) {
			rightArm.pickUp();
			this.CarriedItem++;
			rightArm.isGrabbing = true;
			consumeBatteryForMovement();
			return true;
		}
		return false;
	}

	@Override
	public boolean dropDown() {
		
		if(leftArm.isGrabbing() && isBatteryEnough( getBatteryRequirementForMovement())) {
			leftArm.dropDown();
			this.CarriedItem--;
			leftArm.isGrabbing = false;
			return true;
		}
		else if(rightArm.isGrabbing() && isBatteryEnough( getBatteryRequirementForMovement())) {
			rightArm.dropDown();
			this.CarriedItem--;
			rightArm.isGrabbing = false;
			return true;
		}
		return false;
	}

	@Override
	public int getCarriedItemsCount() {
		return this.CarriedItem;
	}

	@Override
	public boolean moveUp() {
		return move(0, MOVEMENT_DELTA);
	}

	@Override
	public boolean moveDown() {
        return move(0, -MOVEMENT_DELTA);
	}

	@Override
	public boolean moveLeft() {
        return move(-MOVEMENT_DELTA,0);
	}

	@Override
	public boolean moveRight() {
        return move(MOVEMENT_DELTA, 0);
	}

	@Override
	public void recharge() {
	       this.batteryLevel = BATTERY_FULL;
		
	}

	@Override
	public double getBatteryLevel() {
		// TODO Auto-generated method stub
		return this.batteryLevel;
	}

	@Override
	public Position2D getPosition() {
	    return environment.getPosition();
	}

}
