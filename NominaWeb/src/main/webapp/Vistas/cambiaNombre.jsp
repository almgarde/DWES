<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Actualizar nombre</title>
<link rel="stylesheet" href="estilos.css">
</head>
<body>

	<h1>Actualizar el nombre de un empleado</h1>

	<form action="Controller?opcion=nombre" method="post">
		<table class="tftable" border="1">
			<tr>
				<th>Introduzca el DNI de un empleado</th>
				<td><input type="text" name="dni"></td>
			</tr>
			<tr>
				<th>Introduzca el nuevo nombre que desea guardar</th>
				<td><input type="text" name="nomEmp"></td>
			</tr>
		</table>
		</br> <input type="submit" value="Actualizar">
	</form>
	</br>

	<%
	if (request.getParameter("dni") == null || request.getParameter("nomEmp") == null) {
	%>
	<p>Inserte un DNI o nombre válidos</p>
	<button onclick="window.location.href='cambiaNombre.jsp'">Repetir
		el proceso</button>

	<%
	} else {
	%>
	<p>
		<c:out value="${result}"></c:out>
	</p>
	<%
	}
	%>

	<button onclick="window.location.href='index.jsp'">Regresar al
		menú de opciones</button>
</body>
</html>