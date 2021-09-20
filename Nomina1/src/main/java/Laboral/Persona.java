package Laboral;

public class Persona {

    /**
     * Propiedad: Nombre del empleado
     */
    public String nombre;

    /**
     * Propiedad: DNI del empleado
     */
    public String dni;

    /**
     * Propiedad: Sexo del empleado
     */
    public char sexo;

    /**
     * Constructor 1
     *
     * @param nombre
     * @param dni
     * @param sexo
     */
    public Persona(String nombre, String dni, char sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    /**
     * Constructor 2
     *
     * @param nombre
     * @param sexo
     */
    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    /**
     * Método Set del atributo DNI
     *
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Impresión de atributos
     */
    public void imprime() {
        System.out.println("Nombre: " + nombre + ", DNI: " + dni);
    }

}
