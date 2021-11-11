package dwes.nomina.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dwes.nomina.modelo.Empleado;

@Repository
public interface IEmpleado extends CrudRepository<Empleado, String> {

}
