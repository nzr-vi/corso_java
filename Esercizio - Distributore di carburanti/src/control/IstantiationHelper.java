package control;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IstantiationHelper extends Assistant {
	private String nome;
	private String indirizzo;
	@SuppressWarnings("rawtypes")
	private Class tipo;
	private Parameter[] paramsInfo;
	private boolean parametersOk = false;
	Object[] parametri;

	
	@Override
	public String toString() {
		return this.tipo.getName() + " [" + nome + ", " + indirizzo + "]";
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

	public String[] getTipes() {
		return Controller.possibiliDistributori.stream().map(c->c.getSimpleName()).toArray(s->new String[s]);
	}
	
	public String getTipo() {
		return tipo.getName();
	}

	public List<String> setTipo(int tipo) {
		try {
			this.tipo = Controller.possibiliDistributori.get(tipo);
			this.paramsInfo = this.tipo.getConstructors()[0].getParameters();
			this.parametri = new Object[this.paramsInfo.length];
			return Stream.of(paramsInfo).map(p -> p.getName() +" ("+ p.getType().getSimpleName()+")").collect(Collectors.toList());
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return null;
	}

	public String[] getParametri() {
		return Stream.of(this.parametri).map(o->{if(o!=null)return o.toString(); return "null";}).toArray(s->new String[s]);
	}

	private void checkAllParamsOk() {
		boolean areParamOk = true;
		for(var param : this.parametri) {
			if(param==null)
				areParamOk = false;
		}
		this.parametersOk = areParamOk;
	}
	
	public void setParametro(int id, String value) {
		try {
			Optional<Method> parser = Stream.of(this.paramsInfo[id].getType().getMethods()).
					filter(m -> {
				Parameter[] ps = m.getParameters();
				return m.getName().contains("parse") &&
						ps.length == 1 &&
						ps[0].getType().equals(String.class);
			}).findFirst();
			if (parser.isPresent()) {
				this.parametri[id] = parser.get().invoke(null, value);
			}
			
		} catch (Exception e) {
			this.manager.error.add("failed to retrive parser for parameter");
		}
		finally {
			this.checkAllParamsOk();
		}
	}

	public IstantiationHelper(Controller manager) {
		super(manager);
	}

	public boolean canInstantiate() {
		return this.tipo != null && this.parametersOk;
	}

	public boolean Istanzia() {
		if (!this.canInstantiate())
			return false;

		this.manager.creaDistributore(tipo, parametri);
		if (this.nome != null)
			this.manager.getActive().setNome(nome);
		if (this.indirizzo != null)
			this.manager.getActive().setIndirizzo(indirizzo);
		return true;
	}
}
