package project.models;

import java.time.LocalDateTime;

public class CoupurePrediction {
	Systeme systeme;
	
	LocalDateTime dateHeureCoupure;
	
	public Systeme getSysteme() {
		return systeme;
	}
	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}
	
	public LocalDateTime getDateHeureCoupure() {
		return dateHeureCoupure;
	}
	public void setDateHeureCoupure(LocalDateTime dateHeureCoupure) {
		this.dateHeureCoupure = dateHeureCoupure;
	}
}
