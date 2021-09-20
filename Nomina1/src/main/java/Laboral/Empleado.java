package Laboral;

public class Empleado extends Persona {

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
    public Empleado(int categoria, int anyos, String nombre, String dni, char sexo) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);

        //Condiciones de integridad del constructor
        if (categoria < 1 || categoria > 10) {
            throw new DatosNoCorrectosException("Datos introducidos no correctos");
        } else {
            this.categoria = categoria;
        }

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
     */
    public void setCategoria(int categoria) {

        // Condiciones de integridad
        if (categoria < 1 && categoria > 10) {
            System.out.println("La categoría debe estar comprendida entre 1 y 10 años");
        } else {
            this.categoria = categoria;
        }

    }

    /*
    Incrementa en 1 el número de años trabajados
     */
    public void incrAnyo() {
        anyos++;
    }

    /**
     * Impresión de atributos
     */
    @Override
    public void imprime() {
        System.out.println("Nombre: " + nombre + ", DNI: " + dni + ", Sexo: " + sexo + ", Categoría: " + categoria + ", Años: " + anyos);
    }

}
