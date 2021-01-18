package it.unibo.oop.lab04.robot.composable;

public interface Componente {
	boolean isOn();
	boolean isConnected();
	double getConsumoEnergia();
	void doSomething();
}
