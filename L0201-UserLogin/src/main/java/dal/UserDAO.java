package dal;

public class UserDAO {

	private String nome;
	private String cognome;
	private String email;
	private String pwd;
	private boolean logged;
	
	public UserDAO(String nome, String cognome, String email, String pwd) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.pwd = pwd;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	
}
