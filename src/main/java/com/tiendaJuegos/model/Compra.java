package com.tiendaJuegos.model;

import java.util.Date;

public class Compra {

	private Long idCliente;
	private Long idJuego;
	private String fechaCompra;
	private String fechaEntrega;
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getIdJuego() {
		return idJuego;
	}
	public void setIdJuego(Long idJuego) {
		this.idJuego = idJuego;
	}
	public String getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
}
