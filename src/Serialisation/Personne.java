package serialisation;

import javafx.beans.property.SimpleStringProperty;

public class Personne 
{
	private SimpleStringProperty Magazin;
    private SimpleStringProperty Annee;
	private SimpleStringProperty Ca;
	private SimpleStringProperty Mois;

    public Personne() {
    	this.Magazin=new SimpleStringProperty();
    	this.Annee=new SimpleStringProperty();
    	this.Ca=new SimpleStringProperty();
    	this.Mois=new SimpleStringProperty();
    }
    

public Personne(String magazin, String annee, String ca,String mois) {
  	this.Magazin=new SimpleStringProperty(magazin);
	this.Annee=new SimpleStringProperty(annee);
	this.Ca=new SimpleStringProperty(ca);
	this.Mois=new SimpleStringProperty(mois);
	}

public String getMois() {
	return this.Mois.get();
};

public void  setMois(String Mois) {
	this.Mois.set(Mois);
};
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