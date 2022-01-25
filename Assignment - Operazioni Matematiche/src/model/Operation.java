package model;

abstract class Operation implements Operazione{
	protected Integer a,b,result;
	protected String operationSign;
	
	Operation() {
		this.a = null;
		this.b = null;
		this.result = null;
	}

	protected boolean isAOK() {
		return this.a!=null;
	}

	protected boolean isBOK() {
		return this.b!=null;
	}
	
	protected boolean areOperandsOK() {
		return this.isAOK() && this.isBOK();
	}

	@Override
	public int calcola(Integer a, Integer b) throws CalcolatriceException {
		this.a = a;
		this.b = b;
		this.result = null;
		if(!areOperandsOK())
			throw new CalcolatriceException("issues with operands");
		this.result = this.doOperation();
		return this.result;
	}
	
	protected abstract int doOperation();
	
	@Override
	public String toString() {	
		return this.a+" "+this.operationSign+" "+this.b+" = "+this.result;
	}
}
