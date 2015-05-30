package dataClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Racun {
	
	private int id;
	private double iznos;
	private String nazivLokala;
	private String konobar;
	private String datum;
	
	public Racun (int id, double iznos, String nazivLokala, String konobar, String datum){
		this.id=id;
		this.iznos=iznos;
		this.nazivLokala=nazivLokala;
		this.konobar=konobar;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    	LocalDateTime trenutnoVrijeme = LocalDateTime.now(ZoneId.of("Europe/Zagreb"));
    	String formatiranoVrijeme = trenutnoVrijeme.format(format);
    	this.datum = formatiranoVrijeme;
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
