package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ContoBanca {

	class Movement {
		final private long amount;
		final private Date time;
		final private boolean allowed;
		private String info;

		public Movement(long amount, boolean allowed) {
			this.amount = amount;
			this.allowed = allowed;
			this.time = new Date();
			
			if(this.allowed)
				balance += amount;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(100);
			if(this.allowed)
				sb.append("-denied-");
			sb.append(amount+" ["+time+"]");
			return sb.toString();
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}
	}

	private final long ID;
	private final Date creationTime;
	private Person user;
	private long balance;
	private List<Movement> movements;
	private String pwd;

	public String getPwdHash() {
		return Integer.toString(pwd.hashCode());
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public boolean auth(String pwd) {
		return this.pwd.equals(pwd);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
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
		ContoBanca other = (ContoBanca) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	public Person getUser() {
		return user;
	}

	public void setUser(Person user) {
		this.user = user;
	}

	public long getID() {
		return ID;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public long getBalance() {
		return balance;
	}

	public ContoBanca(long idCode, Person user) {
		this(idCode,user,0);
	}
	
	public ContoBanca(long idCode, Person user, long startingBalance) {
		this.ID = idCode;
		this.creationTime = new Date();
		this.movements = new LinkedList<ContoBanca.Movement>();
		this.balance = startingBalance;
		this.pwd = Integer.toString(Long.hashCode(this.ID));
	}

	private void tryMovement(long amount,IMovement moneyMover) throws BankAccountException{
		Movement depo = null;
		try {
			depo = moneyMover.move(amount);
		} catch (BankAccountException ex) {
			depo = new Movement(amount, false);
			depo.setInfo(ex.getMessage());
			throw ex;
		}
		finally {
			this.movements.add(depo);
		}
	}
	
	public void deposit(long amount) throws BankAccountException {
		this.tryMovement(amount, (a)->{
			if (amount <= 0)
				throw new BankAccountException("Can't deposit negative amount");
			return new Movement(a, true);
		});
	}

	public void withdrawal(long amount) throws BankAccountException {
		this.tryMovement(-amount, (a)->{
			if(-amount>this.balance)
				throw new BankAccountException("Can't withdarw more than you own");
			if(amount<0)
				throw new BankAccountException("Can't withdarw negative value");
			return new Movement(a, true);
		});
	}
}
