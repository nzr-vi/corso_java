package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Distributore {
	protected String nome, indirizzo;

	protected Map<Rifornimenti, IRifornibile> rifornimenti;
	protected List<Operazione> operations;

	public static String printAmount(long amount) {
		amount /= 10;
		return amount / 100 + "." + amount % 100 + "ml";
	}

	@Override
	public String toString() {
		return "[" + nome + ", " + indirizzo + ", "+ this.getClass().getSimpleName()+"]";
	}

	protected Tank addTank(Rifornimenti tipo) {
		Tank tankTipo = new Tank(tipo.toString());
		this.rifornimenti.put(tipo, tankTipo);
		return tankTipo;
	}

	protected Distributore() {
		this.operations = new LinkedList<>();
		this.rifornimenti = new HashMap<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public List<String> getRifornimenti() {
		return this.rifornimenti.keySet().stream().
				map(rif->rif.toString()).collect(Collectors.toList());
	}

	public List<String> getOperations() {
		return operations.stream().map(Operazione::toString)
				.collect(Collectors.toList());
	}

	protected Operazione addOperation(Operazione operation) {
		this.operations.add(operation);
		return operation;
	}

	protected void tryOperation(Operazione current) throws DistributoreException {
		try {
			Rifornimenti tipo = current.getTipo();

			if (!this.rifornimenti.containsKey(tipo))
				throw new DistributoreException(tipo + " non supportato");
			if (current.getAmount() < 0 ^ current.getClass().equals(Rifornimento.class))
				throw new DistributoreException(current.getClass().getSimpleName() + " di un val neg.");

			this.rifornimenti.get(tipo).variazione(current.getAmount());
		} catch (DistributoreException e) {
			current.setError(e.getMessage());
			throw e;
		}
	}

	public void carica(Rifornimenti tipo, long amount) throws DistributoreException {
		this.tryOperation(this.addOperation(new Caricamento(tipo, amount)));
	}

	public void rifornisci(Rifornimenti tipo, long amount, String auto) throws DistributoreException {
		this.tryOperation(this.addOperation(new Rifornimento(tipo, amount, auto)));
	}
}
