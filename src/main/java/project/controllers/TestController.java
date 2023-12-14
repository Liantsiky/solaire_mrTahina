package project.controllers;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import etu2050.framework.Modelview;
import etu2050.framework.annotations.Url;
import ligeneric.dac.DAC;
import ligeneric.dao.DAO;
import project.models.Coupure;
import project.models.Panneau;
import project.models.PanneauSoleil;
import project.models.Systeme;

public class TestController {

	@Url(lien = "panneausoleils.do",args = false)
    public Modelview getForOnePanneau(){
    	
        HashMap<String,Object> o= new HashMap<>();
        Modelview result = new Modelview(o);
      
        Panneau panneau = null;
        List<PanneauSoleil> depenses = null;
        try {
    		Connection connexion = DAC.getConnectionPostgreSQL("liantsiky","Mdpprom15", "solaire");
    		panneau = DAO.findPredicat(Panneau.class, connexion, "id=1").get(0);
    		depenses = panneau.getPanneauPower(connexion, LocalDate.parse("2023-12-04"));
    		
    		connexion.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//        o.put("depense", depenses);
        result.addItem("depense", depenses);
        // result.addItem("test2", nombre);
        result.setPageJsp("PanneauSoleil.jsp");
        
        return result;
    }
	@Url(lien = "prevision.do",args = false)
    public Modelview prevision(){
    	
        HashMap<String,Object> o= new HashMap<>();
        Modelview result = new Modelview(o);
      
        Systeme systeme = null;
        Coupure coupure = null;
        try {
    		Connection connexion = DAC.getConnectionPostgreSQL("liantsiky","Mdpprom15", "solaire");
    		systeme = DAO.findPredicat(Systeme.class, connexion, "id=1").get(0);
    		systeme.setBatteriePanneau(connexion);
    		String date = "2023-12-18";
    		coupure = systeme.calulCoupure(LocalDate.parse(date), 60, connexion);
    		
    		connexion.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        result.addItem("coupure", coupure);
        
        result.setPageJsp("Coupure.jsp");
        
        return result;
    }
}
