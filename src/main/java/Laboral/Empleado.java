package Laboral;

public class Empleado extends Persona {

    private int categoria;
    public int anyos;

    public Empleado(int categoria, int anyos, String nombre, String dni, char sexo) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);

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

    public Empleado(String nombre, String dni, char sexo) {
        super(nombre, dni, sexo);

        categoria = 1;
        anyos = 0;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        if (categoria < 1 && categoria > 10) {
            System.out.println("La categoría debe estar comprendida entre 1 y 10 años");
        } else {
            this.categoria = categoria;
        }

    }

    public void incrAnyo() {
        anyos++;
    }

    @Override
    public void imprime() {
        System.out.println("Nombre: " + nombre + ", DNI: " + dni + ", Sexo: " + sexo + ", Categoría: " + categoria + ", Años: " + anyos);
    }

}
