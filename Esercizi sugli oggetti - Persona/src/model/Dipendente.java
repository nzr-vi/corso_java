package model;

import java.text.ParseException;

public class Dipendente extends Persona {

	protected float yearlyIncome = 0;
	
	public Dipendente(String name, String surname, String birth) throws ParseException {
		super(name, surname, birth);
	}

	public float getYearlyIncome() {
		return yearlyIncome;
	}

	public void setYearlyIncome(float yearlyIncome) {
		this.yearlyIncome = yearlyIncome;
	}
	
	public void salatyIncrease(float percentage) {
		this.yearlyIncome*=(1+percentage/100);
	}
}
