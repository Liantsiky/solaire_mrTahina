package project.models;

import java.time.LocalDate;

import ligeneric.annotation.Column;
import ligeneric.annotation.Table;

@Table(name = "soleil")
public class Soleil {	
	
	@Column(name = "id")
	int id;
	
	@Column(name = "id_creneau")
	int idCreneau;
	
	Creneau creneau;
	
	@Column(name = "puissance")
	double puissance;
	
	@Column(name = "date_soleil")
	LocalDate dateSoleil;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdCreneau() {
		return idCreneau;
	}
	public void setIdCreneau(int idCreneau) {
		this.idCreneau = idCreneau;
	}
	
	public Creneau getCreneau() {
		return creneau;
	}
	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}
	
	public double getPuissance() {
		return puissance;
	}
	public void setPuissance(double puissance) {
		this.puissance = puissance;
	}
	
	public LocalDate getDateSoleil() {
		return dateSoleil;
	}
	public void setDateSoleil(LocalDate dateSoleil) {
		this.dateSoleil = dateSoleil;
	}
	
}
