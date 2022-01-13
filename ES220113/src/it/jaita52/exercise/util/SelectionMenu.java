package it.jaita52.exercise.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import it.jaita52.exercise.array.*;
import it.jaita52.exercise.string.*;

public class SelectionMenu {
	
	private Scanner inputScan;
	private int selections;
	private List<Integer> history;
	private int selection;
	private List<Exercise> exercizes;
	
	static void clearScreen() {  
	    System.out.print("\n\n");
	}  
	
	public SelectionMenu() {
		this.selection = 0;
		this.selections = 0;
		this.history = new LinkedList<>();
		this.exercizes = new LinkedList<>();
		this.inputScan = new Scanner(System.in);
		this.exercizes.add(new Es1(this.inputScan));
		this.exercizes.add(new Es2(this.inputScan));
		this.exercizes.add(new Es3(this.inputScan));
		this.exercizes.add(new Es4(this.inputScan));
		this.exercizes.add(new Reverse(this.inputScan));
		this.exercizes.add(new OnlyVowels(this.inputScan));
		this.exercizes.add(new Splitter(this.inputScan));
		this.exercizes.add(new ReplaceNumbers(this.inputScan));
		this.exercizes = new ArrayList<>(this.exercizes);
	}
	
	public void printMenu() {
		boolean haveToTerminate = false;
		
		do {
			System.out.println("Exercizes 1 Menu'");
			int id = 1;
			for (Exercise exercise : exercizes) 
				System.out.println("\t" + id++ + " - "+ exercise.getDescription());
			System.out.print("Please make a choice ("+id+" to exit): '");
			
			try {	
				this.selection = Integer.parseInt(this.inputScan.nextLine());
				if(selection != id) {
					clearScreen();
					this.exercizes.get(this.selection-1).run();
					this.selections++;
					this.history.add(this.selection);
				}
				else {
					System.out.println("Bye bye~");
					haveToTerminate = true;
				}
				Thread.sleep(1000);
			}
			catch(Exception ex) {
				System.out.println("ERROR: "+ex.getMessage());
			}
			clearScreen();		
		}while(!haveToTerminate);
	}
	
	public int getSelections() {
		return selections;
	}

	public List<Integer> getHistory() {
		if(history!=null)
			return history;
		return new LinkedList<>();
	}
}
