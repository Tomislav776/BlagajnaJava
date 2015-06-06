package dataClass;

public class Konobar {
	private int id;
	private String naziv;
	private int satnica;
	private String prezime;
	private String oib;
	
	public Konobar(int id, String naziv, int satnica, String prezime, String OIB){
		this.id=id;
		this.naziv=naziv;
		this.satnica=satnica;
		this.setPrezime(prezime);
		this.setOib(OIB);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getSatnica() {
		return satnica;
	}
	public void setSatnica(int satnica) {
		this.satnica = satnica;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getOib() {
		return oib;
	}


	public void setOib(String oib) {
		this.oib = oib;
	}

}
