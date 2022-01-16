package model;

import java.util.Scanner;

public abstract class Exercise {
	
	private String description = "no available description";
	private Scanner input;
	
	public Exercise(Scanner user_input){
		this.input = user_input;
	}
	
	Scanner getInput() {
		return this.input;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	void setDescription(String description) {
		this.description = description;
	}
	
	public abstract void run() throws ExerciseException;
}
