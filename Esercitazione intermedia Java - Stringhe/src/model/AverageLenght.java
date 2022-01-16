package model;

import java.util.Scanner;

public class AverageLenght extends Exercise{

//	TASK 1
//	Scrivere una classe Java che chiede (tramite lo standard input
//	- Scanner)  ripetutamente all'utente di inserire stringhe e che
//	termina quando l'utente inserisce la stringa vuota. Al termine 
//	dell'inserimento delle stringhe, la classe deve stampare sullo
//	standard output la lunghezza media delle stringhe inserite.

	public static void main(String[] args) {
		AverageLenght reverter = new AverageLenght(new Scanner(System.in));
		try {
			reverter.run();
		}
		catch(ExerciseException ex) {
			System.out.println(ex.getMessage());			
		}		
	}

	public AverageLenght(Scanner user_input) {
		super(user_input);
		this.setDescription("TASK 1: Avg Lenght");
	}

	@Override
	public	void run() throws ExerciseException {
		
		long charNumbers = 0;
		int linesWritten = 0;
		String current = "";
		
		//ask to write something
		System.out.println("Type any text:");
		do {
			System.out.print(++linesWritten+":");
			current = this.getInput().nextLine();
			charNumbers+=current.length();
		}while(!current.equals(""));
				
		StringBuilder output = new StringBuilder(20);
		output.append("\nAvg string: ");
		
		//the check is necessary to avoid division by 0 which
		//would throw an exception
		if(linesWritten>1) 
			output.append((float)charNumbers/(linesWritten-1));
		else
			output.append("0");
		System.out.println(output.toString());			
	}
}
