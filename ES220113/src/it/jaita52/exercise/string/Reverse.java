package it.jaita52.exercise.string;

import java.util.Scanner;
import it.jaita52.exercise.util.*;

public class Reverse extends Exercise{

//	Scrivere un programma Contrario che chiede all’utente di inserire una 
//	stringa e la stampa al contrario.
//	Per esempio, se si immette la stringa "Viva Java", il programma stampa 
//	"avaJ aviV"
	
	public static void main(String[] args) {
		Reverse reverter = new Reverse(new Scanner(System.in));
		try {
			reverter.run();
		}
		catch(ExerciseException ex) {
			System.out.println(ex.getMessage());			
		}		
	}

	public Reverse(Scanner user_input) {
		super(user_input);
		this.setDescription("Contrario");
	}

	@Override
	public	void run() throws ExerciseException {
		
		//ask to write something
		System.out.println("Type a text:\n");
		
		StringBuilder reverted = new StringBuilder();
		reverted.append(this.getInput().nextLine());
		reverted.reverse();
		System.out.println(reverted.toString());
	}
}
