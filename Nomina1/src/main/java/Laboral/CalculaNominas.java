package Laboral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculaNominas {

	/**
	 * Logger
	 */
	final static Logger LOGGER = LoggerFactory.getLogger(CalculaNominas.class);

	/**
	 * Método principal
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		LOGGER.info("Inicio del método principal");

		try {

			// Creación de objetos empleados y nómina
			Empleado emp1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);

			Empleado emp2 = new Empleado("Ada Lovelace", "32000031R", 'F');

			Nomina nom = new Nomina();

			// Impresión de los atributos y sueldo de los empleados
			escribe(emp1, nom);
			escribe(emp2, nom);

			// Incremento de años para el empleado 2
			emp2.incrAnyo();

			// Establecimiento de la categoría del empleado 1
			emp1.setCategoria(9);

			// Impresión de los atributos y sueldo de los empleados
			escribe(emp1, nom);
			escribe(emp2, nom);
		} catch (DatosNoCorrectosException e) {
			System.out.println("Datos no correctos");
		}

		LOGGER.info("Fin del método principal");
	}

	/**
	 *  Método de impresión de atributos y sueldo de los empleados
	 *  
	 * @param emp
	 * @param nom
	 */
	private static void escribe(Empleado emp, Nomina nom) {
		
		LOGGER.info("Inicio del método escribe");
		
		emp.imprime();
		System.out.println("Sueldo del empleado: " + nom.sueldo(emp));
		
		LOGGER.info("Fin del método escribe");

	}
}
