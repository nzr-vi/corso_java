package it.jaita52.exercise.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import it.jaita52.exercise.util.*;

public class ReplaceNumbers extends Exercise {

//	Scrivere un programma SostituisciNumeri che chiede all’utente di inserire 
//	una stringa e la ristampa sostituendo le cifre contenute nella stringa con 
//	la loro rappresentazione testuale.
//	Ad esempio, se l’utente inserisce "Ho comprato 2 biscotti e 3 caramelle" 
//	il programma deve stampare "Ho comprato due biscotti e tre caramelle". 
//	Oppure se inserisce "44 gatti" il programma deve stampare "quattroquattro 
//	gatti".

	Map<String, String> decoder;
	String decoded = "";

	public static void main(String[] args) {
		ReplaceNumbers reverter = new ReplaceNumbers(new Scanner(System.in));
		try {
			reverter.run();
		} catch (ExerciseException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public ReplaceNumbers(Scanner user_input) {
		super(user_input);
		this.setDescription("SostituisciNumeri con testuali");
		this.decoder = new HashMap<String, String>();
		decoder.put("0", "zero");
		decoder.put("1", "uno");
		decoder.put("2", "due");
		decoder.put("3", "tre");
		decoder.put("4", "quattro");
		decoder.put("5", "cinque");
		decoder.put("6", "sei");
		decoder.put("7", "sette");
		decoder.put("8", "otto");
		decoder.put("9", "nove");
	}

	@Override
	public void run() throws ExerciseException {

		// ask to write something
		System.out.println("Type any text:\n");

		// memorize the input text
		this.decoded = this.getInput().nextLine();
		
		//apply the decoder to the string
		this.decoder.forEach((key, value) -> {
			this.decoded = this.decoded.replaceAll(key, value);
		});
		
		System.out.println(this.decoded);
	}
}
