package it.jaita52.exercise.array;

import java.util.Scanner;

import it.jaita52.exercise.model.*;

public class Es2 extends Exercise{

//	Scrivere un programma SommaPariDispari che prevede un array di 10 numeri 
//	interi contenente valori a piacere e stampa Pari e dispari uguali se la 
//	somma dei numeri in posizioni pari dell’array è uguale alla somma dei 
//	numeri in posizioni dispari, altrimenti il programma stampa Pari e dispari 
//	diversi.
	
	public Es2(Scanner user_input) {
		super(user_input);
		this.setDescription("SommaPariDispa 10 numbers");
		// TODO Auto-generated constructor stub
	}

	@Override
	public	void run() throws ExerciseException {
		
		//allocate an int vector of 10 elements
		int[] numbers = new int[10];
		
		int array_lenght = numbers.length;
		
		//initialize the vector with random numbers 0 to 99;
		for(int i = 0; i<array_lenght; i++)
			numbers[i] = (int) Math.floor(Math.random()*100);

		long somma_pari = 0, somma_dispari = 0;
		
		StringBuilder array_printer = new StringBuilder();
		
		for(int i = 0; i<array_lenght; i++) {
			if(i%2==0)
				somma_pari+=numbers[i];
			else
				somma_dispari+=numbers[i];
			array_printer.append("(id:"+i+" "+numbers[i]+")\n");
		}
		
		array_printer.append("\neven:"+somma_pari+"\todd:"+somma_dispari+"\n");
		
		array_printer.append("Pari e dispari ");

		if(somma_pari == somma_dispari)
			array_printer.append("uguali");
		else
			array_printer.append("diversi");
		
		System.out.println(array_printer.toString());
	}
}
