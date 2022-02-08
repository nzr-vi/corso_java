package dal;

import java.util.LinkedList;
import java.util.List;

import model.Prodotto;

public class PersonaDAO {
	
	private int ID;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private boolean login;

	private int incrementalID;
	private List<Prodotto> shoppingCart;

	public PersonaDAO(int ID, String nome, String cognome, String email, String password) {
		this.ID = ID;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.shoppingCart = new LinkedList<>();
	}

	public PersonaDAO() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public List<Prodotto> getShoppingCart() {
		return shoppingCart;
	}

	public int getID() {
		return ID;
	}

	public int getIncrementalID() {
		return incrementalID;
	}

	public void setIncrementalID(int incrementalID) {
		this.incrementalID = incrementalID;
	}
	
}