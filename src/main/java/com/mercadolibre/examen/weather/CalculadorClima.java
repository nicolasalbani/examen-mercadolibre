package com.mercadolibre.examen.weather;

import java.math.BigDecimal;

public class CalculadorClima {

	public static final int MAX_PERIMETRO = 4971;
	public static final double VELOCIDAD_FERENGI_GRAD = 1;
	public static final double VELOCIDAD_VULCANO_GRAD = -5;
	public static final double VELOCIDAD_BETASOIDE_GRAD = 3;

	public static final double VELOCIDAD_FERENGI_RAD = 1 * Math.PI / 180;
	public static final double VELOCIDAD_VULCANO_RAD = -5 * Math.PI / 180;
	public static final double VELOCIDAD_BETASOIDE_RAD = 3 * Math.PI / 180;

	public static final int RADIO_FERENGI = 500;
	public static final int RADIO_VULCANO = 1000;
	public static final int RADIO_BETASOIDE = 2000;

	public static final int PRECISION = 5;

	public static final int INIT_DAYS = 1;
	public static final int MAX_DAYS = 10 * 365;

	private double xFerengi;
	private double yFerengi;
	private double xVulcano;
	private double yVulcano;
	private double xBetasoide;
	private double yBetasoide;

	/**
	 * Calcula las coordenadas X e Y en el plano que representa el sistema solar visto desde arriba
	 * @param dia
	 */
	private void calcularCoordenadas(int dia){
		// Calculamos las coordenadas x-y de cada planeta
		xFerengi = new BigDecimal(RADIO_FERENGI * Math.sin(VELOCIDAD_FERENGI_RAD * dia)).setScale(PRECISION, BigDecimal.ROUND_HALF_UP).doubleValue();
		yFerengi = new BigDecimal(RADIO_FERENGI * Math.cos(VELOCIDAD_FERENGI_RAD * dia)).setScale(PRECISION, BigDecimal.ROUND_HALF_UP).doubleValue();
		xVulcano = new BigDecimal(RADIO_VULCANO * Math.sin(VELOCIDAD_VULCANO_RAD * dia)).setScale(PRECISION, BigDecimal.ROUND_HALF_UP).doubleValue();
		yVulcano = new BigDecimal(RADIO_VULCANO * Math.cos(VELOCIDAD_VULCANO_RAD * dia)).setScale(PRECISION, BigDecimal.ROUND_HALF_UP).doubleValue();
		xBetasoide = new BigDecimal(RADIO_BETASOIDE * Math.sin(VELOCIDAD_BETASOIDE_RAD * dia)).setScale(PRECISION, BigDecimal.ROUND_HALF_UP).doubleValue();
		yBetasoide = new BigDecimal(RADIO_BETASOIDE * Math.cos(VELOCIDAD_BETASOIDE_RAD * dia)).setScale(PRECISION, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * Metodo que calcula el estado del clima dependiendo de la posicion de los planetas
	 * en relacion al sol
	 * @param dia
	 * @return
	 */
	public EstadoClima obtenerEstadoClima(int dia){
		if(isAlineados(dia)){
			if(isAlineadosConSol(dia)){
				return EstadoClima.SEQUIA;
			} else {
				return EstadoClima.CONDICIONES_OPTIMAS;
			}
		} else {
			if(isDentroDeTriangulo(dia)){
				if(calcularPerimetro(dia) > MAX_PERIMETRO){
					return EstadoClima.LLUVIA_PICO;
				} else {
					return EstadoClima.LLUVIA;
				}
			}
		}
		
		return EstadoClima.ESTANDAR;
	}

	/**
	 * Metodo que calcula la alineacion de planetas en base a la pendiente
	 * que forma cada segmento que los une: F-V y V-B donde
	 * F = Ferengi
	 * V = Vulcano
	 * B = Betasoide
	 * @param dia
	 * @return
	 */
	public boolean isAlineados(int dia){
		calcularCoordenadas(dia);

		if((xVulcano == xFerengi) && (xBetasoide == xVulcano)){
			return true;
		}

		double pendiente1 = (int)(22 * (yVulcano - yFerengi) / (xVulcano - xFerengi));
		double pendiente2 = (int)(22 * (yBetasoide - yVulcano) / (xBetasoide - xVulcano));

		return Math.abs((pendiente1)) == Math.abs((pendiente2));
	}

	/**
	 * Metodo que calcula la alineacion de planetas en base al punto
	 * central de las orbitas
	 * @param dia
	 * @return
	 */
	public boolean isAlineadosConSol(int dia){
		double mFerengi = Math.abs((VELOCIDAD_FERENGI_GRAD * dia) % 180);
		double mVulcano = Math.abs((VELOCIDAD_VULCANO_GRAD * dia) % 180);
		double mBetasoide = Math.abs((VELOCIDAD_BETASOIDE_GRAD * dia) % 180);

		return (mFerengi == mVulcano) && (mVulcano == mBetasoide);
	}

	/**
	 * Metodo que calcula el perimetro del triangulo formado por los
	 * tres planetas
	 * @param dia
	 * @return
	 */
	public double calcularPerimetro(int dia){
		calcularCoordenadas(dia);
		
		// Calculamos la suma de las hipotenusas formadas en cada segmento del triangulo
		double hfv = calcularHipotenusa(xFerengi, yFerengi, xVulcano, yVulcano);
		double hvb = calcularHipotenusa(xVulcano, yVulcano, xBetasoide, yBetasoide);
		double hbf = calcularHipotenusa(xBetasoide, yBetasoide, xFerengi, yFerengi);
		
		return hfv + hvb + hbf;
	}
	
	private double calcularHipotenusa(double punto1x, double punto1y, double punto2x, double punto2y){
		double cateto1 = punto1x - punto2x;
		double cateto2 = punto1y - punto2y;
		
		return Math.sqrt(cateto1*cateto1 - cateto2*cateto2);
	}
	
	/**
	 * Metodo que indica si el sol se encuentra dentro del triangulo formado
	 * por los tres planetas. Esto se calcula determinando de que lado de la recta
	 * formada por la union de cada uno de los vertices del triangulo queda el sol.
	 * @param dia
	 * @return
	 */
	public boolean isDentroDeTriangulo(int dia){
		calcularCoordenadas(dia);
		
		boolean dentroDeFV,dentroDeVB,dentroDeBF;
		
		dentroDeFV = calcularPendienteRelativa(0, 0, xFerengi, yFerengi, xVulcano, yVulcano) < 0f;
		dentroDeVB = calcularPendienteRelativa(0, 0, xVulcano, yVulcano, xBetasoide, yBetasoide) < 0f;
		dentroDeBF = calcularPendienteRelativa(0, 0, xBetasoide, yBetasoide, xFerengi, yFerengi) < 0f;
		
		return (dentroDeFV == dentroDeVB) && (dentroDeVB == dentroDeBF);
	}
	
	private double calcularPendienteRelativa(double puntox, double puntoy, double vertice1x, double vertice1y, double vertice2x, double vertice2y){
		return (puntox - vertice2x) * (vertice1y - vertice2y) - (vertice1x - vertice2x) * (puntoy - vertice2y); 
	}
}
