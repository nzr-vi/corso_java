package Java_ES1;

import java.util.Scanner;

class Es3 extends Exercise {

	Es3(Scanner user_input){
		super(user_input);
		this.description = "print times table of N";
	}

	@Override
	void run() throws ExerciseException {
		System.out.print("Number: ");
		int number = Integer.parseInt(this.input.nextLine());
		int i = 0;
		while(i<10)
			System.out.println(number*(++i));			
	}
	
}
