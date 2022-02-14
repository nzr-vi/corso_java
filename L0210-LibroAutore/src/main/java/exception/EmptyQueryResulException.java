package exception;

public class EmptyQueryResulException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String query;
	
	@Override
	public String getMessage() {
		
		String message =super.getMessage();
		if(message == null)
			message = "";
		else if(!message.isEmpty())
			message =  "\n\nMESSAGE:\n"+message;
		message = "QUERY:\n"+this.query+message;
		
		return message;
	}

	public EmptyQueryResulException(String query, String message) {
		super(message);
		this.query = query;
	}
	
	public EmptyQueryResulException(String query) {
		super("");
		this.query = query;
	}
}
