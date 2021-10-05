package Laboral;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManejaFicheros {

	static FileReader fr;
	static BufferedReader br;
	static FileWriter fw;
	static BufferedWriter bw;

	public static void leeFicheros(File fichero, List<Empleado> empleados)
			throws DatosNoCorrectosException, IOException {

		List<String> cadenas = new ArrayList<>();

		fr = new FileReader(fichero);
		br = new BufferedReader(fr);

		while (br.ready()) {
			String cadena = br.readLine();
			cadenas.add(cadena);

		}

		Iterator<String> iter = cadenas.iterator();
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

	}

	public void escribeFicheroNominas(File fichero, List<Empleado> empleados, boolean anexar, Nomina nom)
			throws IOException, SQLException {

		fw = new FileWriter(fichero, anexar);
		bw = new BufferedWriter(fw);

		Iterator<Empleado> iter2 = empleados.iterator();
		while (iter2.hasNext()) {
			Empleado elem = (Empleado) iter2.next();
			String sueldosEmpleados = elem.dni + "-" + String.valueOf(nom.sueldo(elem));
			fw.write(sueldosEmpleados);
			bw.newLine();
			bw.flush();
		}
	}

	public void escribeFicheroEmpleados(File fichero, List<Empleado> empleados, boolean anexar) throws IOException {

		fw = new FileWriter(fichero, anexar);
		bw = new BufferedWriter(fw);

		Iterator<Empleado> iter2 = empleados.iterator();
		while (iter2.hasNext()) {
			Empleado elem = (Empleado) iter2.next();
			String sueldosEmpleados = elem.nombre + "-" + elem.dni + "-" + elem.sexo + "-" + elem.getCategoria() + "-"
					+ elem.anyos;
			fw.write(sueldosEmpleados);
			bw.newLine();
			bw.flush();
		}
	}

}
