package control;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import model.BankAccountException;
import model.ContoBanca;
import model.Person;

public class Banca {
	public final long FAILED_BANK_ACCOUNT_CREATION_CODE = 0x10;
	
	private long accountIDGen = 0x100;

	private String name;
	private Set<Person> clients = new HashSet<>();
	private Map<Long,ContoBanca> bankAccounts = new HashMap<>();

	private ContoBanca authAccount = null;
	
	public Banca(String name) {
		this.name = name;
	}
	
	public Person addPerson(String name, String surname, String birthDate) {
		try {
			Person newClient = new Person(name, surname, birthDate);
			if (this.clients.add(newClient)) {
				System.out.println("Client [" + newClient.hashCode() + "] added correctly!");
				return newClient;
			}
			else {
				System.err.println("Client already present, returning client in db!");
				return this.clients.stream().
						filter(p->p.equals(newClient)).findFirst().get();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public long createBankAccount(Person person) {
		return createBankAccount(person, 0);
	}

	public long createBankAccount(Person client, long startingAmount) {
		if (client == null) {
			System.err.println("Client is null");
		}
		else if (startingAmount < 0) {
			System.err.println("Starting amount can't be null");
		}
		else {
			try {
				long bankAccountID = this.accountIDGen++;
				ContoBanca newAccount = new ContoBanca(bankAccountID, client, startingAmount);
				this.bankAccounts.put(bankAccountID, newAccount);
				client.addBankAccount(newAccount);
				return bankAccountID;
			} catch (BankAccountException e) {
				e.printStackTrace();
			}
		}
		return FAILED_BANK_ACCOUNT_CREATION_CODE;
	}

	private ContoBanca getAccount(long ID) throws BankAccountException
	{
		if(!this.bankAccounts.containsKey(ID))
			throw new BankAccountException("Account Not Found");
		return this.bankAccounts.get(ID);
	}
	
	public boolean login(long bID, String pwd) {
		try {
			ContoBanca account = this.getAccount(bID);
			if(account.auth(pwd))
			{
				this.authAccount = account;
				System.out.println("Welcome "+this.authAccount.getUser()+"\nBA:"+this.authAccount.getID());
				return true;
			}
			else
				System.out.println("wrogn ID or pwd");
		}catch(BankAccountException ex) {
			System.err.println("Login failed: "+ex.getMessage());
		}
		return false;
	}
	
	public void logout() {
		this.authAccount = null;
	}
	
	public void deposit(long amount) {
		if(this.authAccount!=null) {
			try {
				this.authAccount.deposit(amount);
			} catch (BankAccountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void withdraw(long amount) {
		if(this.authAccount!=null) {
			try {
				this.authAccount.withdrawal(amount);
			} catch (BankAccountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String balance() {
		if(this.authAccount!=null) {
			return "["+this.authAccount.getID()+"] "+ this.authAccount.getBalance()/100f;
		}
		return "You ain't logged into any bank account!";
	}
	
	public double getAvgBalances() {
		return this.bankAccounts.values().stream()
			.collect(Collectors.averagingLong(account->account.getBalance()));
	}
}
