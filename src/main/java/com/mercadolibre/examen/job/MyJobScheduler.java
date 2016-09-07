package com.mercadolibre.examen.job;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.mercadolibre.examen.dao.RespuestaDAO;
import com.mercadolibre.examen.domain.Respuesta;
import com.mercadolibre.examen.weather.CalculadorClima;

public class MyJobScheduler implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent)
	{
		// Asumimos que los planetas comienzan a girar Enero 1 1970
		int daysSinceEpoch = Days.daysBetween(new DateTime(0), new DateTime()).getDays();
		
		// Creamos una instancia del calculador de clima
		CalculadorClima calculadorClima = new CalculadorClima();
		
		// Calculando el clima para los proximos 10 anos
		List<Respuesta> respuestaList = new ArrayList<Respuesta>();
		for(int dia=CalculadorClima.INIT_DAYS;dia<CalculadorClima.MAX_DAYS;dia++){
			respuestaList.add(new Respuesta(dia, calculadorClima.obtenerEstadoClima(dia+daysSinceEpoch).getEstado()));
		}
		
		// Persistir clima para los proximos 10 anos
		RespuestaDAO.getInstance().persistirListado(respuestaList);
		
//		try {
//			// Obtenemos una instancia de scheduler de la factory
//			Scheduler scheduler;
//			scheduler = StdSchedulerFactory.getDefaultScheduler();
//			
//			// Iniciamos el scheduler
//			scheduler.start();
//			
//			// Definimos el job y lo asociamos con la clase ClimaJob
//			JobDetail job = newJob(ClimaJob.class)
//					.withIdentity("job1", "group1")
//					.build();
//			
//			// Disparamos el job ahora y le indicamos que corra cada 24 horas
//			Trigger trigger = newTrigger()
//					.withIdentity("trigger1", "group1")
//					.startNow()
//					.withSchedule(simpleSchedule()
//							.withIntervalInHours(24)
//							.repeatForever())
//					.build();
//			
//			// Indicamos a Quartz que corra el job usando nuestro trigger
//			scheduler.scheduleJob(job, trigger);
//		} catch (SchedulerException e) {
//			// DoNothing
//		}

	}
}
