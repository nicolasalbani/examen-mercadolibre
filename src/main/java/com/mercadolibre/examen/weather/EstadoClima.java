package com.mercadolibre.examen.weather;

public enum EstadoClima {
	LLUVIA("lluvia"),
	LLUVIA_PICO("lluvia_pico"),
	SEQUIA("sequia"),
	CONDICIONES_OPTIMAS("condiciones_optimas"),
	ESTANDAR("estandar");

	private String estado;

	private EstadoClima(String estado){
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
