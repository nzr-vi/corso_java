package boh;

import java.util.ArrayList;
import java.util.List;

public class Paperino{

	void faiQualcosa() {
		System.out.println("Sto lavorando");
	}
	
	public static void main(String[] args) {

		List<Paperino> pippo = new ArrayList<Paperino>();
		pippo.add(new Paperino());
		pippo.get(0).faiQualcosa();
	}

}
