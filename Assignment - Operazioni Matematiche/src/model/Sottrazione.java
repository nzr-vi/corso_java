package model;

public class Sottrazione extends Operation{

	Sottrazione(){
		this.operationSign = "-";
	}
	
	@Override
	protected int doOperation() {
		return this.a-this.b;
	}

}
