package model;

public class Divisione extends Operation{
	
	Divisione(){
		this.operationSign = "/";
	}

	@Override
	protected int doOperation() {
		return this.a/this.b;
	}
}
