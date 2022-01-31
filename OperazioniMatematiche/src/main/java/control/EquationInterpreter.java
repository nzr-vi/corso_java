package control;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import exceptions.*;

public class EquationInterpreter {

	private String equation;
	private List<String> executionFlow;
	private OperationController controller;
	private Double equationResult;

	public EquationInterpreter() {
		this.executionFlow = new LinkedList<String>();
		this.controller = OperationController.getInstance();
	}

	public List<String> getExecutionFlow() {
		return new ArrayList<String>(this.executionFlow);
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}

	private List<Integer> findAllIndex(String message, String toFind) {
		List<Integer> matches = new LinkedList<>();
		int index = 0;
		while ((index = message.indexOf(toFind, index)) >= 0)
			matches.add(index++);
		return new ArrayList<>(matches);
	}

	private Double interpretSingleOperation(String singleOperation) throws OperationException, CalcolatriceException {
		StringReader buffer = new StringReader(singleOperation);
		Scanner inputOperation = new Scanner(buffer);
		try {
			this.controller.numberInterpreter(inputOperation.next(), true);
			this.controller.operationInterpreter(inputOperation.next());
			this.controller.numberInterpreter(inputOperation.next(), false);
			return this.controller.executeOperation();
		} catch (Exception e) {
			throw e;
		} finally {
			inputOperation.close();
			buffer.close();
		}
	}

	public void interpret() throws EquationException {
		int index = 0;
		this.executionFlow.clear();
		
		List<Integer> openings = this.findAllIndex(this.equation, "("),
				closings = this.findAllIndex(this.equation, ")");

		int levels = openings.size();

		if (levels != closings.size())
			throw new EquationException("Mismatch number of open and close parentesys");

		this.executionFlow.add(equation);

		String processingEquation = equation;

		try {
			while ((levels--) > 0) {
				final int first_closing = closings.get(0);
				Optional<Integer> o_last_opening = openings.stream().filter((i) -> i < first_closing)
						.collect(Collectors.maxBy(Integer::compare));

				if (o_last_opening.isEmpty())
					throw new EquationException("Parentesys order mismatch");

				final int last_opening = o_last_opening.get();

				String next_step = processingEquation.substring(0, last_opening);
				next_step += (this.interpretSingleOperation(
						processingEquation.substring(last_opening+1, first_closing-last_opening)));
				next_step += processingEquation.substring(first_closing+1);
				
				processingEquation = next_step;
				this.executionFlow.add(processingEquation);
			}
			
			this.equationResult = this.interpretSingleOperation(processingEquation);
			
		} catch (Exception e) {
			throw new EquationException(e.getMessage());
		}
	}

	
	public Double getEquationResult() {
		return equationResult;
	}

	
	
}
