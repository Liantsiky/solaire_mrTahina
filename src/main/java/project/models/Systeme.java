package project.models;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ligeneric.annotation.Column;
import ligeneric.annotation.Table;
import ligeneric.dao.DAO;

@Table(name = "systeme")
public class Systeme {
	
	@Column(name = "id")
	int id;
	
	@Column(name = "id_panneau")
	int idPanneau;
	
	Panneau panneau;
	
	@Column(name = "id_batterie")
	int idBatterie;
	
	Batterie batterie;
	public Coupure calulCoupure(LocalDate datePrevision,double puissanceMoyenne,Connection connection) throws Exception  {
		Coupure resultat = new Coupure();
		List<DetailsCoupure> detailsCoupures = new ArrayList<DetailsCoupure>();
		List<PanneauSoleil> depensesPanneau = this.getPanneau().getPanneauPower(connection, datePrevision);
		double resteBatterie = this.getBatterie().getCapacite();
		int dayOfWeek = datePrevision.getDayOfWeek().getValue();
		LocalTime tapaka = depensesPanneau.get(0).getCreneau().getHeureDebut();
		int i =1;
		for(PanneauSoleil depensePanneau : depensesPanneau) {
			System.out.println("_____________________________________");
			System.out.println("Boucle "+ i);
			SystemeMoyenne systemeMoyenne = this.getMoyenneDowCreneau(this.getId(), dayOfWeek, depensePanneau.getCreneau().getHeureDebut(), connection).get(0);
			int nombreMpianatra = systemeMoyenne.getMoyenne();
			//puissance utilise par la totalite des utilisateurs par heures
			System.out.println("mpianatra:" + nombreMpianatra);
			double powerUse = puissanceMoyenne*nombreMpianatra ;
			System.out.println(powerUse);
			//puissance restante a tirer de la batterie si les panneaux ne sont pas suffisant
			double powerRest = depensePanneau.getPowerGenerate() - powerUse;
			System.out.println("ambony panneau: "+ powerRest);
			if(powerRest<0) {
				resteBatterie = resteBatterie + powerRest;
				System.out.println("ambony batterie: " + resteBatterie);
			}
			detailsCoupures.add(new DetailsCoupure(tapaka,depensePanneau.getCreneau(),powerUse,depensePanneau.getPowerGenerate(),resteBatterie));
			if((this.getBatterie().getCapacite())/2 > resteBatterie) {
				//tsy miakatra a l'heure intsony fa tadiavina ny ambiny a la minute
				double resteAcalculer = (this.getBatterie().getCapacite())/2 - resteBatterie;
				int minuteAjout = (int)((resteAcalculer*60)/powerUse);
				tapaka = tapaka.plusHours(1);
				tapaka = tapaka.minusMinutes(minuteAjout);
//;				tapaka = tapaka.plusMinutes(minuteAjout);
				System.out.println("lera: " + tapaka.toString());
				break;
			}else {
				tapaka = tapaka.plusHours(1);
				System.out.println("tsy tapaka : " + tapaka.toString());
			}	
			i++;
		}
		resultat.setDateCoupure(datePrevision);
		resultat.setHeureCoupure(tapaka);
		resultat.setSysteme(this);
		resultat.setDetailsCoupures(detailsCoupures);
		return resultat;
	}
	
	public void setBatteriePanneau(Connection connection) throws Exception {
		this.setBatterie(DAO.findPredicat(Batterie.class, connection, "id=" + this.getIdBatterie()).get(0));
		this.setPanneau(DAO.findPredicat(Panneau.class, connection, "id="+this.getIdPanneau()).get(0));
	}
	/**
	 * getThe liste of moyenne for the a.m and p.m of this systeme for the day on day of a week
	 * @param dayOfWeek
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	public List<SystemeMoyenne> getMoyenneDowCreneau(int idSysteme,int dayOfWeek,LocalTime heure,Connection connection) throws Exception
	{
		String function = "f_getMoyenneparJour(" + dayOfWeek+")";
		String predicat = "id_systeme=" + this.getId()+" and heure_debut<='" + heure.toString() + "' and heure_fin>'" + heure.toString()+"'";
		List<SystemeMoyenne> resultat = DAO.findPredicatView(SystemeMoyenne.class,function, connection, predicat);
		
		return resultat;
	}
	
	/**
	 * get The List of al classe relied to this systeme
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	public List<Classe> getClasses(Connection connection) throws Exception {
		List<Classe> resultat = DAO.findPredicat(Classe.class, connection, "id_systeme =" + this.getId());
		return resultat;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdPanneau() {
		return idPanneau;
	}
	public void setIdPanneau(int idPanneau) {
		this.idPanneau = idPanneau;
	}
	
	public int getIdBatterie() {
		return idBatterie;
	}
	public void setIdBatterie(int idBatterie) {
		this.idBatterie = idBatterie;
	}
	
	public Panneau getPanneau() {
		return panneau;
	}
	public void setPanneau(Panneau panneau) {
		this.panneau = panneau;
	}
	
	public Batterie getBatterie() {
		return batterie;
	}
	public void setBatterie(Batterie batterie) {
		this.batterie = batterie;
	}
}
