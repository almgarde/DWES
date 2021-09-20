package Laboral;

public class CalculaNominas {

    /**
     * Método principal
     *
     * @param args
     */
    public static void main(String[] args) {

        try {

            //Creación de objetos empleados y nómina
            Empleado emp1 = new Empleado(4, 7, "James Cosling", "32000032G", 'M');

            Empleado emp2 = new Empleado("Ada Lovelace", "32000031R", 'F');

            Nomina nom = new Nomina();

            // Impresión de los atributos y sueldo de los empleados
            escribe(emp1, emp2, nom);

            // Incremento de años para el empleado 2
            emp2.incrAnyo();

            // Establecimiento de la categoría del empleado 1
            emp1.setCategoria(9);

            // Impresión de los atributos y sueldo de los empleados
            escribe(emp1, emp2, nom);
        } catch (DatosNoCorrectosException e) {
            System.out.println("Datos no correctos");
        }

    }

    // Método de impresión de atributos y sueldo de los empleados
    private static void escribe(Empleado emp1, Empleado emp2, Nomina nom) {
        emp1.imprime();
        System.out.println("Sueldo del empleado 1: " + nom.sueldo(emp1));
        emp2.imprime();
        System.out.println("Sueldo del empleado 2: " + nom.sueldo(emp2));
    }
}
