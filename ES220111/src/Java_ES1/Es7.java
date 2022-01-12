package Java_ES1;

import java.util.Scanner;

public class Es7 extends Exercise {

	public Es7(Scanner user_input) {
		super(user_input);
		this.description = "fibonacci first 100 nr.";
		// TODO Auto-generated constructor stub
	}

	@Override
	void run() throws ExerciseException {
		// TODO Auto-generated method stub
		long[] latest = {0,1};
		for (long i : latest) {
			System.out.println(i);
		}
		for(int i = 0; i<98; i++) {
			long next = latest[0]+latest[1];
			System.out.println(next);
			latest[0] = latest[1];
			latest[1] = next;
		}
	}

}
