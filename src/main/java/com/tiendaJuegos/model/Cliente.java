package com.tiendaJuegos.model;

public class Cliente {

	private Long idCliente;
	private String nombre;
	private Integer edad;
	
	private Integer comprasRealizadas;
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public Integer getComprasRealizadas() {
		return comprasRealizadas;
	}
	public void setComprasRealizadas(Integer comprasRealizadas) {
		this.comprasRealizadas = comprasRealizadas;
	}
}
