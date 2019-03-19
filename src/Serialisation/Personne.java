package serialisation;

import javafx.beans.property.SimpleStringProperty;

public class Personne 
{
	private SimpleStringProperty Magazin;
    private SimpleStringProperty Annee;
	private SimpleStringProperty Ca;

    public Personne() {
    	this.Magazin=new SimpleStringProperty();
    	this.Annee=new SimpleStringProperty();
    	this.Ca=new SimpleStringProperty();}
    

public Personne(String magazin, String annee, String ca) {
  	this.Magazin=new SimpleStringProperty(magazin);
	this.Annee=new SimpleStringProperty(annee);
	this.Ca=new SimpleStringProperty(ca);
	}


public String getMagazin() {
		return Magazin.get();
	}
	public void setMagazin(String Magazin) {
		this.Magazin.set(Magazin);
	}
	public String getAnnee() {
		return Annee.get();
	}
	public void setAnnee(String ANNEE) {
		this.Annee.set(ANNEE);
	}
	public String getCa() {
		return Ca.get();
	}
	public void setCa(String Magazin) {
		this.Ca.set(Magazin);
	}


}