import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SingleTon {

	private static SingleTon instance;
	
	public static SingleTon getInstance() {
		if(instance==null)
			instance = new SingleTon();
		return instance;
	}
	
//	private Map<String, String> mappa;
//	
//	public String reMap(String testo) {
//		for(var entry:this.mappa.entrySet()) {
//			testo = testo.replace(entry.getKey(),entry.getValue());
//		}
//		return testo;
//	}
//	public String reMap(String testo) {
//		for(var entry:this.mappa.entrySet()) {
//			testo = testo.replace(entry.getKey(),entry.getValue().getTesto());
//		}
//		return testo;
//	}

	
	private Map<Parola, Parola> mappa;
	
	
	public Parola getValue(String key) {
		return this.mappa.get(key);
	}
	
	private SingleTon() {

		this.mappa = new HashMap<>();
		
		this.mappa.put(new Parola("a"), new Parola(" ||MARIO|| "));
		this.mappa.put(new Parola("b"), new Parola(" ||re|| "));
		this.mappa.put(new Parola("v"), new Parola(" ||MAR2	frqwIO|| "));
		this.mappa.put(new Parola("c"), new Parola(" ||csd|| "));
		this.mappa.put(new Parola("r"), new Parola(" ||asc|| "));
		this.mappa.put(null, new Parola(" ||NULL|| "));
		//this.mappa
	}
	
	public void sayHello() {
		System.out.println("HELLO!!");
	}
}
