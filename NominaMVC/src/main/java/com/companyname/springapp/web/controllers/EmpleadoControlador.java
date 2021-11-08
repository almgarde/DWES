package com.companyname.springapp.web.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.companyname.springapp.business.conexion.DBUtils;
import com.companyname.springapp.business.entities.Nomina;

public class EmpleadoControlador {

	private DBUtils dbu = new DBUtils();
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dbu.conexion());

	Nomina n = new Nomina();

	ModelAndView mav = new ModelAndView();

	@RequestMapping(value = "/mostrarDatos.htm", method = RequestMethod.GET)
	public ModelAndView mostrar() {

		String query = "select * from empleados";
		List datos = this.jdbcTemplate.queryForList(query);
		mav.addObject("lista", datos);
		mav.setViewName("mostrarDatos");
		return mav;

	}
	
//	@RequestMapping(value="/sueldoDni.htm")
//    public ModelAndView mostrarSalario() {
//
//    	mav.setViewName("sueldoDni");
//		return mav;
//    }
//    
//    @RequestMapping(value="/editar.htm")
//    public ModelAndView editar() {
//
//    	mav.setViewName("ActualizarEmp");
//		return mav;
//    }
//    
//    @RequestMapping(value="/cambiaNombre.htm")
//    public ModelAndView cambiaNombre() {
//
//    	mav.setViewName("cambiaNombre");
//		return mav;
//    }
//    
//    @RequestMapping(value="/cambiaDni.htm")
//    public ModelAndView cambiaDni() {
//
//    	mav.setViewName("cambiaDni");
//		return mav;
//    }
//    
//    @RequestMapping(value="/cambiaSexo.htm")
//    public ModelAndView cambiaSexo() {
//
//    	mav.setViewName("cambiaSexo");
//		return mav;
//    }
//    
//    @RequestMapping(value="/cambiaCateg.htm")
//    public ModelAndView cambiaCateg() {
//
//    	mav.setViewName("cambiaCat");
//		return mav;
//    }
//    
//    @RequestMapping(value="/cambiaAnyos.htm")
//    public ModelAndView cambiaAnyos() {
//
//    	mav.setViewName("cambiaAnyos");
//		return mav;
//    }
//    
//    
//    
//    
//    
//    
//    @RequestMapping(value="/buscarDni.htm")
//    public ModelAndView buscarDni() {
//
//    	mav.setViewName("buscarDni");
//		return mav;
//    }

}
