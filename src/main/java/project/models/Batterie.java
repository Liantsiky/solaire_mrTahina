package project.models;

import ligeneric.annotation.Column;
import ligeneric.annotation.Table;

@Table(name = "batterie")
public class Batterie {
	
	@Column(name = "id")
	int id;
	
	@Column(name = "designation")
	String designation;
	
	@Column(name = "capacite")
	double capacite;
	
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
	
	public double getCapacite() {
		return capacite;
	}
	public void setCapacite(double capacite) {
		this.capacite = capacite;
	}
}
