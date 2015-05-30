package dataClass;

public class Artikli {
	private int id;
	private String naziv;
	private int kolicina;
	private double cijena;
	
	public Artikli (){
	}
	
	public Artikli (int id, String naziv, int kolicina, double cijena){
		this.naziv = naziv;
		this.kolicina = kolicina;
		this.cijena = cijena;
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
	
	
	public double getCijena() {
		return cijena;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

	
}
