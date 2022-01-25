package model;

public class Somma extends Operation{
	
	Somma(){
		this.operationSign = "+";
	}

	@Override
	protected int doOperation() {
		return this.a+this.b;
	}
}
