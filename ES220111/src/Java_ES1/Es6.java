package Java_ES1;

import java.util.Scanner;

class Es6 extends Exercise {

/*
1      654321
12      54321
123      4321
1234      321
12345      21
123456      1
 */
	
	public Es6(Scanner user_input) {
		super(user_input);
		// TODO Auto-generated constructor stub
		this.description ="number spaced";
	}

	@Override
	void run() throws ExerciseException {
		// TODO Auto-generated method stub
		int max = 6;
		
		for(int i =0; i<max; i++) {
			for(int j = 0; j<=i; j++)
				System.out.print((j+1));
			System.out.print("      ");
			for(int j = i+1; j<=max; j++)
				System.out.print((j+1));		
			System.out.print('\n');		
		}
	}

}
