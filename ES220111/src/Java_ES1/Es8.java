package Java_ES1;

import java.util.Scanner;

public class Es8 extends Exercise {

	public Es8(Scanner user_input) {
		super(user_input);
		this.description = "all positives and even";
		// TODO Auto-generated constructor stub
	}

	@Override
	void run() throws ExerciseException {
		// TODO Auto-generated method stub
		System.out.print("Define #N numbers:");
		int n_numbers = Integer.parseInt(this.input.nextLine());
		System.out.print('\n');
		if(n_numbers>0) {
			for(int i = 0; i<n_numbers; i++)
			{
				System.out.print((i+1)+"\t:");
				int number = Integer.parseInt(this.input.nextLine());
			    if(number%2==1 || number <=0 ) {
			    	System.out.println("NO");
			    	return;
			    }
			}
			System.out.println("tutti positivi e pari");
		}
		else
			throw new ExerciseException("Undefined number of numbers!");
	}

}
