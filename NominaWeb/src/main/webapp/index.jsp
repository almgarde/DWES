<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menú de Opciones</title>
<link rel="stylesheet" href="estilos.css">
</head>
<body>


	<h1>Menú para la gestión de los empleados</h1>

	<table class="tftable" border="1">
		<tr>
			<td><a href="Controller?opcion=mostrarDatos">Mostrar los
					datos de los empleados dados de alta</a></td>
		</tr>
		<tr>
			<td><a href="Controller?opcion=sueldoDni">Consultar sueldo
					por DNI</a></td>
		</tr>
	</table>

	</br>
	</br>

	<table class="tftable" border="1">
		<tr>
			<th>Actualizar datos de un empleado</th>
		</tr>
		<tr>
			<td><a href="Controller?opcion=cambiaNombre">Actualizar
					nombre</a></td>
		</tr>
		<tr>
			<td><a href="Controller?opcion=cambiaDni">Actualizar dni</a></td>
		</tr>
		<tr>
			<td><a href="Controller?opcion=cambiaSexo">Actualizar sexo</a></td>
		</tr>
		<tr>
			<td><a href="Controller?opcion=cambiaCateg">Actualizar
					categoría</a></td>
		</tr>
		<tr>
			<td><a href="Controller?opcion=cambiaAnyos">Actualizar años
					trabajados</a></td>
		</tr>
	</table>
</body>
</html>