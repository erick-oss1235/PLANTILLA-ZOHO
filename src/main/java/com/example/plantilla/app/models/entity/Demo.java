package com.example.plantilla.app.models.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Demo {

	@NotNull
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty(message = "apellido no puede ser vacio")
	private String apellido;
	
	
	
	public Demo() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
}
