package dataClass;

public class Artikli {
	private int id;
	private String naziv;
	private int kolicina;
	
	public Artikli (int id, String naziv, int kolicina){
		this.naziv=naziv;
		this.kolicina=kolicina;
		this.id=id;
		
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
