package project.models;

import java.time.LocalTime;

public class DetailsCoupure {
	LocalTime heure;
	
	Creneau creneau;
	
	double powerUse;
	
	double panneaupourvoir;
	
	double batterieReste;
	
	public DetailsCoupure(LocalTime heure,Creneau creneau,double powerUse,double panneauPourvoir,double batterieReste) {
		this.setHeure(heure);
		this.setCreneau(creneau);
		this.setPowerUse(powerUse);
		this.setPanneaupourvoir(panneauPourvoir);
		this.setBatterieReste(batterieReste);
	}
	
	public DetailsCoupure() {
		
	}
	
	public LocalTime getHeure() {
		return heure;
	}
	public void setHeure(LocalTime heure) {
		this.heure = heure;
	}
	
	public Creneau getCreneau() {
		return creneau;
	}
	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}
	
	public double getPowerUse() {
		return powerUse;
	}
	public void setPowerUse(double powerUse) {
		this.powerUse = powerUse;
	}
	
	public double getPanneaupourvoir() {
		return panneaupourvoir;
	}
	public void setPanneaupourvoir(double panneaupourvoir) {
		this.panneaupourvoir = panneaupourvoir;
	}
	
	public double getBatterieReste() {
		return batterieReste;
	}
	public void setBatterieReste(double batterieReste) {
		this.batterieReste = batterieReste;
	}
	
}
