package test;


import control.EquationInterpreter;
import exceptions.EquationException;

public class InterpreterTest {
	
	EquationInterpreter toTest = new EquationInterpreter();
	
	private void tryEq(String eq) {		
		toTest.setEquation(eq);
		try {
			toTest.interpret();
			System.out.println(toTest.getEquationResult());
		} catch (EquationException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	public static void main(String... args) {
		String[] classTests = {
				"( 15 / 5 ) + 10",
				"( 15 / 5 ) + ( 10 + 2 )",
		};
		
		if(args.length>0)
			classTests = args;
		
		InterpreterTest it = new InterpreterTest();
		for (String equation : classTests) {
			it.tryEq(equation);
		}
	}
}
