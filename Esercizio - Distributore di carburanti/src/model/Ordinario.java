package model;

public class Ordinario extends Distributore{
	public Ordinario(Long ciccin, Long capacitaBenzina) {
		super();
		this.addTank(Rifornimenti.Benzina).setTotalCapacity(capacitaBenzina);;
		this.addTank(Rifornimenti.Diesel).setTotalCapacity(ciccin);;
	}
}
