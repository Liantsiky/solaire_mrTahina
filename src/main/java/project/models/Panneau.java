package project.models;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ligeneric.annotation.Column;
import ligeneric.annotation.Table;
import ligeneric.dao.DAO;

@Table(name = "panneau")
public class Panneau {
	
	@Column(name = "id")
	int id;
	
	@Column(name = "designation")
	String designation;
	
	@Column(name = "capacite")
	double capacite;
	
	/**
	 * prend la puissance genere par ce panneau a une date donnée
	 * @param connection
	 * @param panneau
	 * @param day
	 * @return
	 * @throws Exception
	 */
	public List<PanneauSoleil> getPanneauPower(Connection connection,LocalDate day) throws Exception {
		List <PanneauSoleil> resultat = new ArrayList <>();
		String date = day.toString();
		List <Soleil> soleils = DAO.findPredicat(Soleil.class, connection, "date_soleil= '"+date+"'");
		for (Soleil soleil : soleils) {
			PanneauSoleil pS = new PanneauSoleil();
			pS.setPanneau(this);
			String idCreneau = ""+soleil.getIdCreneau();
			pS.setDatePanneauSoleil(day);
			pS.setCreneau(DAO.findPredicat(Creneau.class, connection, "id=" + idCreneau).get(0));
			pS.setPowerGenerate(this.getPowerUse(soleil.getPuissance()));
			resultat.add(pS);
		}
		return resultat;
	}
	
	/**
	 * calcule la capacite procurer par le panneau selon le pourcentage donner
	 * @param pourcentage
	 * @return
	 */
	public double getPowerUse(double pourcentage) {
		return (this.getCapacite()*pourcentage)/100;
	}
	
	
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
