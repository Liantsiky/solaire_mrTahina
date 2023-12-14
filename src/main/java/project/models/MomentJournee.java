package project.models;

import java.time.LocalTime;

import ligeneric.annotation.Column;
import ligeneric.annotation.Table;

@Table(name = "moment_journee")
public class MomentJournee {
	
	@Column(name = "id")
	int id;
	
	@Column(name = "designation")
	String designation;
	
	@Column(name = "heure_debut")
	LocalTime heureDebut;
	
	@Column(name = "heure_fin")
	LocalTime heureFin;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
