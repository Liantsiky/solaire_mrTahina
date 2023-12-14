package project.models;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class Coupure {
	
	Systeme systeme;
	
	LocalTime heureCoupure;
	
	LocalDate dateCoupure;
	
	List<DetailsCoupure> detailsCoupures;
	
	public Coupure calulCoupure(LocalDate datePrevision,double puissanceMoyenne,Connection connection) throws Exception  {
		Coupure resultat = new Coupure();
		List<PanneauSoleil> depensesPanneau = this.getSysteme().getPanneau().getPanneauPower(connection, datePrevision);
		double resteBatterie = this.getSysteme().getBatterie().getCapacite();
		int dayOfWeek = datePrevision.getDayOfWeek().getValue();
		LocalTime tapaka = depensesPanneau.get(0).getCreneau().getHeureDebut();
		for(PanneauSoleil depensePanneau : depensesPanneau) {
			SystemeMoyenne systemeMoyenne = this.getSysteme().getMoyenneDowCreneau(this.getSysteme().getId(), dayOfWeek, depensePanneau.getCreneau().getHeureDebut(), connection).get(0);
			int nombreMpianatra = systemeMoyenne.getMoyenne();
			//puissance utilise par la totalite des utilisateurs par heures
			double powerUse = puissanceMoyenne*nombreMpianatra ;
			//puissance restante a tirer de la batterie si les panneaux ne sont pas suffisant
			double powerRest = depensePanneau.getPowerGenerate() - powerUse;
			if(powerRest<0) {
				resteBatterie = resteBatterie + powerRest;
			}
			if((this.getSysteme().getBatterie().getCapacite())/2 > resteBatterie) {
				//tsy miakatra a l'heure intsony fa tadiavina ny ambiny a la minute
				double resteAcalculer = (this.getSysteme().getBatterie().getCapacite())/2 - resteBatterie;
				int minuteAjout = (int)((resteAcalculer*60)/powerUse);
				tapaka = tapaka.plusMinutes(minuteAjout);
			}else {
				tapaka = tapaka.plusHours(1);
			}	
		}
		return resultat;
	}
	public Systeme getSysteme() {
		return systeme;
	}
	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}
	
	public LocalTime getHeureCoupure() {
		return heureCoupure;
	}
	public void setHeureCoupure(LocalTime heureCoupure) {
		this.heureCoupure = heureCoupure;
	}
	
	public LocalDate getDateCoupure() {
		return dateCoupure;
	}
	public void setDateCoupure(LocalDate dateCoupure) {
		this.dateCoupure = dateCoupure;
	}
	
	public List<DetailsCoupure> getDetailsCoupures() {
		return detailsCoupures;
	}
	public void setDetailsCoupures(List<DetailsCoupure> detailsCoupures) {
		this.detailsCoupures = detailsCoupures;
	}
}
