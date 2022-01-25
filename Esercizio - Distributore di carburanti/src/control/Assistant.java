package control;

public abstract class Assistant {
	protected Controller manager;
	
	public Assistant(Controller manager) {
		this.manager = manager;
	}
	
	public void getError() {
		this.manager.getError();
	}
	
	public void getOutput() {
		this.manager.getOutput();
	}
	
	protected void addText(String message) {
		this.manager.output.add(message);
	}
	
	protected void addError(String message) {
		this.manager.error.add(message);
	}
}
