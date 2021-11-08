<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mostrar los datos de los empleados</title>
<link rel="stylesheet" href="estilos.css">
</head>
<body>

	<h1>Datos de los empleados dados de alta</h1>

	</br>

	<table class="tftable" border="1">
		<tr>
			<th>Nombre</th>
			<th>Dni</th>
			<th>Sexo</th>
			<th>Categoría</th>
			<th>Años trabajados</th>
		</tr>

		<c:forEach var="empleado" items="${empleados}">
			<tr>
				<td><c:out value="${empleado.nombre}"></c:out></td>
				<td><c:out value="${empleado.dni}"></c:out></td>
				<td><c:out value="${empleado.sexo}"></c:out></td>
				<td><c:out value="${empleado.categoria}"></c:out></td>
				<td><c:out value="${empleado.anyos}"></c:out></td>
			</tr>
		</c:forEach>

	</table>
	</br>
	</br>

	<button onclick="window.location.href='index.jsp'">Regresar al
		menú de opciones</button>

</body>
</html>