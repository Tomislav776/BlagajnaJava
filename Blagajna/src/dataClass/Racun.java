package dataClass;

public class Racun {
	
	private int id;
	private double iznos;
	private String nazivLokala;
	private String konobar;
	private String datum;
	
	public Racun (int id, double iznos, String nazivLokala, String konobar, String datum){
		this.id=id;
		this.iznos=iznos;
		this.datum = datum;
		this.nazivLokala=nazivLokala;
		this.konobar=konobar;
	}
	

	public String getNazivLokala() {
		return nazivLokala;
	}

	public void setNazivLokala(String nazivLokala) {
		this.nazivLokala = nazivLokala;
	}
	
	public String getDatum() {
		return datum;
	}

	public void setdatum(String datum) {
		this.datum = datum;
	}


	public String getKonobar() {
		return konobar;
	}

	public void setKonobar(String konobar) {
		this.konobar = konobar;
	}
	
	
	public double getIznos() {
		return iznos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

}
