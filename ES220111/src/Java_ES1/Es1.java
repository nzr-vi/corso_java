package Java_ES1;

import java.util.Scanner;

class Es1 extends Exercise {

	Es1(Scanner user_input) {
		super(user_input);
		this.description = "print first 10 int numbers";
		// TODO Auto-generated constructor stub
	}

	@Override
	void run() throws ExerciseException {
		// TODO Auto-generated method stub
		for(int i = 0; i<10; i++)
			System.out.println(i);
	}
}
