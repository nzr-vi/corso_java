import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String... args) {
		
//		List<Statico> lista = new ArrayList<>();
//		for(int i = 0;i<10; i++) {
//			lista.add(new Statico());
//		}
//		
//		Statico elemento = new Statico();
//		elemento.aggiungi100();
//		elemento = new Statico();
//		
//		System.out.println(Statico.getIdCounter());
//		
//		SingleTon stFromElemento = elemento.sayHello();
//		
		SingleTon st = SingleTon.getInstance();
//		st.sayHello();
		
		//String testo = "aaaaa bbbbb fffff sono mappato";
		//System.out.println(st.reMap(testo));
	
		System.out.println(st.getValue("mario"));
		try {
			//System.out.println(st.getValue("gennaro").getTesto());
			throw new NullPointerException("yuechsueijc");
		}
		catch (NullPointerException e) {
			System.err.println("MI SONO ROTTO!");
		}
		System.out.println(st.getValue(null));
	}
}
