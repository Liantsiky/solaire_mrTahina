<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="project.models.*"%>
<%@page import="java.util.*"%>
<%@page import="java.time.*"%>
<% Coupure coupure = (Coupure)request.getAttribute("coupure"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Coupure du systeme : <% out.println(coupure.getSysteme().getId()); %>
	</br>
	Date prevision : <% out.println(coupure.getDateCoupure().toString()); %>
	</br>
	Heure Coupure : <% out.println(coupure.getHeureCoupure().toString()); %>
	<table border="1">
		<tr>
			<th>Heure</th>
			<th>Creneau</th>
			<th>Puissance utilisé</th>
			<th>Panneau</th>
			<th>Reste Batterie </th>
		</tr>
		<% for(DetailsCoupure detailsCoupure: coupure.getDetailsCoupures()) { %>
			<tr>
				<td><% out.println(detailsCoupure.getHeure().toString()); %></td>
				<td><% out.println(detailsCoupure.getCreneau().getId()); %></td>
				<td><% out.println(detailsCoupure.getPowerUse()); %></td>
				<td><% out.println(detailsCoupure.getPanneaupourvoir()); %></td>
				<td><% out.println(detailsCoupure.getBatterieReste()); %></td>
			</tr>
		<% } %>
	</table>
</body>
</html>