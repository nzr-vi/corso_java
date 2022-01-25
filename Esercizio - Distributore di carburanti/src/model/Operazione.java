package model;

class Operazione {
	
	protected static String units(Rifornimenti tipo) {
		switch(tipo) {
		 case Benzina:
		 case Diesel:
			 return "lt";
		 case Gas:
			 return "m3";
		 case Elettrico:
			 return "kwh";
		}
		return "";
	}
	
	protected final Rifornimenti tipo;
	protected final long amount;
	protected String data;
	protected String error = null;
	
	Operazione(Rifornimenti tipo, long amount) {
		this.tipo = tipo;
		this.amount = amount;
	}

	void setError(String error) {
		this.error = error;
	}
	
	String getError() {
		return error;
	}
	
	Rifornimenti getTipo() {
		return tipo;
	}

	long getAmount() {
		return amount;
	}

	protected String getData() {
		return Distributore.printAmount(amount)+" "+ Operazione.units(this.tipo);
	}
	
	@Override
	public String toString() {
		String message = tipo + " " + this.getClass().getSimpleName().toLowerCase() + " " +this.getData();
		if(error != null && !error.isBlank()) {
			return "[x] "+message;
		}
		return message;
	}
}
