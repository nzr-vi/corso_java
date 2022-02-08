package controller;

import java.util.List;

import javax.security.auth.login.AccountException;

import dal.Database;
import dal.PersonaDAO;
import exceptions.NoLoginException;
import jakarta.servlet.http.HttpSession;
import model.Prodotto;

public class CartCtrl {

	Database db;

	public CartCtrl() {
		db = Database.getDatabase();
	}

	public PersonaDAO getPerson(HttpSession session) throws NoLoginException, AccountException {
		Object ob = session.getAttribute("email");
		if (ob == null)
			throw new NoLoginException();
		return this.getPerson(ob.toString());
	}

	public PersonaDAO getPerson(String email) throws AccountException {
		var persons = this.db.getPersone();
		if (!persons.containsKey(email))
			throw new AccountException("Account doesn't exist");
		return persons.get(email);
	}

	public List<Prodotto> getCart(String email) throws AccountException {
		return this.getPerson(email).getShoppingCart();
	}

	public int addPerson(String name, String surname, String email, String pwd) throws AccountException {
		return db.addPerson(name, surname, email, pwd).getID();
	}
	
	public static void addProduct(PersonaDAO user, String name, double prezzo, String reparto ) {
		int id = user.getIncrementalID()+1;
		user.getShoppingCart().add(new Prodotto(id, name, prezzo, reparto));
		user.setIncrementalID(id);
	}
}
