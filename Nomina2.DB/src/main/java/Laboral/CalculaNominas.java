package Laboral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
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

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Scanner sc = new Scanner(System.in);

		List<Empleado> empleados = new ArrayList<>();
		Nomina nom = new Nomina();
		String nombre;
		String dni;
		char sexo;
		int categoria;
		int anyos;

		LOGGER.info("Inicio del método principal");

		try {
			conn = DBUtils.getConnection();
			ManejaBD mbd = new ManejaBD(conn);

			// Estado de la conexión: Descomentar para usar

			System.out.println("Conexión válida: " + conn.isValid(10));

			System.out.println("Estado del autocommit: " + conn.getAutoCommit());

			int opcion;

			// Menú de opciones
			do {
				System.out.println("--------------Menú---------------");
				System.out.println("1 - Consultar los datos de los empleados");
				System.out.println("2 - Dar de alta un nuevo empleado");
				System.out.println("3 - Consultar el sueldo de un empleado por su dni");
				System.out.println("4 - Actualizar los datos de los empleados");
				System.out.println("5 - Actualizar los salarios base");
				System.out.println("6 - Salir");
				System.out.println("Escoge una opción del menú");
				opcion = sc.nextInt();
				sc.nextLine(); // limpieza de búfer

				switch (opcion) {

				// Datos de los empleados

				case 1:
					mbd.consultaDatosEmpleados();
					break;

				// Alta de empleado

				case 2:

					System.out.println("Seleccione como desea dar de alta al empleado");
					System.out.println("1 - Ingresando valores");
					System.out.println("2 - Mediante un fichero");
					int opcion2 = sc.nextInt();
					sc.nextLine(); // Limpieza del búfer
					switch (opcion2) {
					case 1:
						System.out.println("Diga su nombre");
						nombre = sc.nextLine();
						System.out.println("Diga su dni");
						dni = sc.nextLine();
						System.out.println("Seleccione su sexo: M o F");
						sexo = sc.nextLine().charAt(0);
						System.out.println("Diga su categoria");
						categoria = sc.nextInt();
						sc.nextLine();
						System.out.println("Diga los años trabajados");
						anyos = sc.nextInt();
						sc.nextLine(); // Limpieza del búfer
						mbd.altaEmpleado(nombre, dni, sexo, categoria, anyos);
						break;

					case 2:
						System.out.println("Diga el nombre del fichero");
						String nombreFichero = sc.nextLine();
						File fichero = new File(nombreFichero);
						mbd.altaEmpleadoFichero(fichero, empleados);
						break;

					default:
						System.out.println("Introduce un valor correcto");
						break;
					}
					break;

				// Consulta de sueldo por dni

				case 3:
					System.out.println("Dime el dni del empleado que desea consultar");
					dni = sc.nextLine();
					mbd.consultaSueldoPorDni(dni);

					break;

				// Actualizar datos de un empleado

				case 4:

					// Selección del empleado a actualizar
					System.out.println("Dime el dni del empleado que desea actualizar");
					dni = sc.nextLine();

					// Menu de actualizaciones
					System.out.println("Seleccione el campo a actualizar");
					System.out.println("1 - Nombre");
					System.out.println("2 - Dni");
					System.out.println("3 - Sexo");
					System.out.println("4 - Categoría");
					System.out.println("5 - Años trabajados");
					int opcion3 = sc.nextInt();
					sc.nextLine(); // Limpieza del búfer
					switch (opcion3) {

					case 1:
						System.out.println("Diga el nombre nuevo");
						String nombreNuevo = sc.nextLine();
						mbd.actualizarNombre(dni, nombreNuevo);
						break;

					case 2:
						System.out.println("Diga el dni nuevo");
						String dniNuevo = sc.nextLine();
						mbd.actualizarDni(dni, dniNuevo);
						break;

					case 3:
						System.out.println("Diga el sexo nuevo");
						char sexoNuevo = sc.nextLine().charAt(0);
						mbd.actualizarSexo(dni, sexoNuevo);
						break;

					case 4:
						System.out.println("Diga la categoría nueva");
						int categoriaNueva = sc.nextInt();
						sc.nextLine();
						mbd.actualizarCategoria(dni, categoriaNueva);
						break;

					case 5:
						System.out.println("Diga el número de años nuevo");
						int anyosNuevo = sc.nextInt();
						sc.nextLine();
						mbd.actualizarAnyos(dni, anyosNuevo);
						break;

					default:
						System.out.println("Introduce una opción correcta");
						break;
					}

					break;

				case 5:
					System.out.println("¿Qué categoría deseas actualizar?");
					int cat = sc.nextInt();
					System.out.println("¿Cuál es el nuevo sueldo base?");
					int sb = sc.nextInt();
					sc.nextLine();
					mbd.actualizarSueldoBase(cat, sb);
					break;

				case 6:
					System.out.println("Adios!");
					break;

				}

			} while (opcion != 6);

		} catch (SQLException e) {
			System.out.println("Ocurrió algún error al conectar u operar con la BD");
		} catch (DatosNoCorrectosException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Se produjo un error al operar con el fichero");

		} finally {

			try {
				DBUtils.close(rs);
				DBUtils.close(st);
				DBUtils.close(conn);
			} catch (SQLException e) {
				System.out.println("Ocurrió una excepción al cerrar la BD");
			}
		}

		LOGGER.info("Fin del método principal");
	}
}
