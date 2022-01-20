package view;

import java.text.ParseException;

import model.Dipendente;

public class DipendenteTest {
	public static void main(String[] args) {
		try {
			Dipendente d1 = new Dipendente("Mario", "Monti", "01/01/1980");
			d1.setYearlyIncome(10000.00f);
			d1.salatyIncrease(30);
			
			System.out.println(d1.dettaglio()+"\nyearly income: " + d1.getYearlyIncome());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
