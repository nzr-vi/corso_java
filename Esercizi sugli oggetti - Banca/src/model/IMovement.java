package model;

public interface IMovement {
	public ContoBanca.Movement move(long amount) throws BankAccountException;
}
