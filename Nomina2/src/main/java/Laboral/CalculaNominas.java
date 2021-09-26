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
		List<String> cadenas = new ArrayList();
		List<Empleado> empleados = new ArrayList();
		Nomina nom = new Nomina();

		try {

			try (FileReader fr = new FileReader(fichero); BufferedReader br = new BufferedReader(fr)) {

				while (br.ready()) {
					String cadena = br.readLine();
					cadenas.add(cadena);

				}

				Iterator iter = cadenas.iterator();
				while (iter.hasNext()) {
					String elem = (String) iter.next();
					String[] parts = elem.split("-");

					String nombre = parts[0];

					String dni = parts[1];

					String part3 = parts[2];
					char sexo = part3.charAt(0);

					int categoria = Integer.parseInt(parts[3]);

					int anyos = Integer.parseInt(parts[4]);

					Empleado emp = new Empleado(nombre, dni, sexo, categoria, anyos);

					empleados.add(emp);

				}

			} catch (FileNotFoundException ex) {
				System.err.println("El fichero no se encuentra");
			} catch (IOException ex) {
				System.err.println("Se produjo un error al leer del fichero ");
			}

			try (FileWriter fw = new FileWriter("sueldos.txt", true); BufferedWriter bw = new BufferedWriter(fw)) {

				Iterator iter2 = empleados.iterator();
				while (iter2.hasNext()) {
					Empleado elem = (Empleado) iter2.next();
					String sueldosEmpleados = "DNI: " + elem.dni + ", Sueldo: "
							+ String.valueOf(nom.sueldo(elem));
					fw.write(sueldosEmpleados);
					bw.newLine();
					bw.flush();
				}
			} catch (Exception e) {
				System.err.println("Se produjo error al escribir en el fichero ");
			}

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
