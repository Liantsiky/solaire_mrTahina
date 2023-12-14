package project.models;

import java.time.LocalTime;

import ligeneric.annotation.Column;
import ligeneric.annotation.Table;

@Table(name = "creneau")
public class Creneau {

	@Column(name = "id")
	int id;
	
	@Column(name = "date_debut")
	LocalTime heureDebut;
	
	@Column(name = "date_fin")
	LocalTime heureFin;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
