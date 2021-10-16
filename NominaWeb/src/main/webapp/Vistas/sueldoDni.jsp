<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consultar el sueldo de los empleados por DNI</title>
<link rel="stylesheet" href="estilos.css">
</head>
<body>

	<h1>Consultar el sueldo de los empleados por DNI</h1>

	<form action="Controller?opcion=sueldo" method="post">
		<table class="tftable" border="1">
			<tr>
				<th>Introduzca el DNI de un empleado</th>
				<td><input type="text" name="dni"></td>
			</tr>
		</table>
		</br> <input type="submit" value="Consultar">
	</form>
	</br>

	<%
	if (request.getParameter("dni") == null) {
	%>
	<p>Inserte un DNI válido</p>
	<button onclick="window.location.href='sueldoDni.jsp'">Repetir
		la búsqueda</button>

	<%
	} else {
	%>

	<table class="tftable" border="1">
		<tr>
			<th>DNI</th>
			<th>Sueldo</th>
		</tr>
		<tr>
			<td>
				<%
				request.getParameter("dni");
				%>

			</td>
			<td><c:out value="${sueldo}"></c:out></td>
		</tr>
	</table>

	</br>
	</br>
	<button onclick="window.location.href='sueldoDni.jsp'">Repetir
		la búsqueda</button>

	<%
	}
	%>


	<button onclick="window.location.href='index.jsp'">Regresar al
		menú de opciones</button>

</body>
</html>