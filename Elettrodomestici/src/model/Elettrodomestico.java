package model;

public abstract class Elettrodomestico implements IUtilizzatore {
	
	boolean acceso;
	float wattaggio_elettrodo;
	
	public Elettrodomestico(float wattaggio) {
		wattaggio_elettrodo = wattaggio;
	}
	
	abstract void illuminati();
	
	public void chiamaMario() {
		System.out.println("Ciao Mario!!");
	}
}
