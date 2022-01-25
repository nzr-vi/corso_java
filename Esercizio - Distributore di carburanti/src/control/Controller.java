package control;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Distributore;
import model.DistributoreException;
import model.Elettrico;
import model.Gas;
import model.Gestore;
import model.Ordinario;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Controller {

	private static Controller instance;
	public static Controller getInstance() {
		if(instance==null)
			instance = new Controller();
		return instance;
	}
	
	final static List<Class> possibiliDistributori;
	static {
		possibiliDistributori = new ArrayList<>();
		possibiliDistributori.add(Ordinario.class);
		possibiliDistributori.add(Gas.class);
		possibiliDistributori.add(Elettrico.class);
	}
	private Gestore gestore;

	Queue<String> output, error;

	Distributore getActive() {
		return this.gestore.getActive();
	}

	private Controller() {
		this.gestore = new Gestore();
		this.output = new LinkedList<>();
		this.error = new LinkedList<>();
	}

	public boolean hasActive() {
		return this.gestore.getActive() != null;
	}

	void creaDistributore(Class type, Object... params) {

		Optional<Class> match = possibiliDistributori.stream().filter(dist -> dist.equals(type)).findFirst();
		if (match.isPresent()) {
			try {
				Class[] parameterTypes = Stream.of(params).map(o -> o.getClass()).toArray(size -> new Class[size]);
				var newDis = (Distributore) match.get().getConstructor(parameterTypes).newInstance(params);
				this.gestore.addDistributore(newDis);
				this.gestore.setActive(newDis);
				this.output.add(match.get().getName() + " correctly created");
			} catch (DistributoreException | InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				this.output.add(e.getMessage());
				System.err.println(e.getMessage());
			}
		} else
			this.output.add("no " + type + " available as distributore type");
	}

	private String joinInfoAndClear(Queue<String> infos) {
		String toReturn = infos.stream().collect(Collectors.joining("\n"));
		infos.clear();
		return toReturn;
	}

	public String getError() {
		return this.joinInfoAndClear(error);
	}

	public String getOutput() {
		return this.joinInfoAndClear(output);
	}

	Gestore getGestore() {
		return this.gestore;
	}

	public String getActiveDistributor() {
		if(this.hasActive())
			return this.getActive().toString();
		return "";
	}
	
	
	public int getDisNumber() {
		return this.gestore.getDistributori().size();
	}
	
	public Stream<List<String>> getLogs(){
		return this.gestore.getDistributori().stream()
			.map(d->{
				var operations = d.getOperations().stream()
						.map(o->"  "+o).collect(Collectors.toList());
				operations.add(0, d.toString());
				return operations;
			});
	}
}