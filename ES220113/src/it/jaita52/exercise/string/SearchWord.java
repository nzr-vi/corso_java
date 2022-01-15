package it.jaita52.exercise.string;

import java.util.Scanner;

import it.jaita52.exercise.model.*;

//TASK3
public class SearchWord extends Exercise {

	/*
	 * Extra Lezione: Scrivi un programma in linguaggio Java che legga da tastiera
	 * una frase ed una parola. Queste devono essere passate ad un metodo che
	 * restituisce il numero di occorrenze della parola nella frase
	 */
	String text, word;

	public static void main(String[] args) {
		// check if the program have been started with "right" parameters
		SearchWord reverter = new SearchWord(new Scanner(System.in));

		try {
			if (args.length > 2) {
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < args.length - 1; i++)
					builder.append(args[i]);
				reverter.text = builder.toString();
				reverter.word = args[args.length - 1];
				reverter.print();
			} else {
				reverter.run();
			}
		} catch (ExerciseException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public SearchWord(Scanner user_input) {
		super(user_input);
		this.setDescription("Cerca Parola");
	}

	public int matches(String text, String toBeFound) throws ExerciseException {
		int index = 0, found = 0, step = toBeFound.length();

		if (step == 0)
			throw new ExerciseException("Word to be Found must be at least 1 char long");

		while ((index = (text.indexOf(toBeFound,index) + step)) >= step)
			found++;

		return found;

	}

	private int runMatches() throws ExerciseException {
		if(this.text == null || this.word == null)
			throw new ExerciseException("Text and Word must be initialized!!");
		return this.matches(this.text, this.word);
	}

	private void print() throws ExerciseException {
		System.out.println("The word: \"" + this.word + "\" is present: " + this.runMatches() + " times");
	}

	@Override
	public void run() throws ExerciseException {

		// ask to write something
		System.out.println("Type any text:\n");

		// memorize the input text
		this.text = this.getInput().nextLine();

		System.out.println("Type word to be found/count:\n");

		this.word = this.getInput().nextLine();

		this.print();
	}
}
