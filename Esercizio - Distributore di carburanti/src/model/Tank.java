package model;

class Tank implements IRifornibile {
	private long currentAmount;
	private long totalCapacity = -1;
	private final String tipo;

	Tank(String tipo){
		this.tipo = tipo;
	}

	public long getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(long totalCapacity) {
		this.totalCapacity = totalCapacity;
		if(this.currentAmount > totalCapacity)
			this.currentAmount = totalCapacity;
	}

	@Override
	public void variazione(long amount) throws DistributoreException {
		if(this.currentAmount + amount<0)
			throw new DistributoreException(
					"Stai cercando di rifornire più di quanto disponibile");
		this.currentAmount += amount;
	}

	@Override
	public String tipoRifornimento() {
		return this.tipo;
	}
}
