package model;


public class Televisore extends Elettrodomestico {

	public Televisore(float wattaggio) {
		super(wattaggio);
	}

	@Override
	void illuminati() {
		// TODO Auto-generated method stub
		System.out.println("Si illumina");
	}

	@Override
	public float consumo(float time) {
		return this.wattaggio_elettrodo*time;
	}
}
