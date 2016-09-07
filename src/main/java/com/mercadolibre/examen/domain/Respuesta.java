package com.mercadolibre.examen.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "respuesta")
@Table(name = "respuesta", catalog = "EXAMEN")
public class Respuesta {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "dia", unique = true, nullable = false)
	private int dia;
	
	@Column(name = "clima", length = 128)
	private String clima;

	public Respuesta() {}

	public Respuesta(int dia, String clima) {
		this.dia = dia;
		this.clima = clima;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	@Override
	public String toString() {
		return "Respuesta [dia=" + dia + ", clima=" + clima + "]";
	}
}
