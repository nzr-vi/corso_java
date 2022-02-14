
public class Statico {

	private static int idCounter = 0;
	public  static int getIdCounter(){
		return idCounter;
	}
	
	
	
	private final int id;
	
	public Statico() {
		this.id = Statico.idCounter++;
		
		System.out.println("sono stato creato: id: "+this.id);
	}
	
	public SingleTon sayHello() {
		SingleTon st = SingleTon.getInstance();
		st.sayHello();
		return st;
	}
	
	public void aggiungi100() {
		idCounter+=100;
	}

	public int getId() {
		return id;
	}
	
}
