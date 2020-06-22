package com.tiendaJuegos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonObject;
import com.tiendaJuegos.model.Cliente;
import com.tiendaJuegos.model.Compra;
import com.tiendaJuegos.model.Juego;
import com.tiendaJuegos.service.TiendaService;

@Controller
public class TiendaController {

	@Autowired
	private TiendaService tiendaService;
	
	@GetMapping("/juegos")
	public String juegos(Model model) {
		List<Juego> juegos = tiendaService.getJuegos();
		model.addAttribute("juegos", juegos);
		
		List<Cliente> clientes = tiendaService.getClientes();
		
		model.addAttribute("clientes", clientes);

		return "inicio";
	}
	
	@RequestMapping(value = "/alquilarJuego", method = RequestMethod.POST)
	public String alquilarJuego(Model model,
			@RequestParam(name = "idJuego") Long idJuego,
			@RequestParam(name = "idCliente") Long idCliente) {
		JsonObject objCompra = new JsonObject();
		objCompra.addProperty("idJuego", idJuego.toString());
		objCompra.addProperty("idCliente", idCliente.toString());
		Compra compra = tiendaService.alquilarJuego(objCompra.toString());
		return "inicio";
	}
}
