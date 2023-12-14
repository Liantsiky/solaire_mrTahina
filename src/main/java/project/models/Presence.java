package project.models;


import java.time.LocalDate;

import ligeneric.annotation.Column;
import ligeneric.annotation.Table;

@Table(name = "presence")
public class Presence {
	
	@Column(name = "id")
	int id;
	
	@Column(name = "id_classe")
	int idClasse;
	
	Classe classe;
	
	@Column(name = "nombre")
	int nombre;
	
	@Column(name = "date_presence")
	LocalDate datePresence;
	
	@Column(name = "id_moment_journee")
	int idMomentJournee;
	
	double depenseTotal;
	
	MomentJournee momentJournee;
	
	/**
	 * calcul la puissance total depense par une classe selon le nombre de watt
	 * @param depensePareleve
	 * @return
	 */
	public void setPuissance(double depensePareleve) {
		setDepenseTotal(depensePareleve* this.getNombre()); 
	};
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdClasse() {
		return idClasse;
	}
	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}
	
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	
	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	
	public LocalDate getDatePresence() {
		return datePresence;
	}
	public void setDatePresence(LocalDate datePresence) {
		this.datePresence = datePresence;
	}
	
	public int getIdMomentJournee() {
		return idMomentJournee;
	}
	public void setIdMomentJournee(int idMomentJournee) {
		this.idMomentJournee = idMomentJournee;
	}
	
	public double getDepenseTotal() {
		return depenseTotal;
	}
	public void setDepenseTotal(double depenseTotal) {
		this.depenseTotal = depenseTotal;
	}
	
	public MomentJournee getMomentJournee() {
		return momentJournee;
	}
	public void setMomentJournee(MomentJournee momentJournee) {
		this.momentJournee = momentJournee;
	}
}
