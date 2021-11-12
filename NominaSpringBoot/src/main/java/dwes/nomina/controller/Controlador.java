package dwes.nomina.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dwes.nomina.interfaceService.IEmpleadoService;
import dwes.nomina.modelo.Empleado;
import dwes.nomina.modelo.Nomina;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping
public class Controlador {

	@Autowired
	private IEmpleadoService service;

	@GetMapping("/listar")
	public String listar(Model model) {
		List<Empleado> empleados = service.listar();
		model.addAttribute("empleados", empleados);
		return "index";
	}

	@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("empleado", new Empleado());
		model.addAttribute("nomina", new Nomina());
		return "form";
	}

	@PostMapping("/guardar")
	public String guardar(Empleado e, Nomina n, Model model) {
		service.guardar(e);
		return "redirect:/listar";
	}

	@GetMapping("/editar/{dni}")
	public String editar(@PathVariable String dni, Model model) {
		Optional<Empleado> empleado = service.listarPorDNI(dni);
		model.addAttribute("empleado", empleado);
		return "form";
	}

	@GetMapping("/eliminar/{dni}")
	public String eliminar(@PathVariable String dni, Model model) {
		service.eliminar(dni);
		return "redirect:/listar";
	}
}
