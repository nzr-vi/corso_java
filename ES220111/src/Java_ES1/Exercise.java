package Java_ES1;

import java.util.Scanner;

abstract class Exercise {
	
	String description = "no available description";
	Scanner input;
	
	Exercise(Scanner user_input){
		this.input = user_input;
	}
	
	String getDescription() {
		return this.description;
	}
	
	abstract void run() throws ExerciseException;
}
