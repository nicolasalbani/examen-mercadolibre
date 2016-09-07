package com.mercadolibre.examen.weather;

import org.junit.Test;

import junit.framework.TestCase;

public class CalculadorClimaTest extends TestCase {

	@Test
	public void testObtenerEstadoClimaEstandar(){
		CalculadorClima calc = new CalculadorClima();

		// Probando estados estandar
		assertEquals(EstadoClima.ESTANDAR, calc.obtenerEstadoClima(2465));
		assertEquals(EstadoClima.ESTANDAR, calc.obtenerEstadoClima(2662));
		assertEquals(EstadoClima.ESTANDAR, calc.obtenerEstadoClima(2818));
		assertEquals(EstadoClima.ESTANDAR, calc.obtenerEstadoClima(3412));
		assertEquals(EstadoClima.ESTANDAR, calc.obtenerEstadoClima(3621));
		assertEquals(EstadoClima.ESTANDAR, calc.obtenerEstadoClima(4152));
		assertEquals(EstadoClima.ESTANDAR, calc.obtenerEstadoClima(5343));
		assertEquals(EstadoClima.ESTANDAR, calc.obtenerEstadoClima(6528));
		assertEquals(EstadoClima.ESTANDAR, calc.obtenerEstadoClima(6981));
		assertEquals(EstadoClima.ESTANDAR, calc.obtenerEstadoClima(7072));
		assertEquals(EstadoClima.ESTANDAR, calc.obtenerEstadoClima(7338));
	}

	@Test
	public void testObtenerEstadoClimaSequia(){
		CalculadorClima calc = new CalculadorClima();

		// Probando estados de sequia
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(0));
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(90));
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(180));
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(360));
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(450));
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(540));
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(720));
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(900));
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(990));
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(1080));
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(1260));
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(1440));
		assertEquals(EstadoClima.SEQUIA, calc.obtenerEstadoClima(1620));
	}

	@Test
	public void testObtenerEstadoClimaLluvia(){
		CalculadorClima calc = new CalculadorClima();

		// Probando estados de lluvia
		assertEquals(EstadoClima.LLUVIA, calc.obtenerEstadoClima(23));
		assertEquals(EstadoClima.LLUVIA, calc.obtenerEstadoClima(30));
		assertEquals(EstadoClima.LLUVIA, calc.obtenerEstadoClima(68));
		assertEquals(EstadoClima.LLUVIA, calc.obtenerEstadoClima(112));
		assertEquals(EstadoClima.LLUVIA, calc.obtenerEstadoClima(151));
		assertEquals(EstadoClima.LLUVIA, calc.obtenerEstadoClima(157));
		assertEquals(EstadoClima.LLUVIA, calc.obtenerEstadoClima(203));
		assertEquals(EstadoClima.LLUVIA, calc.obtenerEstadoClima(210));
		assertEquals(EstadoClima.LLUVIA, calc.obtenerEstadoClima(248));
		assertEquals(EstadoClima.LLUVIA, calc.obtenerEstadoClima(337));
		assertEquals(EstadoClima.LLUVIA, calc.obtenerEstadoClima(283));
	}

	@Test
	public void testObtenerEstadoClimaLluviaPico(){
		CalculadorClima calc = new CalculadorClima();

		// Probando estados de lluvia maxima
		assertEquals(EstadoClima.LLUVIA_PICO, calc.obtenerEstadoClima(89));
		assertEquals(EstadoClima.LLUVIA_PICO, calc.obtenerEstadoClima(91));
		assertEquals(EstadoClima.LLUVIA_PICO, calc.obtenerEstadoClima(269));
		assertEquals(EstadoClima.LLUVIA_PICO, calc.obtenerEstadoClima(271));
		assertEquals(EstadoClima.LLUVIA_PICO, calc.obtenerEstadoClima(449));
		assertEquals(EstadoClima.LLUVIA_PICO, calc.obtenerEstadoClima(451));
		assertEquals(EstadoClima.LLUVIA_PICO, calc.obtenerEstadoClima(629));
		assertEquals(EstadoClima.LLUVIA_PICO, calc.obtenerEstadoClima(809));
	}

	@Test
	public void testObtenerEstadoClimaCondicionesOptimas(){
		CalculadorClima calc = new CalculadorClima();

		// Probando estados de condiciones optimas
		assertEquals(EstadoClima.CONDICIONES_OPTIMAS, calc.obtenerEstadoClima(18));
		assertEquals(EstadoClima.CONDICIONES_OPTIMAS, calc.obtenerEstadoClima(162));
		assertEquals(EstadoClima.CONDICIONES_OPTIMAS, calc.obtenerEstadoClima(342));
		assertEquals(EstadoClima.CONDICIONES_OPTIMAS, calc.obtenerEstadoClima(522));
		assertEquals(EstadoClima.CONDICIONES_OPTIMAS, calc.obtenerEstadoClima(702));
		assertEquals(EstadoClima.CONDICIONES_OPTIMAS, calc.obtenerEstadoClima(882));
		assertEquals(EstadoClima.CONDICIONES_OPTIMAS, calc.obtenerEstadoClima(918));
		assertEquals(EstadoClima.CONDICIONES_OPTIMAS, calc.obtenerEstadoClima(1242));
	}
}
