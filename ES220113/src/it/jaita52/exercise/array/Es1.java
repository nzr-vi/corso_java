package it.jaita52.exercise.array;

import java.util.Arrays;
import java.util.Scanner;

import it.jaita52.exercise.model.*;

public class Es1 extends Exercise{

	public Es1(Scanner user_input) {
		super(user_input);
		this.setDescription("StampaZigZ 10 numbers");
	}

	@Override
	public	void run() throws ExerciseException {
		
		//allocate an int vector of 10 elements
		int[] numbers = new int[10];
		
		int array_lenght = numbers.length;
		
		//initialize the vector with random numbers 0 to 99;
		for(int i = 0; i<array_lenght; i++)
			numbers[i] = (int) Math.floor(Math.random()*100);
		
		StringBuilder buffer = new StringBuilder(100);
		
		Extensions.printCollection(()->Arrays.stream(numbers).iterator());
		
		//zig zag print
		for(int i = 0; i<(array_lenght>>1); i++)
			buffer.append(numbers[i]+"\t"+numbers[array_lenght-(i+1)]+"\t");
		
		//in case the array has a odd length append the middle value 
		if(array_lenght%2!=0)
			buffer.append(numbers[(array_lenght>>1)+1]);
		
		//print the generated string
		System.out.println(buffer.toString());
	}
}
