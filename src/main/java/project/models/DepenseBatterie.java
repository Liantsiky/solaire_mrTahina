package project.models;

import ligeneric.annotation.Column;
import ligeneric.annotation.Table;

@Table(name = "depense_batteire")
public class DepenseBatterie {
	
	@Column(name = "id")
	int id;
	
	@Column(name = "id_batterie")
	int idBatterie;
	
	Batterie batterie;
	
	@Column(name = "id_creneau")
	int idCreneau;
	
	Creneau creneau;
	
	@Column(name = "power_use")
	double powerUse;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdBatterie() {
		return idBatterie;
	}
	public void setIdBatterie(int idBatterie) {
		this.idBatterie = idBatterie;
	}
	
	public int getIdCreneau() {
		return idCreneau;
	}
	public void setIdCreneau(int idCreneau) {
		this.idCreneau = idCreneau;
	}
	
	public double getPowerUse() {
		return powerUse;
	}
	public void setPowerUse(double powerUse) {
		this.powerUse = powerUse;
	}
	
	public Batterie getBatterie() {
		return batterie;
	}
	public void setBatterie(Batterie batterie) {
		this.batterie = batterie;
	}
	
	public Creneau getCreneau() {
		return creneau;
	}
	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}
	

}
