package control;

import java.util.List;
import java.util.stream.Collectors;

import model.Distributore;

public class SelectorHelper extends Assistant {
	
	List<Distributore> lastSearch;
	
	public SelectorHelper(Controller manager) {
		super(manager);
	}

	public boolean canChoose() {
		return this.manager.getDisNumber()>0;
	}
	
	public void findByIndex(int index) {
		lastSearch = this.manager.getGestore().getDistributori();
		this.pickFromLS(index);
	}
	
	public void pickFromLS(int index) {
		if(index < 0)
			this.manager.error.add("Negative index!");
		else if(index < lastSearch.size())
		{
			this.manager.getGestore().setActive(lastSearch.get(index));
			this.manager.output.add("selected:"+lastSearch.get(index));
		}
		else
			this.manager.error.add("Index out of range");
	}
	
	public void processStringFind() {
		if(lastSearch.size()==1)
		{
			this.manager.getGestore().setActive(lastSearch.get(0));
			this.addText("Found: "+lastSearch.get(0));
		}
		else if(lastSearch.size()>1) {
			this.addError("1+");
			String message = lastSearch.size()+" distributori found\n";
			for(int i = 0; i<lastSearch.size(); i++)
				message+=i+": "+lastSearch.get(i)+"\n";
			message+="pick one:";
			this.addText(message);
		}
		else
			this.addText("Not found");
	}
	
	public void findByName(String name) {
		lastSearch = this.manager.getGestore().getDistributori();
		if(name == null || name.isEmpty())
			this.manager.error.add("Name is null or empty");
		else 
		{
			lastSearch = lastSearch.stream().
					filter(d->d.getNome().contains(name)).collect(Collectors.toList());
			
			this.processStringFind();
		}
	}
	
	public void findByAddress(String address) {
		lastSearch = this.manager.getGestore().getDistributori();
		if(address == null || address.isEmpty())
			this.manager.error.add("Address is null or empty");
		else 
		{
			lastSearch = lastSearch.stream().
					filter(d->d.getIndirizzo().contains(address)).collect(Collectors.toList());
			
			this.processStringFind();
		}
	}

	public void getFullList() {
		int index = 0;
		for(var d:this.manager.getGestore().getDistributori()) {
			this.addText(index++ +": "+d.toString());
		}
	}
}
