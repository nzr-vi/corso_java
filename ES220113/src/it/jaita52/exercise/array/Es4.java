package it.jaita52.exercise.array;

import java.util.Arrays;
import java.util.Scanner;
import it.jaita52.exercise.util.*;

public class Es4 extends Exercise {

	// ##Esercizio - 4
	// Scrivere un programma TreConsecutivi che prevede un array di 10 numeri
	// interi contenente valori a piacere, devi verificare se ci sono valori
	// consecutivi uguali.
	// Se l’array contiene tre valori uguali in tre posizioni consecutive,
	// altrimenti stampa "NO".

	public Es4(Scanner user_input) {
		super(user_input);
		this.setDescription("TreConsecutivi");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() throws ExerciseException {

		// allocate an int vector of 10 elements
		int[] numbers = new int[10];

		int array_lenght = numbers.length;

		// initialize the vector with random numbers 0 to 1;
		for (int i = 0; i < array_lenght; i++)
			numbers[i] = (int) Math.floor(Math.random() * 2);

		Extensions.printCollection(()->Arrays.stream(numbers).iterator());
		
		int consecutives = 0;
		int element = 0;
		
		for (int i = 0; i < array_lenght; i++) {
			if (element != numbers[i]) {
				consecutives = 1;
				element = numbers[i];
				continue;
			}
			consecutives++;

			if (consecutives > 2) {
				System.out.println("Si");
				return;
			}
		}
		System.out.println("No");
	}
}
