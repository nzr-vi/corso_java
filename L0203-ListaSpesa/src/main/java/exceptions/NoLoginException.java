package exceptions;

public class NoLoginException extends AccountException{

	private static final long serialVersionUID = 1L;

	public NoLoginException() {
		super("user not logged in");
	}
}
