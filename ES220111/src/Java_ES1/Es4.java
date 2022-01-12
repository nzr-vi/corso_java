package Java_ES1;

import java.util.Scanner;

class Es4 extends Exercise {

	int[] pattern = {2,3,2,1,2,1};
	
	/* 
Stampare a video la seguente figura:
java
**
***
**
*
**
* 
	 * */
	
	Es4(Scanner user_input) {
		super(user_input);
		this.description = "print fig.1";
		// TODO Auto-generated constructor stub
	}

	@Override
	void run() throws ExerciseException {
		// TODO Auto-generated method stub
		for (int i : pattern) {
			for(int j = 0; j<i; j++)
				System.out.print('*');
			System.out.print('\n');
		}
	}

}
