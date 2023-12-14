package project.models;


import java.time.LocalTime;

import ligeneric.annotation.Column;

public class SystemeMoyenne {
	
	@Column(name = "id_systeme")
	int idSysteme;
	
	@Column(name = "moyenne")
	int moyenne;
	
	@Column(name = "id_moment_journee")
	int idMomentJournee;
	
	@Column(name = "heure_debut")
	LocalTime heureDebut;
	
	@Column(name = "heure_fin")
	LocalTime heureFin;
	
	public int getIdSysteme() {
		return idSysteme;
	}
	public void setIdSysteme(int idSysteme) {
		this.idSysteme = idSysteme;
	}
	
	public int getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(int moyenne) {
		this.moyenne = moyenne;
	}
	
	public int getIdMomentJournee() {
		return idMomentJournee;
	}
	public void setIdMomentJournee(int idMomentJournee) {
		this.idMomentJournee = idMomentJournee;
	}
	
	public LocalTime getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}
	
	public LocalTime getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(LocalTime heureFin) {
		this.heureFin = heureFin;
	}
	
}
