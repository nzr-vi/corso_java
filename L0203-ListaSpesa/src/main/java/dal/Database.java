package dal;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.AccountException;

import controller.CartCtrl;
import model.Prodotto;


public class Database {

	private static Database instance;

	private int idCounter = 0;
	private Map<String,PersonaDAO> listaPersone;

	private Database() {
		listaPersone = new HashMap<>();
		try {
			var p = this.addPerson("Giuseppe", "Verdi", "g.verdi@gmail.com", "123456");
			CartCtrl.addProduct(p, "Mela",1.49, "Ortofrutta");
			CartCtrl.addProduct(p, "Pera",1.58, "Ortofrutta");
			CartCtrl.addProduct(p, "Ananas",2.49, "Ortofrutta");
			CartCtrl.addProduct(p, "Arancia",1.29, "Ortofrutta");
			this.addPerson("Paperino", "Paperino", "paperino@gmail.com", "123456");
			this.addPerson("Topo", "Lino", "topolino@gmail.com", "123456");
		} catch (AccountException e) {
			e.printStackTrace();
		}
	}

	public static Database getDatabase() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public Map<String,PersonaDAO> getPersone() {
		return listaPersone;
	}

	public PersonaDAO addPerson(String name, String surname, String email, String pwd) throws AccountException {
		if(!this.listaPersone.containsKey(email)) {
			PersonaDAO newPerson = new PersonaDAO(++this.idCounter, name, surname, email, pwd);
			this.listaPersone.put(email, newPerson);
			return newPerson;
		}
		throw new AccountException("Account already created");
	}
}
