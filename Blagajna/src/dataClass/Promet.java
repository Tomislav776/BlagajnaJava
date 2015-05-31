package dataClass;

public class Promet {

	private double iznos_prometa;
	private String razdoblje;
	
	public Promet(double iznos_prometa, String razdoblje)
	{
		this.setIznos_prometa(iznos_prometa);
		this.setRazdoblje(razdoblje);
	}

	public double getIznos_prometa() {
		return iznos_prometa;
	}

	public void setIznos_prometa(double iznos_prometa) {
		this.iznos_prometa = iznos_prometa;
	}

	public String getRazdoblje() {
		return razdoblje;
	}

	public void setRazdoblje(String razdoblje) {
		this.razdoblje = razdoblje;
	}
	
	
	
}
