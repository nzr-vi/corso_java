package dal;

import java.util.ArrayList;
import java.util.List;

public class Database {

	private List<UserDAO> userList;
	private static Database instance;
	
	private void populateDb() {
		this.userList.add(new UserDAO("Giuseppe", "Verdi", "gv@gmail.com", "123456"));
		this.userList.add(new UserDAO("Mario", "Monti", "mm@gmail.com", "123456"));
		this.userList.add(new UserDAO("Pippo", "Blue", "pb@gmail.com", "123456"));
		this.userList.add(new UserDAO("Paperino", "Bianchi", "pap@gmail.com", "123456"));
		this.userList.add(new UserDAO("Antho", "Banche", "anba@gmail.com", "123456"));
		this.userList.add(new UserDAO("Topo", "Lino", "topone@gmail.com", "123456"));
	}
	
	public List<UserDAO> getUserList() {
		return userList;
	}

	private Database() {
		this.userList = new ArrayList<>();
		this.populateDb();
	}
	
	public static Database getInstance() {
		if(instance == null)
			instance = new Database();
		return instance;
	}
}
