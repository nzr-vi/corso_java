package model;

import java.util.Scanner;
import java.util.stream.Stream;

public class DoubleSpaceRemover extends Exercise {

//	TASK 2
//	Acquisire una stringa di caratteri, contenente pure degli spazi.
//	Scrivere un procedimento tale che, ogni volta che viene trovato uno spazio,
//	vengano soppressi eventuali altri spazi contigui (due o piu'). 

	public static void main(String[] args) {
		DoubleSpaceRemover reverter = new DoubleSpaceRemover(new Scanner(System.in));
		try {
			reverter.run();
		} catch (ExerciseException ex) {
			System.out.println(ex.getMessage());

		}
	}

	public DoubleSpaceRemover(Scanner user_input) {
		super(user_input);
		this.setDescription("TASK 2: Double spaces remover");
	}

	@Override
	public void run() throws ExerciseException {

		// ask to write something
		System.out.println("Type any text:\n");

		// memorize the input text
		String text = this.getInput().nextLine();
		
		String[] splitted = text.split(" ");
		StringBuilder sb = new StringBuilder(text.length());
		Stream.of(splitted).filter(s->!s.isBlank())
			.forEach(s->sb.append(s+" "));
		System.out.println(sb.toString().trim());
	}
}
