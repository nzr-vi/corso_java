package it.jaita52.exercise.array;

import java.util.Arrays;
import java.util.Scanner;

import it.jaita52.exercise.util.*;

public class Es3 extends Exercise{

//	scrivere un programma SecondoArray che chiede all’utente di inserire 10 
//	numeri interi e li memorizza in un array 
//	Successivamente, crea un nuovo array di dimensione pari al numero di 
//	valori maggiori o uguali a zero inseriti dall’utente.
//	Copia tutti i valori maggiori o uguali a zero nel nuovo array e ne stampa 
//	i valori in ordine inverso
	
	static final int ARRAY_LENGTH = 10; 
	int[] secondoArray;
	
	public Es3(Scanner user_input) {
		super(user_input);
		this.setDescription("load SecondoArray");
		this.secondoArray = new int[ARRAY_LENGTH];
	}

	private void acquireArray() {
		System.out.println("\nPlease insert 10 numbers:");
		
		Scanner syin = this.getInput();
		
		for(int i = 0; i<Es3.ARRAY_LENGTH; i++) {
			System.out.print("\n"+(i+1)+":");
			this.secondoArray[i] = Integer.parseInt(syin.nextLine());
		}
		
		StringBuilder message = new StringBuilder(100);
		Arrays.stream(this.secondoArray).forEach(value->message.append(value+"\t"));
		
		System.out.println(message.toString());		
	}
	
	private int[] extrapolatePositives() {
		return Arrays.stream(this.secondoArray).
				filter(value->value>=0).toArray();
	}
	
	private void printArrayInv(int[] array) {
		int lenght = array.length;
		StringBuilder toPrint = new StringBuilder(lenght*3);
		
		for(int i = lenght-1; i>-1; i--)
			toPrint.append(array[i]+"\t");
		
		System.out.println(toPrint.toString());
	}
	
	@Override
	public	void run() throws ExerciseException {
		this.acquireArray();
		int[] positives = this.extrapolatePositives();
		this.printArrayInv(positives);
	}
}
