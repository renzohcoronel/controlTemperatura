package org.recoit.controlSensoresTemperatura;

import java.util.Date;

public class SensorTemperatura {

	private Integer id;
	private String description;
	private Double valor;
	private Double valorMin;
	private Date timestamp;
	
	public SensorTemperatura(){
		
	}

	

	public SensorTemperatura(Integer id, String description, Double valor, Double valorMin) {
		this();
		this.id = id;
		this.description = description;
		this.valor = valor;
		this.valorMin = valorMin;

	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}


	public Double getValorMin() {
		return valorMin;
	}

	public void setValorMin(Double valorMin) {
		this.valorMin = valorMin;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "SensorTemperatura [id=" + id + ", description=" + description + ", valor=" + valor + ", valorMin="
				+ valorMin + ", timestamp=" + timestamp + "]";
	}

	


	
	
	
}
