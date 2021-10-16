package controlador;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexion.DBUtils;
import dao.ManejaBD;
import modelo.DatosNoCorrectosException;

/**
 * Servlet implementation class controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	Connection conn;
	ManejaBD mbd;

	public void init() throws ServletException {
		try {
			conn = DBUtils.getConnection();
			mbd = new ManejaBD(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String opcion = request.getParameter("opcion");

//		if (opcion.equals("mostrarDatos")) {
//			System.out.println("Se ha seleccionado mostrar los datos de los empleados");
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Vistas/mostrarDatos.jsp");
//		requestDispatcher.forward(request, response);
//		
//		} else if (opcion.equals("sueldoDni")) {
//
//			System.out.println("Se ha seleccionado buscar un empleado por DNI");
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Vistas/sueldoDni.jsp");
//			requestDispatcher.forward(request, response);
//
//			}

		switch ("opcion") {
		case "mostrarDatos":

			try {
				List empleados = new ArrayList();

				empleados = mbd.consultaDatosEmpleados();

				request.setAttribute("empleados", empleados);
				RequestDispatcher requestDispatcher2 = request.getRequestDispatcher("/Vistas/mostrarDatos.jsp");
				requestDispatcher2.forward(request, response);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DatosNoCorrectosException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;
		case "sueldoDni":
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Vistas/sueldoDni.jsp");
			requestDispatcher.forward(request, response);
			break;
		case "sueldo":

			try {
				String dni = request.getParameter("dni");
				double sueldo = mbd.consultaSueldoPorDni(dni);
				request.setAttribute("sueldo", sueldo);
				RequestDispatcher requestDispatcher2 = request.getRequestDispatcher("/Vistas/sueldoDni.jsp");
				requestDispatcher2.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "cambiaNombre":
			RequestDispatcher requestDispatcher3 = request.getRequestDispatcher("/Vistas/cambiaNombre.jsp");
			requestDispatcher3.forward(request, response);
			break;
		case "nombre":
			try {
				String dni = request.getParameter("dni");
				String nombre = request.getParameter("nomEmp");
				mbd.actualizarNombre(dni, nombre);
				request.setAttribute("result", "Actualización realizada");
				RequestDispatcher requestDispatcher2 = request.getRequestDispatcher("/Vistas/cambiaNombre.jsp");
				requestDispatcher2.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatosNoCorrectosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "cambiaDni":
			RequestDispatcher requestDispatcher4 = request.getRequestDispatcher("/Vistas/cambiaDni.jsp");
			requestDispatcher4.forward(request, response);
			break;
		case "dni":
			try {
				String dni = request.getParameter("dni");
				String dniNuevo = request.getParameter("dniEmp");
				mbd.actualizarDni(dni, dniNuevo);
				request.setAttribute("result", "Actualización realizada");
				RequestDispatcher requestDispatcher2 = request.getRequestDispatcher("/Vistas/cambiaDni.jsp");
				requestDispatcher2.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatosNoCorrectosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "cambiaSexo":
			RequestDispatcher requestDispatcher5 = request.getRequestDispatcher("/Vistas/cambiaSexo.jsp");
			requestDispatcher5.forward(request, response);
			break;
		case "sexo":
			try {
				String dni = request.getParameter("dni");
				char sexo = request.getParameter("sexoEmp").charAt(0);
				mbd.actualizarSexo(dni, sexo);
				request.setAttribute("result", "Actualización realizada");
				RequestDispatcher requestDispatcher2 = request.getRequestDispatcher("/Vistas/cambiaSexo.jsp");
				requestDispatcher2.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatosNoCorrectosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "cambiaCateg":
			RequestDispatcher requestDispatcher6 = request.getRequestDispatcher("/Vistas/cambiaCat.jsp");
			requestDispatcher6.forward(request, response);
			break;
		case "categ":
			try {
				String dni = request.getParameter("dni");
				int cat = Integer.parseInt(request.getParameter("catEmp"));
				mbd.actualizarCategoria(dni, cat);
				request.setAttribute("result", "Actualización realizada");
				RequestDispatcher requestDispatcher2 = request.getRequestDispatcher("/Vistas/cambiaCat.jsp");
				requestDispatcher2.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatosNoCorrectosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "cambiaAnyos":
			RequestDispatcher requestDispatcher7 = request.getRequestDispatcher("/Vistas/cambiaAnyos.jsp");
			requestDispatcher7.forward(request, response);
			break;
		case "anyos":
			try {
				String dni = request.getParameter("dni");
				int anyos = Integer.parseInt(request.getParameter("anyosEmp"));
				mbd.actualizarAnyos(dni, anyos);
				request.setAttribute("result", "Actualización realizada");
				RequestDispatcher requestDispatcher2 = request.getRequestDispatcher("/Vistas/cambiaAnyos.jsp");
				requestDispatcher2.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatosNoCorrectosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		}

	}

	// response.getWriter().append("Served at: ").append(request.getContextPath());

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
