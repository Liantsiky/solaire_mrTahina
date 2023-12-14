<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="project.models.*"%>
<%@page import="java.util.*"%>
<%@page import="java.time.*"%>
<% List <PanneauSoleil> liste = (List<PanneauSoleil>) request.getAttribute("depense"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Panneau : <% out.println(liste.get(0).getPanneau().getDesignation()); %>
	Date : <% out.println(liste.get(0).getDatePanneauSoleil().toString()); %>
	<table border = "1">
	<tr>
		<th>Heure debut</th>
		<th>Heure fin</th> 
		<th>Puissance generée</th>
	</tr>
	<% for (PanneauSoleil panneau : liste) { %>
		<tr>
			<td> <% out.println(panneau.getCreneau().getHeureDebut().toString()); %> </td>
			<td> <% out.println(panneau.getCreneau().getHeureFin().toString()); %> </td>
			<td> <% out.println(panneau.getPowerGenerate()); %> </td>
		</tr>	
	<% } %>
	</table>
</body>
</html>