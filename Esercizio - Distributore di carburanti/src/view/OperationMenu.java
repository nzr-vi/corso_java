package view;

import java.util.Scanner;

import control.OperationHelper;

public class OperationMenu extends Menu{

	OperationHelper helper;
	boolean requireMenuRegeneration;
	
	public OperationMenu(Scanner input) {
		super(input, "");
		helper = new OperationHelper(gestoreRifornimenti);
		this.generateMenu();
	}

	private void generateMenu() {
		this.menu = "|| Operation Menu ||\n"
			+ (gestoreRifornimenti.hasActive()?
					"Distributore: "+gestoreRifornimenti.getActiveDistributor()+
					"\n1: Cambia Distributore Selezionato"
					+ "\n2: Carica Distributore"
					+ "\n3: Rifornimento Auto"
					+ "\n4: Log Operazioni":
					"1: Seleziona Distributore")+
			"\n5: EXIT";
		this.requireMenuRegeneration = false;
	}
	
	private boolean distributoreCheck() {
		if(!gestoreRifornimenti.hasActive())
		{
			System.out.println("Seleziona un distributore prima di eseguire un operazione");
			return false;
		}
		else
			return true;
	}
	
	private void helpedOperation(String operation) {
		if (distributoreCheck()) {
			this.helper.setOperation(operation);
			if(!this.isErrorOrPrintOutput()) {
				String[] pInfo = this.helper.getParamInfo();
				for(int i = 0; i<pInfo.length; i++) {
					System.out.print(pInfo[i]+": ");
					this.helper.setParam(i, this.inputLine());
					if(this.isErrorOrPrintOutput())
						return;
				}
				this.helper.runOperation();
			}
		}
	}
	
	@Override
	protected void loopFunction() {

		switch(this.selection) {
			default:
				this.selection = 0;
				System.out.println("unknown operation");
				break;
		
			case 1:
				{
					Menu menu = new SelectionMenu(input); 
					menu.loopSelection();
					this.requireMenuRegeneration = true;
				}
				break;
			
			case 2:
				this.helpedOperation("caricoDistributore");
				break;
			
			case 3:
				this.helpedOperation("rifornimento");
				break;
			
			case 4:
				if (distributoreCheck()) {
					System.out.println(this.helper.getLogs());
				}
				break;
			
			case 5:
				this.selection = -1;
				break;
		}

		this.isErrorOrPrintOutput();
		if(this.requireMenuRegeneration)
			this.generateMenu();
	}
}
