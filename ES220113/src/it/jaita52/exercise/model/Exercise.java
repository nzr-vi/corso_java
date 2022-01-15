package it.jaita52.exercise.model;

import java.util.Scanner;

public abstract class Exercise {
	
	String description = "no available description";
	Scanner input;
	
	public Exercise(Scanner user_input){
		this.input = user_input;
	}
	
	public Scanner getInput() {
		return this.input;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public abstract void run() throws ExerciseException;
}
