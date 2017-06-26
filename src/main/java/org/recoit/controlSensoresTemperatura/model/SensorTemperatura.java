package org.recoit.controlSensoresTemperatura.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SensorTemperatura {

	private Integer id;
	private String descripcion;
	private Double valor;
	private Double valorSet;
	@JsonIgnore
	private Date timestamp;
	
	public SensorTemperatura(){
		this.timestamp = new Date();
	}

	

	public SensorTemperatura(Integer id, String description, Double valor, Double valorSet) {
		this();
		this.id = id;
		this.descripcion = description;
		this.valorSet = valor;
		this.valorSet = valorSet;
		this.timestamp = new Date();

	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String description) {
		this.descripcion = description;
	}

	public Double getValor() {
		return valorSet;
	}

	public void setValor(Double valor) {
		this.valorSet = valor;
	}


	public Double getValorSet() {
		return valorSet;
	}

	public void setValorSet(Double valorSet) {
		this.valorSet = valorSet;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}



	@Override
	public String toString() {
		return "SensorTemperatura [id=" + id + ", descripcion=" + descripcion + ", valor=" + valor + ", valorSet="
				+ valorSet + ", timestamp=" + timestamp + "]";
	}

	
	

	


	
	
	
}
