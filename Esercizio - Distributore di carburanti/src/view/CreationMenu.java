package view;

import java.util.Scanner;

import control.Controller;

public class CreationMenu extends Menu {

	public CreationMenu(Scanner input) {
		super(input, null);
		Menu.gestoreRifornimenti = Controller.getInstance();
		this.generateMenu();
	}

	private void generateMenu() {
		this.menu = "|| Distributori di Carburanti ||\n" 
				+ "distributori: " + Menu.gestoreRifornimenti.getDisNumber() + "\n"
				+ "1: select distributor\n" 
				+ "2: create distributor\n" 
				+ "3: go to operations\n" 
				+ "4: exit\n";
	}
	
	@Override
	protected void loopFunction() {
		switch (this.selection) {
		case 1:
			{
				Menu menu = new SelectionMenu(input); 
				menu.loopSelection();
			}
			break;
			
		case 2:
			{
				Menu menu = new InstantiationMenu(input);
				menu.loopSelection();
			}
			break;

		case 3:
			this.returnCode = 1;
			this.selection = -1;
			break;
			
		case 4:
			this.selection = -1;
			this.returnCode = -1;
			break;
			
		case -1:
		default:
			System.out.println("selection not available.");
			selection = 0;
			break;
		}

		this.generateMenu();
	}
}
