package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Studente;

public class Database {

	private static Database instance;

	public static Database getDatabase() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	private Connection con;

	private final static String DB_URL = "jdbc:mysql://localhost:3306/generation?";
	private final static String DB_USER = "app_generation";
	private final static String DB_PASSWORD = "generation_2022";
	private final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

	private Database() throws SQLException, ClassNotFoundException {
		Class.forName(DB_DRIVER);
		con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}


	public boolean insertStudente(Studente st) throws SQLException {
		String sql = "insert into studente"
				+ "(nome, cognome, genere, indirizzo, citta, provincia, regione, email, data_nascita) values"
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?);";

		PreparedStatement istruzione = this.con.prepareStatement(sql);

		istruzione.setString(1, st.getNome());
		istruzione.setString(2, st.getCognome());
		istruzione.setString(3, st.getGenere());
		istruzione.setString(4, st.getIndirizzo());
		istruzione.setString(5, st.getCitta());
		istruzione.setString(6, st.getProvincia());
		istruzione.setString(7, st.getRegione());
		istruzione.setString(8, st.getEmail());
		istruzione.setDate(9, st.getData_nascita());

		int numRow = istruzione.executeUpdate();

		return numRow == 1;
	}

	public boolean updateStudente(Studente st) throws SQLException {
		String sql = "update studente" + "set nome=?, cognome=?, genere=?, indirizzo=?, citta=?, "
				+ "provincia=?, regione=?, email=?, data_nascita=? " + "where id=?;";

		PreparedStatement istruzione = this.con.prepareStatement(sql);

		istruzione.setString(1, st.getNome());
		istruzione.setString(2, st.getCognome());
		istruzione.setString(3, st.getGenere());
		istruzione.setString(4, st.getIndirizzo());
		istruzione.setString(5, st.getCitta());
		istruzione.setString(6, st.getProvincia());
		istruzione.setString(7, st.getRegione());
		istruzione.setString(8, st.getEmail());
		istruzione.setDate(9, st.getData_nascita());

		istruzione.setInt(10, st.getId());

		int numRow = istruzione.executeUpdate();

		return numRow == 1;
	}

	public boolean deleteStudente(Studente st) throws SQLException {
		String sql = "delete from studente where id=?;";

		PreparedStatement istruzione = this.con.prepareStatement(sql);

		istruzione.setInt(1, st.getId());

		int numRow = istruzione.executeUpdate();

		return numRow == 1;
	}

	public Studente getStudentById(int id) throws SQLException {
		String sql = "select id, nome, cognome, genere, indirizzo, citta, provincia, regione, email, data_nascita, ins"
				+ "from studente where id=?;";

		PreparedStatement istruzione = this.con.prepareStatement(sql);
		istruzione.setInt(1, id);

		ResultSet rs = istruzione.executeQuery();
		while (rs.next()) {
			Studente rstu = new Studente();
			rstu.setId(rs.getInt("id"));
			rstu.setNome(rs.getString("nome"));
			rstu.setCognome(rs.getString("cognome"));
			rstu.setGenere(rs.getString("genere"));
			rstu.setIndirizzo(rs.getString("indirizzo"));
			rstu.setProvincia(rs.getString("provincia"));
			rstu.setRegione(rs.getString("regione"));
			rstu.setEmail(rs.getString("email"));
			rstu.setData_nascita(rs.getDate("data_nascita"));
			rstu.setIns(rs.getDate("ins"));
			return rstu;
		}

		return null;
	}

	public List<Studente> getAllStudent() throws SQLException {
		String sql = "select id, nome, cognome, genere, indirizzo, "
				+ "citta, provincia, regione, email, data_nascita, ins "
				+ "from studente;";
		
		PreparedStatement istruzione = this.con.prepareStatement(sql);

		ResultSet rs = istruzione.executeQuery();
		List<Studente> studenti = new ArrayList<Studente>();

		while (rs.next()) {
			Studente rstu = new Studente();
			rstu.setId(rs.getInt("id"));
			rstu.setNome(rs.getString("nome"));
			rstu.setCognome(rs.getString("cognome"));
			rstu.setGenere(rs.getString("genere"));
			rstu.setIndirizzo(rs.getString("indirizzo"));
			rstu.setProvincia(rs.getString("provincia"));
			rstu.setRegione(rs.getString("regione"));
			rstu.setEmail(rs.getString("email"));
			rstu.setData_nascita(rs.getDate("data_nascita"));
			rstu.setIns(rs.getDate("ins"));
			studenti.add(rstu);
		}

		return studenti;
	}
}
