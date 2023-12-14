/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ligeneric.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import ligeneric.annotation.Column;
import ligeneric.annotation.Table;

/**
 *
 * @author liantsiky
 */

public class DaoHelper {
	
	 public static final HashMap <Class <?>, Function<String,?>> PARSING = new HashMap<>();
	    static{
	        PARSING.put(int.class, Integer::parseInt);
	        PARSING.put(double.class, Double :: parseDouble);
	        PARSING.put(String.class, Function.identity());
	        PARSING.put(LocalDate.class, LocalDate::parse);
	        PARSING.put(LocalDateTime.class, LocalDateTime::parse);
	        PARSING.put(LocalTime.class, LocalTime::parse);
	    }
    
	/**
	 * Get the fields of a class with the corresponding name columns in the database
	 * @param Class<T> classe
	 * @return Map<Field,String> 
	 * @throws Exception
	 */
    public static <T> Map<Field,String> getFieldsColumns(Class<T> classe) throws Exception {
        Map <Field,String> fieldscolumns = new HashMap<>();
        Field[] fields = classe.getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(Column.class)){
                fieldscolumns.put(field,field.getAnnotation(Column.class).name());
            }
        }
        return  fieldscolumns;
    }
    
    /**
     * Get the fields of a class with the corresponding getters 
     * @param Class <T> classe
     * @return Map<Field,Method>
     * @throws Exception
     */
    public static <T> Map<Field,Method> getFieldsGetters(Class <T> classe) throws Exception{
    	Map <Field,String> fieldsColumns = DaoHelper.getFieldsColumns(classe);
    	Map <Field,Method> fieldsGetters = new HashMap<Field, Method>();
    	for (Field field: fieldsColumns.keySet()) {
    		String fieldName = field.getName();
    		String fieldGetter = "get"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    		Method method = classe.getMethod(fieldGetter);
    		fieldsGetters.put(field, method);
    	}
    	return fieldsGetters;
    }
    
    public static String getSetterName(Field field) {
    	String fieldName = field.getName();
    	return 	"set"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
    /**
     * Get the method name of the setters
     * @param <T>
     * @param classe
     * @return
     * @throws java.lang.Exception
     */
    public static <T> Map<Field,String> getFieldsSettersName(Class <T> classe) throws Exception{
    	Map <Field,String> fieldsColumns = DaoHelper.getFieldsColumns(classe);
    	Map <Field,String> fieldsSetters = new HashMap<Field, String>();
    	for (Field field: fieldsColumns.keySet()) {
    		String fieldSetter = DaoHelper.getSetterName(field);
    		fieldsSetters.put(field, fieldSetter);
    	}
    	return fieldsSetters;
    }
    /**
     * Get the fields of a class with the corresponding setters 
     * @param Class <T> classe
     * @return Map<Field,Method>
     * @throws Exception
     */
    public static <T> Map<Field,Method> getFieldsSetters(Class <T> classe) throws Exception{
    	Map <Field,String> fieldsColumns = DaoHelper.getFieldsColumns(classe);
    	Map <Field,Method> fieldsSetters = new HashMap<Field, Method>();
    	Method [] methods = classe.getMethods();
    	for (Field field: fieldsColumns.keySet()) {
    		String fieldSetter = DaoHelper.getSetterName(field);
    		for(Method method : methods) {
    			if(method.getName().equals(fieldSetter) ) {
    				fieldsSetters.put(field, method);
    			}
    		}
    	}
    	return fieldsSetters;
    }
    /**
     * Get a long string of all the columns of the object
     * @param columns
     * @return
     * @throws Exception
     */
    public static String getColumnsString (Map <Field,String> columns) throws Exception{
    	
    	int size = columns.size();		
    	int counter = 1;
    	String column = "(";
    	for (Field field : columns.keySet()) {
    		column = column + columns.get(field);
    		if(counter == size) {
    			column = column + ")";
    		} else {
    			column = column + ",";
    		}
    		counter++;
    	}
    	return column;
    }
    
    /**
     * get the String request of a select All with the tableName
     * @param <T>
     * @param classe
     * @return
     */
    public static <T> String getSelectAllRequest(Class<T> classe)  {
    	return "select * from " + classe.getAnnotation(Table.class).name();
    }
    
    /**
     * get the String request of a select All with a view or a function
     * @param <T>
     * @param classe
     * @return
     */
    public static <T> String getSelectAllRequestView(Class<T> classe,String view)  {
    	return "select * from " + view;
    }
    
    /**
     * get the String request of a select with a predicat 
     * @param <T>
     * @param classe
     * @return
     */
    public static <T> String getSelectPredicatRequest(Class <T> classe,String predicat) {
    	return DaoHelper.getSelectAllRequest(classe) + " where " + predicat;
    }
    

    /**
     * get the String request of a select from a view or function with a predicat 
     * @param <T>
     * @param classe
     * @return
     */
    public static <T> String getSelectPredicatRequestView(Class <T> classe,String view,String predicat) {
    	return DaoHelper.getSelectAllRequestView(classe,view) + " where " + predicat;
    }
    
    
    /**
     * Get the String of an insert request
     * @param toInsert
     * @return
     */
	public static String getInsertRequest(Object toInsert) throws Exception {
		String table = toInsert.getClass().getAnnotation(Table.class).name();
		Map <Field,String> fieldsColumns = DaoHelper.getFieldsColumns(toInsert.getClass());
		Map <Field,Method> fieldsGetters = DaoHelper.getFieldsGetters(toInsert.getClass());
		String columns = DaoHelper.getColumnsString(fieldsColumns);
		int size = fieldsGetters.size()	;
		int counter = 1;
		
		String request = "insert into "+ table + " "+ columns + "values (";
		for (Field field : fieldsGetters.keySet()) {
			if(field.getGenericType() == String.class || field.getGenericType() == LocalTime.class) {
				request = request + "'"+ String.valueOf(fieldsGetters.get(field).invoke(toInsert))+"'";
			} else if (field.getType() == Date.class) {
				request = request + "TO_DATE ('"+ String.valueOf(fieldsGetters.get(field).invoke(toInsert))+"','YYYY-MM-DD')";
			}else {
				request = request + String.valueOf(fieldsGetters.get(field).invoke(toInsert));
			}
			
			if(counter == size) {
				request = request + ")";
			} else {
				request = request + ",";
			}
			counter++;
		}
		return request;
	}
	
	/**
	 * set the object that we are gonna list
	 * @param <T>
	 * @param classe
	 * @param resultset
	 * @return
	 * @throws Exception
	 */
	public static <T> T setObject (Class<T> classe,ResultSet resultset) throws Exception {
		T result = classe.newInstance();
		Map <Field,String> fieldsColumns = DaoHelper.getFieldsColumns(classe);
		Map <Field,Method> fieldsSetters = DaoHelper.getFieldsSetters(classe);
		
		for(Field field : fieldsColumns.keySet()) {
			String value = resultset.getString(fieldsColumns.get(field));
			Function <String,?> parser = PARSING.get(field.getGenericType());
			Object fieldValue = parser.apply(value);
			fieldsSetters.get(field).invoke(result,fieldValue);
		}	
		
		return result;
	}
}

	





