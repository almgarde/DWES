package dwes.nomina.interfaceService;

import java.util.List;
import java.util.Optional;

import dwes.nomina.modelo.Empleado;

public interface IEmpleadoService {

	public List<Empleado> listar();

	public Optional<Empleado> listarPorDNI(String DNI);

	public int guardar(Empleado e);

	public void eliminar(String DNI);
}
