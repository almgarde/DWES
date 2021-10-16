package modelo;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ManejaBD;

public class Nomina {

	/**
	 * Logger
	 */
	final static Logger LOGGER = LoggerFactory.getLogger(Nomina.class);

	/**
	 * Cálculo del sueldo del empleado
	 * 
	 * @param emp
	 * @param sueldo_base
	 * @return
	 * @throws SQLException
	 */
	public static int sueldo(Empleado emp) throws SQLException {

		LOGGER.info("Inicio del método sueldo");

		int sueldo_base = ManejaBD.consultaSueldoBase(emp.getCategoria());
		int sueldo = sueldo_base + 5000 * emp.anyos;

		LOGGER.info("Fin del método sueldo");

		return sueldo;

	}
}
