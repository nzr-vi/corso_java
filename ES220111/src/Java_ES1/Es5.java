package Java_ES1;

import java.util.Scanner;

class Es5 extends Exercise {

/*
#
##
###
####
#####
######
 */
	
	Es5(Scanner user_input) {
		super(user_input);
		this.description = "print fig.2";
		// TODO Auto-generated constructor stub
	}

	@Override
	void run() throws ExerciseException {
		// TODO Auto-generated method stub
		for(int i = 1; i<=6; i++)
		{
			for(int j =0; j<i; j++)
				System.out.print('#');
			System.out.print('\n');
		}
	}

}
