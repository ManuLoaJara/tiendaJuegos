package com.tiendaJuegos.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Compra {

	private Long idCliente;
	private Long idJuego;
	private String fechaCompra;
	private String fechaEntrega;
	
	private String nombreCliente;
	private String tituloJuego;
	private Integer precioJuego;
	
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
        long fechaLong = Long.parseLong(fechaCompra);
        Date fecha = new Date(fechaLong);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = sdf.format(fecha);
        return fechaString;
//		return fechaCompra;
	}
	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public String getFechaEntrega() {
        long fechaLong = Long.parseLong(fechaEntrega);
        Date fecha = new Date(fechaLong);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = sdf.format(fecha);
        return fechaString;
//		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getTituloJuego() {
		return tituloJuego;
	}
	public void setTituloJuego(String tituloJuego) {
		this.tituloJuego = tituloJuego;
	}
	public Integer getPrecioJuego() {
		return precioJuego;
	}
	public void setPrecioJuego(Integer precioJuego) {
		this.precioJuego = precioJuego;
	}
}
