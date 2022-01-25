package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.OpFactory;
import model.Operazione;

public class OperationController {
	
	private static OperationController instance;
	public static OperationController getInstance() {
		if(instance == null)
			instance = new OperationController();
		return instance;
	}
	
	private List<String> operationLog, output, error;
	private Map<String,Operazione> availableOperations;
	private Operazione currentOperation;
	private Integer inputA, inputB;
	
	private OperationController() {
		this.operationLog = new LinkedList<>();
		this.output = new LinkedList<>();
		this.error = new LinkedList<>();
		this.availableOperations = new HashMap<>();
	}

	private void getOrCreateOperation(String operation) {
		this.currentOperation = null;
		if(this.availableOperations.containsKey(operation))
			this.currentOperation = this.availableOperations.get(operation);
		else {
			try {
				this.currentOperation = OpFactory.createOperation(operation);
				this.availableOperations.put(operation, 
						this.currentOperation);
			}
			catch (Exception e) {
				this.error.add(e.getMessage());
			}
		}
		if(this.currentOperation==null)
			this.error.add("failed to fetch operation");
	}
	
	public void operationInterpreter(String operation){
		switch(operation.trim().toLowerCase()){
			case "somma":
			case "addizione":
			case "+":
				this.getOrCreateOperation("+");;
				break;
				
			case "sottrazione":
			case "differenza":
			case "-":
				this.getOrCreateOperation("-");;
				break;

			case "moltiplicazione":
			case "prodotto":
			case "x":
			case "*":
				this.getOrCreateOperation("*");;
				break;
			
			case "divisione":
			case "/":
				this.getOrCreateOperation("/");;
				break;
				
			default:
				this.error.add("no operation '"+operation+"' supported");
				break;
		}
	}

	public void numberInterpreter(String input, boolean first) {
		try {
			if(first)
				this.inputA = Integer.parseInt(input);
			else
				this.inputB = Integer.parseInt(input);
		}
		catch (Exception e) {
			this.error.add(e.getMessage());
		}
	}

	public void executeOperation() {
		try {
			if(this.currentOperation==null)
				throw new Exception("operation is null");
			if(this.inputA == null)
				throw new Exception("number#1 is not defined");
			if(this.inputB == null)
				throw new Exception("number#2 is not defined");
			this.currentOperation.calcola(this.inputA, this.inputB);
			String result = this.currentOperation.toString();
			this.operationLog.add(result);
			this.output.add(result);	
		}
		catch (Exception e){
			this.error.add(e.getMessage());
		}
	}

	
	private List<String> getLog(List<String> log) {
		List<String> toReturn = new ArrayList<>(log);
		log.clear();
		return toReturn;
	}
	
	public List<String> getHistory() {
		return new ArrayList<String>(this.operationLog);
	}

	public List<String> getOutput() {
		return this.getLog(this.output);
	}
	
	public List<String> getError() {
		return this.getLog(this.error);
	}	
}
