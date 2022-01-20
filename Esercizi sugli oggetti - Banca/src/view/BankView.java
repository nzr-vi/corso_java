package view;

import java.util.Scanner;

import control.Banca;

public class BankView {
	private Banca bank;
	private Scanner input;
	
	public BankView() {
		this.input = new Scanner(System.in); 
	}
	
	private void createBank() {
		bank = new Banca(this.input.nextLine());
	}
	
	public void menu() {
		if(bank == null)
			this.createBank();
	}
}
