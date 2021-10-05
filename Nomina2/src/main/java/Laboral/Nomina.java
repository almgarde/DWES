package Laboral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nomina {

	/**
	 * Logger
	 */
	final static Logger LOGGER = LoggerFactory.getLogger(Nomina.class);

	/**
	 * Biblioteca de sueldos bases en función de la categoría
	 */
	private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
			230000 };

	/**
	 * Cálculo del sueldo del empleado
	 * 
	 * @param emp
	 * @return
	 */
	public double sueldo(Empleado emp) {

		LOGGER.info("Inicio del método sueldo");

		double sueldo = SUELDO_BASE[emp.getCategoria() - 1] + 5000 * emp.anyos;
		LOGGER.info("Fin del método sueldo");
		return sueldo;

	}
}
