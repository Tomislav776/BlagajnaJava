package dataClass;

public class Artikli {
	private int id;
	private String naziv;
	private int kolicina;
	private double cijena;
	private double ukupno;
	
	public Artikli (){
	}
	
	public Artikli (int id, String naziv, int kolicina, double cijena){
		this.naziv = naziv;
		this.kolicina = kolicina;
		this.cijena = cijena;
		this.id=id;
		this.ukupno=0;
	}
	
	public Artikli (int id, String naziv, int kolicina, double cijena, double ukupno){
		this.naziv = naziv;
		this.kolicina = kolicina;
		this.cijena = cijena;
		this.id=id;
		this.ukupno=ukupno;
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
	
	public double getCijena() {
		return cijena;
	}
	
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	
	public double getUkupno() {
		return ukupno;
	}
	
	public void setUkupno(double ukupno) {
		this.ukupno = ukupno;
	}

	
}
