package dataClass;

public class Konobar {
	private int id;
	private String naziv;
	private int satnica;
	
	public Konobar(int id, String naziv, int satnica){
		this.id=id;
		this.naziv=naziv;
		this.satnica=satnica;
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

}
