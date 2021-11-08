package com.companyname.springapp.business.entities;

import javax.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {

	@Id
	
	@Column(name = "nombre")
	public String nombre;
	
	@Column(name = "dni")
	public String dni;
	
	@Column(name = "sexo")
	public String sexo;
	
	@Column(name = "categoria")
	private int categoria;
	
	@Column(name = "anyos")
	public int anyos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getAnyos() {
		return anyos;
	}

	public void setAnyos(int anyos) {
		this.anyos = anyos;
	}

	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", dni=" + dni + ", sexo=" + sexo + ", categoria=" + categoria
				+ ", anyos=" + anyos + "]";
	}
	
	

}
