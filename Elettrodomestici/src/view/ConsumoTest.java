package view;

import model.IUtilizzatore;
import model.Lampadina;
import model.Televisore;

public class ConsumoTest {
	public static void main(String[] args) {
		
		Televisore tel = new Televisore(60);
		tel.chiamaMario();
		Lampadina lampadina = new Lampadina(8);
		
		IUtilizzatore[] utilizzatori = new IUtilizzatore[2];
		utilizzatori[0] = tel;
		utilizzatori[1] = lampadina;
		
		float consumoTotale = 0;
		
		for (IUtilizzatore elemento : utilizzatori) {
			consumoTotale+=elemento.consumo(10);
		}
		
		System.out.println("il consumo totale è: "+consumoTotale);
		
	}
}
