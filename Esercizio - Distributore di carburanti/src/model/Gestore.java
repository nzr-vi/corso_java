package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Gestore {
	private List<Distributore> distributori;
	private Distributore active;
	
	public void setActive(Distributore active) {
		this.active = active;
	}

	public Distributore getActive() {
		return active;
	}
	
//	private void populateForDebug() {
//		Distributore tmp = new Gas(100l,100l,100l);
//		tmp.setNome("mar");
//		tmp.setIndirizzo("via albero");
//		this.distributori.add(tmp);
//		tmp = new Gas(13l,142l,123l);
//		tmp.setNome("mozzo");
//		tmp.setIndirizzo("via albero");
//		this.distributori.add(tmp);
//		tmp = new Gas(10l,1l,430l);
//		tmp.setNome("micc");
//		tmp.setIndirizzo("via albero");
//		this.distributori.add(tmp);
//		tmp = new Gas(1l,9l,80l);
//		tmp.setNome("ma");
//		tmp.setIndirizzo("via alberello");
//		this.distributori.add(tmp);
//	}
	
	public Gestore() {
		this.distributori = new LinkedList<Distributore>();
		//this.populateForDebug();
	}
	
	public List<Distributore> getDistributori() {
		return new ArrayList<Distributore>(distributori);
	}
	
	public void addDistributore(Distributore dis) throws DistributoreException {
		if(dis==null)
			throw new DistributoreException("Can't add a null distributor");
		this.distributori.add(dis);
	}
	
	public void removeDistributore(Distributore dis) {
		this.distributori.remove(dis);
	}
}
