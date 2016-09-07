package com.mercadolibre.examen.job;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mercadolibre.examen.dao.RespuestaDAO;
import com.mercadolibre.examen.domain.Respuesta;
import com.mercadolibre.examen.weather.CalculadorClima;

public class ClimaJob implements org.quartz.Job{
	
	/**
	 * Metodo que calcula el clima para los proximos 10 años y lo guarda en la base de datos.
	 * Se calcula la fecha de creacion de la galaxia como la fecha EPOCH.
	 */
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// Asumimos que los planetas comienzan a girar Enero 1 1970
		int daysSinceEpoch = Days.daysBetween(new DateTime(0), new DateTime()).getDays();
		
		// Creamos una instancia del calculador de clima
		CalculadorClima calculadorClima = new CalculadorClima();
		
		// Calculando el clima para los proximos 10 anos
		List<Respuesta> respuestaList = new ArrayList<Respuesta>();
		for(int dia=CalculadorClima.INIT_DAYS;dia<=CalculadorClima.MAX_DAYS;dia++){
			respuestaList.add(new Respuesta(dia, calculadorClima.obtenerEstadoClima(dia+daysSinceEpoch).getEstado()));
		}
		
		// Persistir clima para los proximos 10 anos
		RespuestaDAO.getInstance().persistirListado(respuestaList);
	}
}
