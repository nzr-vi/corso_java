package model;

public class Lampadina implements IUtilizzatore{
	float wattaggio;
	
	public Lampadina(float wattaggio) {
		this.wattaggio = wattaggio;
	}

	@Override
	public float consumo(float time) {
		return wattaggio * time;
	}
	
	public void faiQualcosa() {
		
	}
}
