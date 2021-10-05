package Laboral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManejaBD {

	/**
	 * Logger
	 */
	final static Logger LOGGER = LoggerFactory.getLogger(CalculaNominas.class);

	static Connection conn = null;
	static Statement st = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	Empleado emp;

	public ManejaBD(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Alta de un nuevo empleado mediante parámetros
	 * 
	 * @param nombre
	 * @param dni
	 * @param sexo
	 * @param categoria
	 * @param anyos
	 * @throws DatosNoCorrectosException
	 * @throws SQLException
	 */
	public void altaEmpleado(String nombre, String dni, char sexo, int categoria, int anyos)
			throws DatosNoCorrectosException, SQLException {

		LOGGER.info("Inicio del método altaEmpleado");

		// Datos a introducir en la BD

		Empleado emp = new Empleado(nombre, dni, sexo, categoria, anyos);

		// Incorporación a la BS: Tabla Empleados

		ps = conn
				.prepareStatement("INSERT INTO EMPLEADOS (nombre, dni, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)");
		ps.setString(1, nombre);
		ps.setString(2, dni);
		ps.setString(3, String.valueOf(sexo));
		ps.setInt(4, categoria);
		ps.setInt(5, anyos);

		ps.executeUpdate();

		// Incorporación a la BS: Tabla Nominas

		double sueldoEmpleado = Nomina.sueldo(emp);

		ps = conn.prepareStatement("INSERT INTO NOMINAS (dni, sueldo) VALUES (?, ?)");
		ps.setString(1, dni);
		ps.setDouble(2, sueldoEmpleado);

		ps.executeUpdate();

		LOGGER.info("Fin del método altaEmpleado");

	}

	/**
	 * Alta de un nuevo empleado mediante fichero
	 * 
	 * @param fichero
	 * @param empleados
	 * @throws DatosNoCorrectosException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void altaEmpleadoFichero(File fichero, List<Empleado> empleados)
			throws DatosNoCorrectosException, SQLException, IOException {

		LOGGER.info("Inicio del método altaEmpleadoFichero");

		ManejaFicheros.leeFicheros(fichero, empleados);

		Iterator<Empleado> iter = empleados.iterator();
		while (iter.hasNext()) {

			// Extracción datos del fichero

			Empleado elem = (Empleado) iter.next();
			String nombre = elem.nombre;
			String dni = elem.dni;
			char sexo = elem.sexo;
			int categoria = elem.getCategoria();
			int anyos = elem.anyos;

			altaEmpleado(nombre, dni, sexo, categoria, anyos);

		}

		LOGGER.info("Fin del método altaEmpleadoFichero");

	}

	/**
	 * Datos de los empleados de la BD
	 * 
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public void consultaDatosEmpleados() throws SQLException, DatosNoCorrectosException {

		LOGGER.info("Inicio del método consultaDatosEmpleados");

		st = conn.createStatement();
		rs = st.executeQuery("SELECT * FROM EMPLEADOS");

		while (rs.next()) {

			String nombre = rs.getString("NOMBRE");
			String dni = rs.getString("DNI");
			char sexo = rs.getString("SEXO").charAt(0);
			int categoria = rs.getInt("CATEGORIA");
			int anyos = rs.getInt("ANYOS");

			Empleado emp = new Empleado(nombre, dni, sexo, categoria, anyos);
			emp.imprime();
		}

		LOGGER.info("Fin del método consultaDatosEmpleados");
	}

	/**
	 * Consulta de los empleados por dni
	 * 
	 * @param dni
	 * @return
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public Empleado buscaEmpleadoPorDNI(String dni) throws SQLException, DatosNoCorrectosException {

		LOGGER.info("Inicio del método buscaEmpleadoPorDNI");

		ps = conn.prepareStatement("SELECT * FROM EMPLEADOS WHERE DNI = ?");
		ps.setString(1, dni);

		rs = ps.executeQuery();

		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				String nombre = rs.getString("NOMBRE");
				char sexo = rs.getString("SEXO").charAt(0);
				int categoria = rs.getInt("CATEGORIA");
				int anyos = rs.getInt("ANYOS");

				emp = new Empleado(nombre, dni, sexo, categoria, anyos);

				LOGGER.info("Fin del método buscaEmpleadoPorDNI");
				return emp;
			}
		} else {
			System.out.println("No se encuentra al empleado con dni " + dni);

		}
		LOGGER.error("No se ha encontrado el dni");

		return null;
	}

	/**
	 * Consulta del sueldo de los empleados por Dni
	 * 
	 * @param dni
	 * @return
	 * @throws SQLException
	 */
	public double consultaSueldoPorDni(String dni) throws SQLException {

		LOGGER.info("Inicio del método consultaSueldoPorDni");

		ps = conn.prepareStatement("SELECT * FROM NOMINAS WHERE DNI = ?");
		ps.setString(1, dni);

		rs = ps.executeQuery();

		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				double sueldo = rs.getInt("SUELDO");

				System.out.println("El empleado con dni " + dni + " tiene un sueldo de " + sueldo);
				LOGGER.info("Fin del método consultaSueldoPorDni");
				return sueldo;
			}
		} else {
			System.out.println("No se encuentra al empleado con dni " + dni);

		}

		LOGGER.error("No se ha encontrado el dni");
		return 0;
	}

	/**
	 * Sueldo base en función de la categoría
	 * 
	 * @param categoria
	 * @return
	 * @throws SQLException
	 */
	public static int consultaSueldoBase(int categoria) throws SQLException {

		LOGGER.info("Inicio del método consultaSueldoBase");

		ps = conn.prepareStatement("SELECT SUELDO_BASE FROM SUELDOS_BASE WHERE CATEGORIA = ?");
		ps.setInt(1, categoria);

		rs = ps.executeQuery();

		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				int sueldo_base = rs.getInt("SUELDO_BASE");
				LOGGER.info("Fin del método consultaSueldoBase");
				return sueldo_base;
			}
		} else {
			System.out.println("No se encuentra la categoría " + categoria);

		}
		LOGGER.error("No se ha encontrado la categoría");

		return 0;
	}

	/**
	 * Actualizar el nombre de los empleados
	 * 
	 * @param dni
	 * @param nombre
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public void actualizarNombre(String dni, String nombre) throws SQLException, DatosNoCorrectosException {

		LOGGER.info("Inicio del método actualizarNombre");

		st = conn.createStatement();

		if (buscaEmpleadoPorDNI(dni) != null) {
			st.executeUpdate("UPDATE EMPLEADOS SET NOMBRE = '" + nombre + "' WHERE DNI='" + dni + "'");
			LOGGER.info("Fin del método actualizarNombre");
		} else {
			LOGGER.error("No se ha encontrado el dni");
		}

	}

	/**
	 * Actualizar el dni de los empleados
	 * 
	 * @param dni
	 * @param dniNuevo
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public void actualizarDni(String dni, String dniNuevo) throws SQLException, DatosNoCorrectosException {

		LOGGER.info("Inicio del método actualizarDni");

		st = conn.createStatement();

		if (buscaEmpleadoPorDNI(dni) != null) {
			st.executeUpdate("UPDATE EMPLEADOS SET DNI = '" + dniNuevo + "' WHERE DNI='" + dni + "'");
			LOGGER.info("Fin del método actualizarDni");
		} else {
			LOGGER.error("No se ha encontrado el dni");
		}

	}

	/**
	 * Actualizar el sexo de los empleados
	 * 
	 * @param dni
	 * @param sexo
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public void actualizarSexo(String dni, char sexo) throws SQLException, DatosNoCorrectosException {

		LOGGER.info("Inicio del método actualizarSexo");

		st = conn.createStatement();

		if (buscaEmpleadoPorDNI(dni) != null) {
			st.executeUpdate("UPDATE EMPLEADOS SET SEXO = '" + sexo + "' WHERE DNI='" + dni + "'");
			LOGGER.info("Fin del método actualizarSexo");
		} else {
			LOGGER.error("No se ha encontrado el dni");
		}

	}

	/**
	 * Actualizar la categoría de los empleados
	 * 
	 * @param dni
	 * @param categoria
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public void actualizarCategoria(String dni, int categoria) throws SQLException, DatosNoCorrectosException {

		LOGGER.info("Inicio del método actualizarCategoria");

		st = conn.createStatement();

		if (buscaEmpleadoPorDNI(dni) != null) {
			st.executeUpdate("UPDATE EMPLEADOS SET CATEGORIA = '" + categoria + "' WHERE DNI='" + dni + "'");
			actualizarSueldo(dni);
			LOGGER.info("Fin del método actualizarCategoria");
		} else {
			LOGGER.error("No se ha encontrado el dni");
		}

	}

	/**
	 * Actualizar los años de trabajo de los empleados
	 * 
	 * @param dni
	 * @param anyos
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public void actualizarAnyos(String dni, int anyos) throws SQLException, DatosNoCorrectosException {

		LOGGER.info("Inicio del método actualizarAnyos");

		st = conn.createStatement();

		if (buscaEmpleadoPorDNI(dni) != null) {
			st.executeUpdate("UPDATE EMPLEADOS SET ANYOS = '" + anyos + "' WHERE DNI='" + dni + "'");

			actualizarSueldo(dni);

			LOGGER.info("Fin del método actualizarAnyos");
		} else {
			LOGGER.error("No se ha encontrado el dni");
		}

	}

	/**
	 * Actualizar el sueldo de los empleados
	 * 
	 * @param dni
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	private void actualizarSueldo(String dni) throws SQLException, DatosNoCorrectosException {

		LOGGER.info("Inicio del método actualizarSueldo");

		emp = buscaEmpleadoPorDNI(dni);

		if (buscaEmpleadoPorDNI(dni) != null) {
			int sueldo = Nomina.sueldo(emp);

			st.executeUpdate("UPDATE NOMINAS SET SUELDO = '" + sueldo + "' WHERE DNI='" + dni + "'");
			LOGGER.info("Fin del método actualizarSueldo");
		} else {
			LOGGER.error("No se ha encontrado el dni");
		}

	}

	public void actualizarSueldoBase(int categoria, int sueldoBase) throws SQLException, DatosNoCorrectosException {

		LOGGER.info("Inicio del método actualizarSueldoBase");

		st = conn.createStatement();

		List categorias = new ArrayList<>();
		List dnis = new ArrayList();

		// Añade todas las categorías a una lista

		rs = st.executeQuery("SELECT CATEGORIA FROM SUELDOS_BASE");

		while (rs.next()) {
			int cat = rs.getInt("CATEGORIA");
			categorias.add(cat);
		}

		// Comprueba que la categoría a actualizar se encuentra en la base de datos y la
		// actualiza

		if (categorias.contains(categoria)) {
			st.executeUpdate(
					"UPDATE SUELDOS_BASE SET SUELDO_BASE = '" + sueldoBase + "' WHERE CATEGORIA='" + categoria + "'");
			LOGGER.info("Fin del método actualizarSueldoBase");
		} else {
			LOGGER.error("No se ha encontrado la categoría");
		}

		// Añade todos los dni de los empleados a una lista

		rs = st.executeQuery("SELECT DNI FROM EMPLEADOS");

		while (rs.next()) {
			String dni = rs.getString("DNI");
			dnis.add(dni);
		}

		// Actualiza los sueldos de los empleados

		Iterator iter = dnis.iterator();
		while (iter.hasNext()) {
			String elem = (String) iter.next();
			actualizarSueldo(elem);
		}

	}

}
