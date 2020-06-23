package com.tiendaJuegos.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tiendaJuegos.model.Cliente;
import com.tiendaJuegos.model.Compra;
import com.tiendaJuegos.model.Juego;

@Component
public class TiendaService {

	static final String M_POST = "POST";
	static final String M_GET = "GET";
	static final String M_PUT = "PUT";

	static final String CONTENT_TYPE_TEXT = "text/plain";
	static final String CONTENT_TYPE_JSON = "application/json";
	
	static final String urlServiceMocks = "https://5eefde7ead6d710016179d88.mockapi.io/";

	public List<Juego> getJuegos() {
		String resutl = methodGet(urlServiceMocks + "juegos");
		Gson gson = new Gson();
		List<Juego> respuesta = gson.fromJson(resutl, new TypeToken<List<Juego>>(){}.getType());
		return respuesta;
	}
	
	public Juego getJuegoById(String idJuego) {
		String resutl = methodGet(urlServiceMocks + "juegos/" + idJuego);
		Gson gson = new Gson();
		Juego respuesta = gson.fromJson(resutl, new TypeToken<Juego>(){}.getType());
		return respuesta;
	}
	
	public List<Cliente> getClientes() {
		String resutl = methodGet(urlServiceMocks + "clientes");
		Gson gson = new Gson();
		List<Cliente> respuesta = gson.fromJson(resutl, new TypeToken<List<Cliente>>(){}.getType());
		return respuesta;
	}
	
	public Cliente getClienteById(String idCliente) {
		String resutl = methodGet(urlServiceMocks + "clientes/" + idCliente);
		Gson gson = new Gson();
		Cliente respuesta = gson.fromJson(resutl, new TypeToken<Cliente>(){}.getType());
		return respuesta;
	}
	
	public List<Compra> getCompras() {
		String resutl = methodGet(urlServiceMocks + "compras");
		Gson gson = new Gson();
		List<Compra> respuesta = gson.fromJson(resutl, new TypeToken<List<Compra>>(){}.getType());
		for (Compra compra : respuesta) {
			Juego juego = getJuegoById(compra.getIdJuego().toString());
			compra.setTituloJuego(juego.getTitulo());
			compra.setPrecioJuego(juego.getPrecio());
			Cliente cliente = getClienteById(compra.getIdCliente().toString());
			compra.setNombreCliente(cliente.getNombre());
		}
		return respuesta;
	}
	
	public Compra alquilarJuego(String json) {
		String resutl = methodPost(urlServiceMocks + "compras", json);
		Gson gson = new Gson();
		Compra respuesta = gson.fromJson(resutl, new TypeToken<Compra>(){}.getType());
		return respuesta;
	}
	
	
	public String methodGet(String urlr) {
		String result = "";
		try {
			URL url = new URL(urlr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(M_GET);
			conn.setRequestProperty("Content-Type", CONTENT_TYPE_TEXT);
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				result += inputLine;
			}
			in.close();
			return result;
		} catch (MalformedURLException e) {
			return null;

		} catch (IOException e) {
			return null;
		}
	}

	public String methodPost(String urlr, String json) {
		try {
			URL url = new URL(urlr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(M_POST);
			conn.setRequestProperty("Content-Type", CONTENT_TYPE_JSON);
			OutputStream os = conn.getOutputStream();
			os.write(json.getBytes());
			os.flush();
			if (conn.getResponseCode() != 200 & conn.getResponseCode() != 201) {
				return null;
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			String result = "";
			while ((output = br.readLine()) != null) {
				result = output;
			}
			conn.disconnect();
			return result;
		} catch (MalformedURLException e) {
			return null;

		} catch (IOException e) {
			return null;
		}
	}
}
