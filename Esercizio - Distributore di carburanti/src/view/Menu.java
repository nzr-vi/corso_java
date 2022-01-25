package view;

import java.util.Scanner;

import control.Controller;

public abstract class Menu {

	public static Controller gestoreRifornimenti;
	
	protected Scanner input;
	
	protected String menu;
	protected String currentInputLine;
	protected int selection;
	protected String currentError, currentOutput;
	protected int returnCode;
	
	public static int run(Menu menu, Menu next) {
		Menu current;
		do {
			current = menu;
			menu = next;
			next = current;
			current.loopSelection();
		}while(current.returnCode>0);

		final StringBuilder logMessages = new StringBuilder();
		gestoreRifornimenti.getLogs().forEach(logList->{
			logList.forEach(log->logMessages.append(log+"\n"));
			logMessages.append("    -----\n\n");}
		);
		System.out.println(logMessages);
		System.out.println("\npress enter to exit");
		current.inputLine();
		return current.returnCode;
	}
	
	public int getReturnCode() {
		return returnCode;
	}

	public Menu(Scanner input, String menu) {
		this.input	= input;
		this.menu = menu;
		this.selection = -1;
	}
	
	public void showMenu() {
		System.out.println(this.menu);
	};
	
	protected String inputLine() {
		this.currentInputLine = input.nextLine();
		return this.currentInputLine;
	}
	
	protected int intSelection() {
		String line = this.input.nextLine();
		try {
			return Integer.parseInt(line);
		} catch (Exception ex) {
			System.err.println("Failed to parse: " + line);
			return 0;
		}
	}
	
	protected abstract void loopFunction();
	
	protected void loopSelection() {
		do {
			System.out.println(this.menu);
			System.out.print("selection: ");
			this.selection = intSelection();
			System.out.println();
			this.loopFunction();
			System.out.println("\n");
		} while (this.selection != -1);
	}
	
	protected void enterToContinue() {
		System.out.println("press enter to continue");
		this.inputLine();
	}

	protected String getError() {
		this.currentError = Menu.gestoreRifornimenti.getError();
		return this.currentError;
	}
	
	protected String getOutput() {
		this.currentOutput = Menu.gestoreRifornimenti.getOutput();
		return currentOutput;
	}

	protected boolean isErrorOrPrintOutput() {
		if(!this.getError().isBlank()) {
			System.out.println(this.getOutput());
			System.out.println(this.currentError);
			return true;
		}
		if(!this.getOutput().isBlank())
			System.out.println(this.currentOutput);
		return false;
	}
}
