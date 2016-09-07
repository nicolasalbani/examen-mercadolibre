package com.mercadolibre.examen.service;

import java.util.List;
import java.util.Scanner;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.mercadolibre.examen.dao.RespuestaDAO;
import com.mercadolibre.examen.domain.Ocurrencias;
import com.mercadolibre.examen.domain.Respuesta;
import com.mercadolibre.examen.weather.CalculadorClima;
import com.mercadolibre.examen.weather.EstadoClima;

@Path("/clima")
public class ClimaService {
	
	static final String INVALID_INPUT = "Los dias validos van desde " +CalculadorClima.INIT_DAYS+ " hasta " +CalculadorClima.MAX_DAYS;
	static final int INVALID_INPUT_CODE = -1;

	@GET
	@Produces("application/json")
	public Respuesta printMessage(@QueryParam("dia") String dia) {
		Respuesta respuesta = new Respuesta(INVALID_INPUT_CODE, INVALID_INPUT);

		if(isInteger(dia)){
			int diaInt = Integer.parseInt(dia);
			if(diaInt >= CalculadorClima.INIT_DAYS && diaInt <= CalculadorClima.MAX_DAYS){
				respuesta = RespuestaDAO.getInstance().buscarRespuesta(diaInt);
			}
		}

		return respuesta;
	}

	@GET
	@Path("/ocurrencias")
	@Produces("application/json")
	public Ocurrencias printMessage() {
		Ocurrencias ocurrencias = new Ocurrencias();
		ocurrencias.setPeriodosCondicionesOptimas(RespuestaDAO.getInstance().obtenerOcurrencias(EstadoClima.CONDICIONES_OPTIMAS.getEstado()).size());
		ocurrencias.setPeriodosLluvia(RespuestaDAO.getInstance().obtenerOcurrencias(EstadoClima.LLUVIA.getEstado()).size());
		ocurrencias.setPeriodosSequia(RespuestaDAO.getInstance().obtenerOcurrencias(EstadoClima.SEQUIA.getEstado()).size());

		List<Respuesta> lluviasPicoList = RespuestaDAO.getInstance().obtenerOcurrencias(EstadoClima.LLUVIA_PICO.getEstado());
		int[] lluviasPicoArray = new int[lluviasPicoList.size()];
		for(int i=0;i<lluviasPicoList.size();i++){
			lluviasPicoArray[i] = lluviasPicoList.get(i).getDia();
		}
		ocurrencias.setPicosMaximos(lluviasPicoArray);
		return ocurrencias;
	}

	/**
	 * Valida si el string es un integer valido
	 * @param s
	 * @return
	 */
	public static boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		return true;
	}
}