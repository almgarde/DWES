package Laboral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.Scanner;

public class CalculaNominas {

	/**
	 * Logger
	 */
	final static Logger LOGGER = LoggerFactory.getLogger(CalculaNominas.class);

	static Connection conn = null;
	static Statement st = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	static Scanner sc = new Scanner(System.in);
	static Nomina nom = new Nomina();;
	static Empleado emp;
	static String nomEmpleado;
	static String dniEmpleado;
	static char sexoEmpleado;
	static int categoriaEmpleado;
	static int anyosEmpleado;

	/**
	 * Método principal
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		LOGGER.info("Inicio del método principal");

		try {
			conn = DBUtils.getConnection();

			// Estado de la conexión

			System.out.println("Conexión válida: " + conn.isValid(10));

			System.out.println("Estado del autocommit: " + conn.getAutoCommit());

			int opcion;

			// Menú de opciones
			do {
				System.out.println("Escoge una opción del menú");
				System.out.println("1 - Consultar los datos de los empleados");
				System.out.println("2 - Dar de alta un nuevo empleado");
				System.out.println("3 - Consultar el sueldo de un empleado por su nombre");
				System.out.println("4 - Actualizar los datos de los empleados");
				System.out.println("5 - Salir");
				opcion = sc.nextInt();
				sc.nextLine(); // limpieza de búfer

				switch (opcion) {

				// Datos de los empleados
				case 1:
					st = conn.createStatement();
					rs = st.executeQuery("SELECT * FROM EMPLEADOS");

					while (rs.next()) {

						nomEmpleado = rs.getString("NOMBRE");
						dniEmpleado = rs.getString("DNI");
						sexoEmpleado = rs.getString("SEXO").charAt(0);
						categoriaEmpleado = rs.getInt("CATEGORIA");
						anyosEmpleado = rs.getInt("ANYOS");
						int sueldo = rs.getInt("SUELDO");

						emp = new Empleado(nomEmpleado, dniEmpleado, sexoEmpleado, categoriaEmpleado, anyosEmpleado);
						emp.imprime();
					}
					break;

				// Alta de empleado
				case 2:
					altaEmpleado();
					break;

				// Consulta de sueldo por nombre
				case 3:
					System.out.println("Dime el nombre del empleado que desea consultar");
					String nombre = sc.nextLine();

					ps = conn.prepareStatement("SELECT * FROM EMPLEADOS WHERE NOMBRE = ?");
					ps.setString(1, nombre);

					rs = ps.executeQuery();

					if (rs.isBeforeFirst()) {
						while (rs.next()) {
							nomEmpleado = rs.getString("NOMBRE");
							int sueldo = rs.getInt("SUELDO");

							System.out.println("El empleado " + nomEmpleado + " tiene un sueldo de " + sueldo);
						}
					} else {
						System.out.println("No se encuentra al cliente " + nombre);
					}
					break;

				// Actualizar categoría y sueldo de un empleado
				case 4:

					// Selección del empleado a actualizar
					System.out.println("Dime el nombre del empleado que cuya categoría desea actualizar");
					nomEmpleado = sc.nextLine();
					ps = conn.prepareStatement("SELECT * FROM EMPLEADOS WHERE NOMBRE = ?");
					ps.setString(1, nomEmpleado);
					rs = ps.executeQuery();

					if (rs.isBeforeFirst()) {
						while (rs.next()) {

							int numAnyos = rs.getInt(5);

							// Selección de la categoría
							System.out.println("¿Qué categoría desea adjudicarle?");
							int nuevaCategoria = sc.nextInt();
							ps = conn.prepareStatement("SELECT SUELDO_BASE FROM NOMINAS WHERE CATEGORIA = ?");
							ps.setInt(1, nuevaCategoria);
							rs = ps.executeQuery();

							int sueldo_base = rs.getInt(2);
							int sueldo = sueldo_base + 5000 * numAnyos;

							// Actualización de los datos en la BD
							st.executeUpdate("UPDATE EMPLEADOS SET CATEGORIA = ? AND SUELDO = ? WHERE NOMBRE = ?");
							ps.setInt(4, nuevaCategoria);
							ps.setInt(6, sueldo);
							rs = ps.executeQuery();

						}
					} else {
						System.out.println("No se encuentra al cliente " + nomEmpleado);
					}

					break;
				case 5:
					System.out.println("Adios!");
					break;

				}

			} while (opcion != 5);

		} catch (

		SQLException e) {
			System.out.println("Ocurrió algún error al conectar u operar con la BD");
		} catch (DatosNoCorrectosException e) {
			System.out.println("Datos no correctos");

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (st != null) {
					st.close();
				}

				DBUtils.close(conn);
			} catch (SQLException e) {
				System.out.println("Ocurrió una excepción al cerrar la BD");
			}
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

	/**
	 * Alta en BBDD de un nuevo empleado
	 */
	private static void altaEmpleado() {
		
		LOGGER.info("Inicio del método altaEmpleado");

		try {
			// Datos a introducir en la BD
			System.out.println("ALTA DE NUEVO EMPLEADO");
			System.out.println("Diga el nombre del empleado:");
			nomEmpleado = sc.nextLine();
			System.out.println("Diga su DNI:");
			dniEmpleado = sc.nextLine();
			System.out.println("Diga su sexo:");
			sexoEmpleado = sc.nextLine().charAt(0);
			System.out.println("Diga su categoría:");
			categoriaEmpleado = sc.nextInt();
			System.out.println("Diga sus años de antigüedad:");
			anyosEmpleado = sc.nextInt();
			emp = new Empleado(nomEmpleado, dniEmpleado, sexoEmpleado, categoriaEmpleado, anyosEmpleado);
			double sueldoEmpleado = nom.sueldo(emp);

			// Incorporación a la BS
			ps = conn.prepareStatement(
					"INSERT INTO EMPLEADOS (nomEmpleado, dniEmpleado, sexoEmpleado, categoriaEmpleado, anyosEmpleado, sueldoEmpleado "
							+ "VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, nomEmpleado);
			ps.setString(2, dniEmpleado);
			ps.setString(3, String.valueOf(sexoEmpleado));
			ps.setInt(4, categoriaEmpleado);
			ps.setInt(5, anyosEmpleado);
			ps.setDouble(6, sueldoEmpleado);

			ps.executeUpdate();

		} catch (DatosNoCorrectosException e) {
			System.out.println("Datos no correctos");
		} catch (SQLException e) {
			System.out.println("Ocurrió algún error al conectar u operar con la BD");
		}

		LOGGER.info("Fin del método altaEmpleado");
	}
}
