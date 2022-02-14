package model;

public class AutoreLibro {
	private int alAutoreId;
	private int alLibroID;
	private String aCognome, lTitolo;
	private double lPrezzo;
	
	public AutoreLibro() {
		// TODO Auto-generated constructor stub
	}
	
	public AutoreLibro(int alAutoreId, int alLibroID, String aCognome, String lTitolo, double lPrezzo) {
		super();
		this.alAutoreId = alAutoreId;
		this.alLibroID = alLibroID;
		this.aCognome = aCognome;
		this.lTitolo = lTitolo;
		this.lPrezzo = lPrezzo;
	}

	public int getAlAutoreId() {
		return alAutoreId;
	}

	public void setAlAutoreId(int alAutoreId) {
		this.alAutoreId = alAutoreId;
	}

	public int getAlLibroID() {
		return alLibroID;
	}

	public void setAlLibroID(int alLibroID) {
		this.alLibroID = alLibroID;
	}

	public String getaCognome() {
		return aCognome;
	}

	public void setaCognome(String aCognome) {
		this.aCognome = aCognome;
	}

	public String getlTitolo() {
		return lTitolo;
	}

	public void setlTitolo(String lTitolo) {
		this.lTitolo = lTitolo;
	}

	public double getlPrezzo() {
		return lPrezzo;
	}

	public void setlPrezzo(double lPrezzo) {
		this.lPrezzo = lPrezzo;
	}
	
	
	
}
