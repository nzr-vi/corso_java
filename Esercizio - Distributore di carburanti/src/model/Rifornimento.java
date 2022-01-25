package model;

public class Rifornimento extends Operazione {

	private String autovettura;
	
	Rifornimento(Rifornimenti tipo, long amount, String autovettura) {
		super(tipo, amount);
		this.autovettura = autovettura;
	}
	
	@Override
	protected String getData() {
		return super.getData()+" of "+this.autovettura;
	}

	@Override
	long getAmount() {
		return -super.getAmount();
	}
	
}
