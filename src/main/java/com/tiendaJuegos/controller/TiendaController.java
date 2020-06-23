package com.tiendaJuegos.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.tiendaJuegos.model.Cliente;
import com.tiendaJuegos.model.Compra;
import com.tiendaJuegos.model.Juego;
import com.tiendaJuegos.service.TiendaService;

@Controller
public class TiendaController {

	@Autowired
	private TiendaService tiendaService;

	@GetMapping({"/index","/","home","/inicio","/compras"})
	public String compras(Model model, @RequestParam(value = "m", required = false) String mensajeEncriptado) throws Exception {
		model.addAttribute("mensajeRespuesta", desencriptar(mensajeEncriptado));
		List<Compra> compras = tiendaService.getCompras();
		model.addAttribute("clienteRecurrente", clienteRecurrente());
		model.addAttribute("juegoRecurrente", juegoRecurrente());
		model.addAttribute("juegoMenosRentado", juegoMenosRentado());
		model.addAttribute("valorTotal", valorTotal());
		model.addAttribute("compras", compras);
		return "inicio";
	}
	
	private String clienteRecurrente() {
		List<Compra> compras = tiendaService.getCompras();
		List<Cliente> clientes = tiendaService.getClientes();
		int maxCompras = 0;
		String nombreCliente = "";
		for (Cliente cliente : clientes) {
			int cont = 0;
			for (Compra compra : compras) {
				if (compra.getIdCliente() == cliente.getIdCliente()) {
					cont++;
				}
			}
			if (maxCompras < cont) {
				nombreCliente = cliente.getNombre();
				maxCompras = cont;
			}
		}
		return nombreCliente;
	}	
	private String juegoRecurrente() {
		List<Compra> compras = tiendaService.getCompras();
		List<Juego> juegos = tiendaService.getJuegos();
		int maxCompras = 0;
		String tituloJuego = "";
		for (Juego juego : juegos) {
			int cont = 0;
			for (Compra compra : compras) {
				if (compra.getIdJuego() == juego.getIdJuego()) {
					cont++;
				}
			}
			if (maxCompras < cont) {
				tituloJuego = juego.getTitulo();
				maxCompras = cont;
			}
		}
		return tituloJuego;
	}	
	private String juegoMenosRentado() {
		List<Compra> compras = tiendaService.getCompras();
		List<Juego> juegos = tiendaService.getJuegos();
		int minCompras = 999999999;
		String tituloJuego = "";
		for (Juego juego : juegos) {
			int cont = 0;
			for (Compra compra : compras) {
				if (compra.getIdJuego() == juego.getIdJuego()) {
					cont++;
				}
			}
			if (minCompras > cont) {
				tituloJuego = juego.getTitulo();
				minCompras = cont;
			}
		}
		return tituloJuego;
	}
	private int valorTotal() {
		List<Compra> compras = tiendaService.getCompras();
		int suma = 0;
		for (Compra compra : compras) {
			suma += compra.getPrecioJuego();
		}
		return suma;
	}
	
	@GetMapping("/alquiler")
	public String alquiler(Model model) {
		List<Juego> juegos = tiendaService.getJuegos();
		model.addAttribute("juegos", juegos);
		List<Cliente> clientes = tiendaService.getClientes();
		model.addAttribute("clientes", clientes);
		return "alquiler";
	}
	
	@RequestMapping(value = "/alquilarJuego", method = RequestMethod.POST)
	public ModelAndView alquilarJuego(Model model,
			@RequestParam(name = "idJuego") Long idJuego,
			@RequestParam(name = "idCliente") Long idCliente,
			@RequestParam(name = "dias") Long dias) {
		
		Long fechaCompra = new Date().getTime();
		Long diasMS = TimeUnit.MILLISECONDS.convert(dias, TimeUnit.DAYS);
		Long fechaEntrega = fechaCompra + diasMS;
		
		JsonObject objCompra = new JsonObject();
		objCompra.addProperty("idJuego", idJuego.toString());
		objCompra.addProperty("idCliente", idCliente.toString());
		objCompra.addProperty("fechaCompra", fechaCompra);
		objCompra.addProperty("fechaEntrega", fechaEntrega);
		Compra compra = tiendaService.alquilarJuego(objCompra.toString());
		return new ModelAndView("redirect:" + "/compras", "m", encriptar("Compra realizada exitosamente!!"));
	}
	
	public static String encriptar(String texto) {
		byte[] encoded = Base64.encodeBase64(texto.getBytes());
		String encodedString = new String(encoded);
		return encodedString;
	}
	
	public static String desencriptar(String textoEncriptado) throws Exception {
		if (textoEncriptado == null)
			return "";
		byte[] decoded = Base64.decodeBase64(textoEncriptado);
		String decodedString = new String(decoded);
		return decodedString;
	}
}
