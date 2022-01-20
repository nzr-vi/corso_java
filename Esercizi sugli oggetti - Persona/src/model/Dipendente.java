package model;

import java.text.ParseException;

public class Dipendente extends Persona {

	protected long yearlyIncome = 0;
	
	public Dipendente(String name, String surname, String birth) throws ParseException {
		super(name, surname, birth);
	}

	public long getYearlyIncome() {
		return yearlyIncome;
	}

	public void setYearlyIncome(long yearlyIncome) {
		this.yearlyIncome = yearlyIncome;
	}
	
	public void salatyIncrease(float percentage) {
		this.yearlyIncome*=(1+percentage/100);
	}
}
