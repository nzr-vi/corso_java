package it.jaita52.exercise.string;

import java.util.Scanner;

import it.jaita52.exercise.model.*;

public class Splitter extends Exercise {

//	Scrivere un programma Suddividi che chiede all’utente di inserire una 
//	frase e la ristampa
//	una parola per volta. Provare a risolvere questo esercizio impostando un 
//	ciclo che fa tante iterazioni
//	quanti sono gli spazi.

	public static void main(String[] args) {
		Splitter reverter = new Splitter(new Scanner(System.in));
		try {
			reverter.run();
		} catch (ExerciseException ex) {
			System.out.println(ex.getMessage());

		}
	}

	public Splitter(Scanner user_input) {
		super(user_input);
		this.setDescription("Suddividi stringa");
	}

	@Override
	public void run() throws ExerciseException {

		// ask to write something
		System.out.println("Type any text:\n");

		// memorize the input text
		String text = this.getInput().nextLine();
		for (String word : text.split(" "))
			try {
				if (!word.isBlank()) {
					System.out.println(word);
					Thread.sleep(1000);
				}
			} catch (InterruptedException ex) {
				System.err.println(ex.toString());
			}
	}
}
