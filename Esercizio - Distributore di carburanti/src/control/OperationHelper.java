package control;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import model.Distributore;
import model.DistributoreException;
import model.Rifornimenti;

public class OperationHelper extends Assistant {

	private Operation selected;
	private String[] params;
	
	private interface IRunnableOperation{
		public void run(Distributore active, String... params) throws OperationException,DistributoreException;
	}
	
	private class OperationException extends Exception{
		private static final long serialVersionUID = -2175617460310607898L;
		
		public OperationException(String message) {
			super(message);
		}
	}
	
	private class Operation {
		private String help;
		private String code;
		private String[] paramsInfo;
		private IRunnableOperation executer;
		
		public String getHelp() {
			return help;
		}

		public String getCode() {
			return code;
		}

		void setParamsInfo(String... paramsInfo) {
			this.paramsInfo = paramsInfo;
		}
		
		public String[] getParamsInfo() {
			return paramsInfo;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((code == null) ? 0 : code.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Operation other = (Operation) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (code == null) {
				if (other.code != null)
					return false;
			} else if (!code.equals(other.code))
				return false;
			return true;
		}

		public Operation(String help, String code, IRunnableOperation executer) {
			super();
			this.help = help;
			this.code = code;
			this.executer = executer;
		}
		
		public boolean tryRun(String... params) {
			if(params.length == 1 && params[0].equals("man") || params[0].equals("help"))
				manager.output.add("<< "+this.code+" - help >>\n"+this.help);
			else {
				try {
					this.executer.run(manager.getActive(), params);
				}catch (Exception e) {
					manager.error.add(e.getMessage());
					return false;
				}
			}
			return true;
		}

		private OperationHelper getEnclosingInstance() {
			return OperationHelper.this;
		}
	}
	
	private Set<Operation> operations;
	
	public OperationHelper(Controller manager) {
		super(manager);
		this.operations = new HashSet<>();
		Operation tmp = new Operation("carica il distributore specificandone il"
				+ " tipo di carburante e il quantitativo caricato in ml", "caricoDistributore",
				(a,p)->this.load(a, p));
		tmp.setParamsInfo("String tipoRifornimento","Long amount (ml)");
		this.operations.add(tmp);

		tmp =	new Operation("rifornisci specificando autovettura, il tipo di carburante"
				+ " il quantitativo caricato in ml", "rifornimento",
				(active,args)->this.unload(active, args));
		tmp.setParamsInfo("String tipoRifornimento","Long amount (ml)", "String autovettura");
		this.operations.add(tmp);
	}
	
	public boolean checkComposing(String s, boolean isComposing, List<String> parameters, StringBuilder tmp ) {
		int index = s.indexOf("\"")+1;
		if(isComposing){
			if(index<0)
				tmp.append(s+" ");
			else
			{
				tmp.append(s.substring(0,index));
				parameters.add(tmp.toString());
				tmp.setLength(0);
				isComposing = this.checkComposing(s.substring(index), false, parameters, tmp);
			}
		}
		else if(index<0)
			parameters.add(s);
		else
		{
			parameters.add(s.substring(0,index));
			isComposing = this.checkComposing(s.substring(index), true, parameters, tmp);
		}
		
		return isComposing;
	}
	
	public void interpretOperation(String line) throws OperationException {
		String[] splitted = line.split(" ");
		List<String> parameters = new LinkedList<>();
		StringBuilder tmp = new StringBuilder();
		boolean isComposing = false; 
		for(String s:splitted) {
			isComposing = this.checkComposing(s, isComposing, parameters, tmp);
		}
		if(isComposing)
			throw new OperationException("Composit param not closed (make sure to close the \")");
		
		if(parameters.size()>1) {
			String commandName = parameters.get(0).toLowerCase();
			parameters.remove(0);
			Optional<Operation> op = this.operations.stream().
				filter(o->o.getCode().toLowerCase().equals(commandName)).
				findFirst();
			if(op.isPresent()) {
				op.get().tryRun(parameters.toArray(new String[0]));
			}
			else
				throw new OperationException("Command unknown");
		}
		else
			throw new OperationException("Failed to interpret");
	
	}
	
	private Rifornimenti parseRifornimento(String arg) throws OperationException {
		Rifornimenti tipo = null;
		for(var eRif: Rifornimenti.values()) {
			if(eRif.toString().toLowerCase().equals(arg.toLowerCase()))
			{
				tipo = eRif;
				break;
			}
		}
		if(tipo == null)
			throw new OperationException("Carburante unknown");
		return tipo;
	}
	
	private long parseAmount(String arg) throws OperationException {
		long amount = Long.parseLong(arg);
		return amount;
	}
	
	private String parseAutovettura(String arg) throws OperationException {
		String autovettura = arg;
		if(autovettura==null||autovettura.isBlank())
			throw new OperationException("Autovettura is null or empty");
		return autovettura;
	}
	
	private void load(Distributore active, String... args) throws OperationException,DistributoreException {
			if(args.length!=2)
				throw new OperationException("Wrong Args");
			else {
				Rifornimenti tipo = this.parseRifornimento(args[0]);
				long amount = this.parseAmount(args[1]);
				active.carica(tipo, amount);
		}
	}
	
	private void unload(Distributore active, String... args) throws OperationException,DistributoreException {
		if(args.length!=3)
			throw new OperationException("Wrong Args");
		else {
			Rifornimenti tipo = this.parseRifornimento(args[0]);
			long amount = this.parseAmount(args[1]);
			active.rifornisci(tipo, amount, this.parseAutovettura(args[2]) );
		}
	}
	
	public String[] getPossibleOperations() {
		return this.operations.stream().map(op->op.getCode()).toArray(s->new String[s]);
	}
	
	public List<String> getLogs() {
		if(manager.hasActive())
			return this.manager.getActive().getOperations();
		this.manager.error.add("Nessun distributore selezionato");
		return new ArrayList<String>();
	}
	
	public void runOperation() {
		try {
			this.selected.tryRun(this.params);
			this.manager.output.add("Operation successful");
		}
		catch(Exception ex) {
			this.manager.error.add(ex.getMessage());
		}
	}
	
	public void setParam(int index, String param) {
		try {
			if(param == null || param.isEmpty())
				throw new OperationException("null param");
			this.params[index] = param;
			this.manager.output.add("OK");
		}
		catch(Exception ex) {
			this.manager.error.add(ex.getMessage());
		}
	}
	
	public String[] getParamInfo() {
		return this.selected.getParamsInfo();
	}
	
	public void setOperation(String code) {
		try {
			String opCode  = code.toLowerCase();
			Optional<Operation> op = this.operations.stream().
				filter(o->o.getCode().toLowerCase().equals(opCode)).
				findFirst();
			if(op.isPresent()) {
				this.selected = op.get();
				this.manager.output.add("Operation selected, specify params");
				this.params = new String[this.selected.getParamsInfo().length];
			}
			else
				throw new OperationException("Command unknown");
		}
		catch(Exception ex) {
			this.manager.error.add(ex.getMessage());
		}
	}
}
