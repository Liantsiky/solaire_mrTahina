/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ligeneric.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liantsiky
 */
public class DAO {
	
    public static void insert(Object toInsert, Connection connexion) throws Exception {
    	String request = DaoHelper.getInsertRequest(toInsert);
    	Statement stmt = connexion.createStatement();
    	stmt.execute(request);
    };
    
    /**
     * select all the items in database
     * @param <T>
     * @param classe
     * @param connexion
     * @return
     * @throws Exception
     */
    public static <T> List<T> findall(Class<T> classe, Connection connexion) throws Exception {
    	List <T> liste = new ArrayList<>();
    	String request = DaoHelper.getSelectAllRequest(classe);
    	Statement stmt = connexion.createStatement();
    	ResultSet resultset = stmt.executeQuery(request);
    	while(resultset.next()) {
    		T element = DaoHelper.setObject(classe, resultset);
    		liste.add(element);
    	}
    	return liste;
    }
    
    /**
     * select all the items who correspond with the predicat
     * don't have to put the "where" syntax
     * @param <T>
     * @param Class<T> classe
     * @param connexion
     * @param predicat
     * @return
     * @throws Exception
     */
    public static <T> List <T> findPredicat(Class<T> classe, Connection connexion,String predicat) throws Exception{
    	List <T> liste = new ArrayList<T>();
    	String request = DaoHelper.getSelectPredicatRequest(classe, predicat);
    	Statement stmt = connexion.createStatement();
    	ResultSet resultset = stmt.executeQuery(request);
    	while(resultset.next()) {
    		T element = DaoHelper.setObject(classe, resultset);
    		liste.add(element);
    	}
    	return liste;
    }
    

    /**
     * select all the items in database from a view or a function
     * @param <T>
     * @param classe
     * @param connexion
     * @return
     * @throws Exception
     */
    public static <T> List<T> findallView(Class<T> classe, String view,Connection connexion) throws Exception {
    	List <T> liste = new ArrayList<>();
    	String request = DaoHelper.getSelectAllRequestView(classe,view);
    	Statement stmt = connexion.createStatement();
    	ResultSet resultset = stmt.executeQuery(request);
    	while(resultset.next()) {
    		T element = DaoHelper.setObject(classe, resultset);
    		liste.add(element);
    	}
    	return liste;
    }
    
    /**
     * select all the items from a view or function who correspond with the predicat
     * don't have to put the "where" syntax
     * @param <T>
     * @param Class<T> classe
     * @param connexion
     * @param predicat
     * @return
     * @throws Exception
     */
    public static <T> List <T> findPredicatView(Class<T> classe,String view, Connection connexion,String predicat) throws Exception{
    	List <T> liste = new ArrayList<T>();
    	String request = DaoHelper.getSelectPredicatRequestView(classe,view, predicat);
    	Statement stmt = connexion.createStatement();
    	ResultSet resultset = stmt.executeQuery(request);
    	while(resultset.next()) {
    		T element = DaoHelper.setObject(classe, resultset);
    		liste.add(element);
    	}
    	return liste;
    }
//    public void update();
//    public findPredicat();
//    public delete();
}
