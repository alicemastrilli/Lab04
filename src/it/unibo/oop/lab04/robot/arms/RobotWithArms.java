package it.unibo.oop.lab04.robot.arms;

import it.unibo.oop.lab04.robot.base.*;

public interface RobotWithArms extends Robot {
	boolean pickUp();
	boolean dropDown();
	int getCarriedItemsCount(); 

}
