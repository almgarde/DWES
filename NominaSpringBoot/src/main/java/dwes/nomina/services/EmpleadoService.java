package dwes.nomina.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dwes.nomina.interfaceService.IEmpleadoService;
import dwes.nomina.interfaces.IEmpleado;
import dwes.nomina.modelo.Empleado;
import dwes.nomina.modelo.Nomina;

@Service
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	private IEmpleado data;

	@Override
	public List<Empleado> listar() {

		return (List<Empleado>) data.findAll();
	}

	@Override
	public Optional<Empleado> listarPorDNI(String DNI) {

		return data.findById(DNI);
	}

	@Override
	public int guardar(Empleado e) {
		int res = 0;
		Empleado emp = data.save(e);
		if (!emp.equals(null)) {
			res = 1;
		}
		return res;
	}

	@Override
	public void eliminar(String dni) {
		data.deleteById(dni);

	}

}
