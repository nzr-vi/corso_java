package model;

public class Gas extends Ordinario {

	public Gas(Long diesel, Long benzina, Long gas) {
		super(diesel,benzina);
		this.addTank(Rifornimenti.Gas).setTotalCapacity(gas);;
	}
}
