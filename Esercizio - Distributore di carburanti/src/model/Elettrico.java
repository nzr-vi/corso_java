package model;

public class Elettrico extends Gas{
	public Elettrico(Long diesel, Long benzina, Long gas) {
		super(diesel,benzina,gas);
		this.rifornimenti.put(Rifornimenti.Elettrico, new Elettricity());
	}
}
