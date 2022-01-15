package it.jaita52.exercise.string;

import java.util.Scanner;

import it.jaita52.exercise.model.*;

public class OnlyVowels extends Exercise{

//	Scrivere un programma SoloVocali che chiede all’utente di inserire una 
//	stringa e ne stampa le sole vocali.
//	Per esempio, se si immette la stringa "Viva Java", il programma stampa 
//	"iaaa".
	
	public static void main(String[] args) {
		OnlyVowels reverter = new OnlyVowels(new Scanner(System.in));
		try {
			reverter.run();
		}
		catch(ExerciseException ex) {
			System.out.println(ex.getMessage());			
		}		
	}

	public OnlyVowels(Scanner user_input) {
		super(user_input);
		this.setDescription("Solo Vocali");
	}

	@Override
	public	void run() throws ExerciseException {
		
		//ask to write something
		System.out.println("Type any text:\n");
		
		//memorize the input text
		String text = this.getInput().nextLine();
		StringBuilder onlyVowels = new StringBuilder(text.length()>>1);
		for(char c : text.toCharArray())
			switch(c)
			{
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
					onlyVowels.append(c);
					break;
				default:
					continue;
			}
		
		System.out.println(onlyVowels.toString());
	}
}
