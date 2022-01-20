package view;

import control.Banca;
import model.Person;

public class BancaTest {

	private static void loginAndGetBalance(long ID, Banca bank) {
		if(	bank.login(ID, Integer.toString(Long.hashCode(ID)))) {
			System.out.println(bank.balance());
			bank.logout();
		}
	}
	
	public static void main(String[] args) {

		Banca bank = new Banca("Invesa");
		Person gianni = bank.addPerson("Gianni","Rossi","10/01/1993");
		Person moreno = bank.addPerson("Moreno","Bianchi","18/02/1969");
		long acc_moreno_1 = bank.createBankAccount(moreno);
		if(	bank.login(acc_moreno_1, Integer.toString(Long.hashCode(acc_moreno_1)))) {
			bank.deposit(100000);
			System.out.println(bank.balance());
			bank.withdraw(50000);
			System.out.println(bank.balance());
			bank.withdraw(40000);
			System.out.println(bank.balance());
			bank.logout();
		}
		
		long acc_moreno_2 = bank.createBankAccount(moreno, 312900);
		loginAndGetBalance(acc_moreno_2, bank);
		
		long acc_gianno = bank.createBankAccount(gianni, 690000);
		loginAndGetBalance(acc_gianno, bank);
		
		System.out.println("Average balance: "+String.format("%06.2f", bank.getAvgBalances()/100));
		
	}
}
