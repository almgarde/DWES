package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import modelo.DatosNoCorrectosException;
import modelo.Empleado;
import modelo.Nomina;

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
	final static Logger LOGGER = LoggerFactory.getLogger(ManejaBD.class);

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
	 * Datos de los empleados de la BD
	 * 
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	public List consultaDatosEmpleados() throws SQLException, DatosNoCorrectosException {

		LOGGER.info("Inicio del método consultaDatosEmpleados");

		List empleados = new ArrayList();

		st = conn.createStatement();
		rs = st.executeQuery("SELECT * FROM EMPLEADOS");

		while (rs.next()) {

			String nombre = rs.getString("NOMBRE");
			String dni = rs.getString("DNI");
			char sexo = rs.getString("SEXO").charAt(0);
			int categoria = rs.getInt("CATEGORIA");
			int anyos = rs.getInt("ANYOS");

			Empleado emp = new Empleado(nombre, dni, sexo, categoria, anyos);
			empleados.add(emp);

		}
		LOGGER.info("Fin del método consultaDatosEmpleados");
		return empleados;

	}

	/**
	 * Consulta de los empleados por dni
	 * 
	 * @param dni
	 * @return
	 * @throws SQLException
	 * @throws DatosNoCorrectosException
	 */
	private Empleado buscaEmpleadoPorDNI(String dni) throws SQLException, DatosNoCorrectosException {

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
	public static double consultaSueldoPorDni(String dni) throws SQLException {

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

}
