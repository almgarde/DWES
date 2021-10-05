package Laboral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Empleado extends Persona {

	/**
	 * Logger
	 */
	final static Logger LOGGER = LoggerFactory.getLogger(Empleado.class);

	/**
	 * Categoría del empleado
	 */
	private int categoria;

	/**
	 * Número de años trabajados
	 */
	public int anyos;

	/**
	 * Constructor 1
	 *
	 * @param categoria
	 * @param anyos
	 * @param nombre
	 * @param dni
	 * @param sexo
	 * @throws DatosNoCorrectosException
	 */
	public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {

		super(nombre, dni, sexo);

		// Condiciones de integridad del constructor
		this.setCategoria(categoria);

		if (anyos < 0) {
			throw new DatosNoCorrectosException("Datos introducidos no correctos");
		} else {
			this.anyos = anyos;
		}

	}

	/**
	 * Constructor 2
	 *
	 * @param nombre
	 * @param dni
	 * @param sexo
	 */
	public Empleado(String nombre, String dni, char sexo) {
		super(nombre, dni, sexo);

		categoria = 1;
		anyos = 0;
	}

	/**
	 * Metodo Get del atributo Categoría
	 *
	 * @return categoria
	 */
	public int getCategoria() {
		return categoria;
	}

	/**
	 * Método Set del atributo Categoría
	 *
	 * @param categoria
	 * @throws DatosNoCorrectosException
	 */
	public void setCategoria(int categoria) throws DatosNoCorrectosException {

		// Condiciones de integridad
		if (categoria < 1 || categoria > 10) {
			throw new DatosNoCorrectosException("Datos introducidos no correctos");
		} else {
			this.categoria = categoria;
		}

	}

	/*
	 * Incrementa en 1 el número de años trabajados
	 */
	public void incrAnyo() {
		LOGGER.info("Inicio del método incrAnyo");

		anyos++;

		LOGGER.info("Fin del método incrAnyo");
	}

	/**
	 * Impresión de atributos
	 */
	@Override
	public void imprime() {

		LOGGER.info("Inicio del método imprime");

		System.out.println("Nombre: " + nombre + ", DNI: " + dni + ", Sexo: " + sexo + ", Categoría: " + categoria
				+ ", Años: " + anyos);

		LOGGER.info("Fin del método imprime");
	}

}