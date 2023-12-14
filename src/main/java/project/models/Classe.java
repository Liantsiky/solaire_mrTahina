package project.models;

import ligeneric.annotation.Column;
import ligeneric.annotation.Table;

@Table(name = "classe")
public class Classe {
	
	@Column(name = "id")
	int id;
	
	@Column(name = "designation")
	String designation;
	
	@Column(name = "id_systeme")
	int idSysteme;
	
	Systeme systeme;
	
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
	
	public int getIdSysteme() {
		return idSysteme;
	}
	public void setIdSysteme(int idSysteme) {
		this.idSysteme = idSysteme;
	}
	
	public Systeme getSysteme() {
		return systeme;
	}
	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}
}
