package view;

import java.util.Scanner;

import control.SelectorHelper;

public class SelectionMenu extends Menu {

	SelectorHelper helper;

	public SelectionMenu(Scanner input) {
		super(input, "Select by:\n" 
				+ "-1: back to main\n"
				+ "1: index\n" 
				+ "2: name\n" 
				+ "3: address\n"
				+ "4: showList\n");
		this.helper = new SelectorHelper(Menu.gestoreRifornimenti);
	}

	@Override
	protected void loopSelection() {
		if (this.helper.canChoose())
			super.loopSelection();
		else
			System.out.println("No distributor available");
	}

	private boolean stringSelectorSkip() {
		if(this.getError()!=null && !this.currentError.isBlank()) {
			if(this.currentError.contains("1+")) {
				System.out.print(this.getOutput());
				this.helper.pickFromLS(this.intSelection());
			}
			else {
				System.out.println(this.currentError);
				this.enterToContinue();
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected void loopFunction() {
		switch (this.selection) {
			case -1:
				return;
				
			case 1:
				{
					System.out.print("type index: ");
					this.helper.findByIndex(this.intSelection());
					break;
				}
				
			case 2:
				{
					System.out.print("type name: ");
					this.helper.findByName(this.inputLine());
					if(this.stringSelectorSkip())return;
					break;
				}
				
			case 3:
				{
					System.out.print("type address: ");
					this.helper.findByAddress(this.inputLine());
					if(this.stringSelectorSkip())return;
					break;
				}
				
			case 4:
				this.helper.getFullList();
				break;
		
			default:
				break;
		}
		this.isErrorOrPrintOutput();
		this.enterToContinue();
	}

}
