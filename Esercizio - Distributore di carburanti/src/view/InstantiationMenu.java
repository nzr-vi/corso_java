package view;

import java.util.List;
import java.util.Scanner;

import control.IstantiationHelper;

public class InstantiationMenu extends Menu {

	private enum State {
		tipeSelection, paramInitialization, overview,
	}

	IstantiationHelper helper;
	private State state;
	private String[] tipes;
	private List<String> paramInfo;

	public InstantiationMenu(Scanner input) {
		super(input, "Create new Distributore\n" + "-1: back\n");
		helper = new IstantiationHelper(Menu.gestoreRifornimenti);
		this.tipes = helper.getTipes();
		for (int i = 0; i < this.tipes.length; i++)
			this.menu += (i + 1) + ": " + this.tipes[i] + "\n";
		this.state = State.tipeSelection;
	}

	private void generateOverviewMenu() {
		this.menu = this.helper + "\n" + 
				"-1: back to main\n" + 
				"0: instantiate\n"+
				"1: change tipo [" + this.helper.getTipo() + "]\n" +
				"2: change name [" + this.helper.getNome() + "]\n" +
				"3: change address [" + this.helper.getIndirizzo() + "]\n";
		String[] params = this.helper.getParametri();
		int i = 0;
		for (; i < this.paramInfo.size(); i++) {
			this.menu += (i + 4) + ": change " +
					this.paramInfo.get(i) + " [" + params[i] + "]\n";
		}

	}
	
	private void impossibleState() {
		System.err.println("Impossible state, instantiation resetted!");
		helper = new IstantiationHelper(Menu.gestoreRifornimenti);
		this.state = State.tipeSelection;
		this.selection = 0;
		this.paramInfo = null;
	}
	
	private void tipeSelectionUI() {
		if (this.selection != -1) {
			if (this.selection-- > this.tipes.length || this.selection < 0)
				System.out.println("Invalid Selection");
			else {
				this.paramInfo = this.helper.setTipo(selection);
				if (!this.getError().isBlank()) {
					System.out.println(this.currentError);
					this.selection = 0;
					this.enterToContinue();
				} else {
					this.helper.setTipo(selection);
					if (!this.getOutput().isBlank()) {
						System.out.println(this.currentOutput);
					}
					this.state = State.paramInitialization;
					System.out.println("Parameters initialization (-1 to back)");
					this.loopFunction();
				}
			}
		}		
	}
	
	private void paramInitializationUI() {
		if (this.selection == -1) {
			this.state = State.tipeSelection;
			this.selection = 0;
		} else {
			for (int i = 0; i < this.paramInfo.size(); i++) {
				System.out.print(this.paramInfo.get(i) + ": ");
				this.helper.setParametro(i, this.inputLine());
				if (this.currentInputLine.equals("-1")) {
					this.state = State.tipeSelection;
					this.selection = 0;
					break;
				} else if (this.isErrorOrPrintOutput())
					i--;
			}
			if (this.state == State.paramInitialization) {
				this.generateOverviewMenu();
				this.state = State.overview;
			}
		}
	}
	
	private void overviewUI() {
		switch(this.selection) {
		case -1:
			break;
		default:
			if(this.selection>0) {
				this.selection-=4;
				if(this.selection>0 && this.selection<this.paramInfo.size())
					do {
						System.out.print(this.paramInfo.get(this.selection) + "\n"
								+ "-1 to cancel\nvalue: ");	
						if (!this.inputLine().equals("-1")) {
							this.helper.setParametro(this.selection, this.currentInputLine );
							this.selection = 0;
							break;
						} 
					}while(this.isErrorOrPrintOutput());
			}
			else
				System.out.println("Invalid selection");
			break;
		case 0:
			if(!this.helper.Istanzia())
				System.out.println("Something went wrong or some parameters are missing");
			else
				this.selection = -1;
			this.isErrorOrPrintOutput();
			break;
		case 1:
			this.state = State.tipeSelection;
			break;
		case 2:
			System.out.print("Set name:");
			this.helper.setNome(this.inputLine());
			break;
		case 3:
			System.out.print("Set address:");
			this.helper.setIndirizzo(this.inputLine());
			break;
	}
	this.generateOverviewMenu();
	}
	
	@Override
	protected void loopFunction() {
		switch (this.state) {
		default:
			this.impossibleState();
			break;

		case tipeSelection:
			this.tipeSelectionUI();
			break;
			
		case paramInitialization:
			this.paramInitializationUI();
			break;
			
		case overview:
			this.overviewUI();
			break;

		}
	}
}
