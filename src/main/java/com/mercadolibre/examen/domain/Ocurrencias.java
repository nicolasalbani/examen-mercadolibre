package com.mercadolibre.examen.domain;

import java.util.Arrays;

public class Ocurrencias {
	
	private int periodosSequia;
	private int periodosLluvia;
	private int[] picosMaximos;
	private int periodosCondicionesOptimas;
	
	public Ocurrencias() { }
	
	public Ocurrencias(int periodosSequia, int periodosLluvia, int[] picosMaximos, int periodosCondicionesOptimas) {
		this.periodosSequia = periodosSequia;
		this.periodosLluvia = periodosLluvia;
		this.picosMaximos = picosMaximos;
		this.periodosCondicionesOptimas = periodosCondicionesOptimas;
	}

	public int getPeriodosSequia() {
		return periodosSequia;
	}
	public void setPeriodosSequia(int periodosSequia) {
		this.periodosSequia = periodosSequia;
	}
	public int getPeriodosLluvia() {
		return periodosLluvia;
	}
	public void setPeriodosLluvia(int periodosLluvia) {
		this.periodosLluvia = periodosLluvia;
	}
	public int[] getPicosMaximos() {
		return picosMaximos;
	}
	public void setPicosMaximos(int[] picosMaximos) {
		this.picosMaximos = picosMaximos;
	}
	public int getPeriodosCondicionesOptimas() {
		return periodosCondicionesOptimas;
	}
	public void setPeriodosCondicionesOptimas(int periodosCondicionesOptimas) {
		this.periodosCondicionesOptimas = periodosCondicionesOptimas;
	}

	@Override
	public String toString() {
		return "Ocurrencias [periodosSequia=" + periodosSequia + ", periodosLluvia=" + periodosLluvia
				+ ", picosMaximos=" + Arrays.toString(picosMaximos) + ", periodosCondicionesOptimas="
				+ periodosCondicionesOptimas + "]";
	}
}
