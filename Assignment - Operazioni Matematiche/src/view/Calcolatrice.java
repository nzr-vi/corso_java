package view;

import java.util.List;
import java.util.Scanner;

import control.OperationController;

public class Calcolatrice {
	
	private Scanner scanner;
	private OperationController controller;
	private boolean hasToEnd;
	private String cError, cOutput;

	private String buildSingleString(List<String> log) {
		StringBuilder sb = new StringBuilder();
		log.forEach(s->sb.append(s+"\n"));
		return sb.toString();
	}
	
	private void downloadError() {
		this.cError = this.buildSingleString(this.controller.getError());
	}
	
	private void downloadOutput() {
		this.cOutput = this.buildSingleString(this.controller.getOutput());
	}
	
	private void downloadStream() {
		this.downloadError();
		this.downloadOutput();
	}
	
	private void printStream() {
		if(!this.cOutput.isBlank())
			System.out.println(this.cOutput);
		if(!this.cError.isBlank())
			System.err.println(this.cError);			
	}
	
	public Calcolatrice() {
		this.scanner = new Scanner(System.in);
		this.controller = OperationController.getInstance();
		this.hasToEnd = false;
		this.cError = this.cOutput = "";
	}
	
	private void showInfo() {
		System.out.println("Semplice calcolatrice che consente di\n"
				+ "effettuare le 4 operazioni principali:\n"
				+ " - somma\n"
				+ " - sottrazione\n"
				+ " - moltiplicazione\n"
				+ " - divisione\n");
	}
	
	private void operationCreation() {
		System.out.print("Operazione: ");
		this.controller.operationInterpreter(this.scanner.nextLine());
		this.downloadStream();
		if(!this.cError.isBlank())
			return;
		System.out.print(this.cOutput);
		System.out.print("A: ");
		this.controller.numberInterpreter(this.scanner.nextLine(),true);
		this.downloadStream();
		if(!this.cError.isBlank())
			return;
		System.out.print(this.cOutput);
		System.out.print("B: ");
		this.controller.numberInterpreter(this.scanner.nextLine(),false);
		this.downloadStream();
		if(!this.cError.isBlank())
			return;
		System.out.print(this.cOutput);
		this.controller.executeOperation();
		this.downloadStream();
	}
	
	private void showMenu() {
		System.out.print("||CALCOLATRICE||\n"
				+ "[1]: Nuova Operazione\n"
				+ "[2]: Info Operazioni\n"
				+ "[3]: Log Operazioni\n"
				+ "[0]: EXIT\n\n"
				+ "");
	}
	
	private void printHistory() {
		List<String> history = this.controller.getHistory();
		final StringBuilder sb = new StringBuilder();
		sb.append("|| CRONOLOGIA OPERAZIONI ||\n");
		for(int i = 0; i<history.size(); i++)
			sb.append("#"+(i+1)+"\t"+history.get(i)+"\n");
		System.out.println(sb);
		this.downloadStream();
	}
	
	private void loop() {
		this.showMenu();
		try {
			int selection = Integer.parseInt(this.scanner.nextLine());
			switch (selection) {
				case 0:
					this.hasToEnd = true;
					return;
					
				case 1:
					this.operationCreation();
					break;
					
				case 2:
					this.showInfo();
					return;
					
				case 3:
					this.printHistory();
					break;
	
				default:
					System.err.println("Invalid selection");
					break;
			}

			this.printStream();
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private void pressEnterTo(String operation) {
		System.out.println("press enter to "+operation);
		this.scanner.nextLine();
	}
	
	private void pressEnterToContinue() {
		this.pressEnterTo("continue");
	}
	
	void Start() {
		do {
			this.loop();
	
			this.pressEnterToContinue();
		}while(!this.hasToEnd);
		
		this.printHistory();
		this.pressEnterToContinue();
	}
}
