package dataClass;

public class Artikli {
	private String naziv;
	private int kolicina;
	
	public Artikli (String naziv, int kolicina){
		this.naziv=naziv;
		this.kolicina=kolicina;
		
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

	
}
