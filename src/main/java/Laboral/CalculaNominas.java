package Laboral;

public class CalculaNominas {

    public static void main(String[] args) {

        Empleado emp1 = null;
        Empleado emp2 = null;
        try {
            emp1 = new Empleado(4, 7, "James Cosling", "32000032G", 'M');

            emp2 = new Empleado("Ada Lovelace", "32000031R", 'F');
        } catch (DatosNoCorrectosException e) {
            System.out.println("Datos no correctos");
        }

        Nomina nom = new Nomina();

        escribe(emp1, emp2, nom);

        emp2.incrAnyo();

        emp1.setCategoria(9);

        escribe(emp1, emp2, nom);

    }

    private static void escribe(Empleado emp1, Empleado emp2, Nomina nom) {
        emp1.imprime();
        System.out.println("Sueldo del empleado 1: " + nom.sueldo(emp1));
        emp2.imprime();
        System.out.println("Sueldo del empleado 2: " + nom.sueldo(emp2));
    }
}
