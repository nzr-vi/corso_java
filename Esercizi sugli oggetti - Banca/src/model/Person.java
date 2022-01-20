package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Person {
	private final String name,surname;
	private final Date birth;
	private Set<ContoBanca> bankAccounts;
	
	public Person(String name, String surname, String birth) throws ParseException {
		
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.birth = sourceFormat.parse(birth);
		this.name = name;
		this.surname = surname;
		this.bankAccounts = new HashSet<>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birth == null) ? 0 : birth.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (birth == null) {
			if (other.birth != null)
				return false;
		} else if (!birth.equals(other.birth))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Date getBirth() {
		return birth;
	}

	
	public Collection<Long> getBankAccounts(){
		return this.bankAccounts.stream()
				.map(ContoBanca::getID).collect(Collectors.toList());
	}
	
	public void addBankAccount(ContoBanca account) throws BankAccountException {
		if(account == null)
			throw new BankAccountException("["+this.hashCode()+"]Can't add a null bankAccount");
		if(account.getUser()!=this && account.getUser()!=null) {
			System.out.println("Transferring account" 
					+"\nfrom:\t"+account.getUser().hashCode()
					+"\nto:\t"+this.hashCode());
			account.setUser(this);
		}
		this.bankAccounts.add(account);
	}
	
	public void removeBankAccount(long ID) throws BankAccountException {
		Optional<ContoBanca> toBeRemoved = this.bankAccounts.stream()
				.filter(a->a.getID()==ID).findFirst();
		if(toBeRemoved.isPresent())
			this.bankAccounts.remove(toBeRemoved.get());
		else
			throw new BankAccountException("[U"+this.hashCode()+"] doesn't own any bank ["+ID+"]");
	}
	
	@Override
	public String toString() {
		return name+" "+surname+" [" + birth + "]";
	}		
}
