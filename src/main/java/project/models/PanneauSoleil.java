package project.models;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ligeneric.dao.DAO;

public class PanneauSoleil {
	Panneau panneau;
	Creneau creneau;
	double powerGenerate;
	LocalDate datePanneauSoleil;
	
	/**
	 * prend la puissance genere par un panneau donné a une date donnée
	 * @param connection
	 * @param panneau
	 * @param day
	 * @return
	 * @throws Exception
	 */
	public List<PanneauSoleil> getPanneauPower(Connection connection, Panneau panneau,LocalDate day) throws Exception {
		List <PanneauSoleil> resultat = new ArrayList <>();
		String date = day.toString();
		List <Soleil> soleils = DAO.findPredicat(Soleil.class, connection, "date_soleil= '"+date+"'");
		for (Soleil soleil : soleils) {
			PanneauSoleil pS = new PanneauSoleil();
			pS.setPanneau(panneau);
			String idCreneau = ""+soleil.getIdCreneau();
			pS.setCreneau(DAO.findPredicat(Creneau.class, connection, "id=" + idCreneau).get(0));
			pS.setPowerGenerate(this.getPanneau().getPowerUse(soleil.getPuissance()));
			resultat.add(pS);
		}
		return resultat;
	}
	
	public Panneau getPanneau() {
		return panneau;
	}
	public void setPanneau(Panneau panneau) {
		this.panneau = panneau;
	}
	
	public Creneau getCreneau() {
		return creneau;
	}
	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}
	
	public double getPowerGenerate() {
		return powerGenerate;
	}
	public void setPowerGenerate(double powerGenerate) {
		this.powerGenerate = powerGenerate;
	}
	
	public LocalDate getDatePanneauSoleil() {
		return datePanneauSoleil;
	}
	public void setDatePanneauSoleil(LocalDate datePanneauSoleil) {
		this.datePanneauSoleil = datePanneauSoleil;
	}
}
