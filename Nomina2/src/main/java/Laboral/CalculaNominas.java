package Laboral;

import org.apache.velocity.runtime.directive.Foreach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

		File fichero = new File("empleados.txt");
		File fichero2 = new File("sueldos.txt");
		List<Empleado> empleados = new ArrayList<>();
		Nomina nom = new Nomina();
		ManejaFicheros mf = new ManejaFicheros();

		try {

			mf.leeFicheros(fichero, empleados);
			mf.escribeFicheroNominas(fichero2, empleados, true, nom);

		} catch (FileNotFoundException ex) {
			System.err.println("El fichero no se encuentra");
		} catch (IOException e) {
			System.err.println("Se produjo error al leer o escribir en el fichero ");
		} catch (DatosNoCorrectosException e) {
			System.out.println("Datos no correctos");
		}

		LOGGER.info("Fin del método principal");
	}

	/**
	 * Método de impresión de atributos y sueldo de los empleados
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
