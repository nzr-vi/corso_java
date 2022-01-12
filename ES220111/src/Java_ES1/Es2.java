package Java_ES1;

import java.util.Scanner;

class Es2 extends Exercise {
	
	Es2(Scanner user_input) {
		super(user_input);
		this.description = "print even number 20 to 0";
		// TODO Auto-generated constructor stub
	}

	@Override
	void run() throws ExerciseException {
		// TODO Auto-generated method stub
		for(int i = 20; i>=0; i-=2)
			System.out.println(i);
	}

}
