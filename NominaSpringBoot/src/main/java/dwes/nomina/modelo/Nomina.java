package dwes.nomina.modelo;

import javax.persistence.*;

@Entity
@Table(name = "nominas")
public class Nomina {

	private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
			230000 };
	Empleado emp = new Empleado();

	@Id
	@Column(name = "DNI")
	public String dni;

	@Column(name = "Sueldo")
	public int sueldo;

	public Nomina() {
		this.dni = emp.getDni();
		this.sueldo = calculaSueldo(emp);
	}

	public int calculaSueldo(Empleado e) {
		int sueldo = SUELDO_BASE[e.getCategoria() - 1] + 5000 * e.anyos;
		return sueldo;

	};

}
