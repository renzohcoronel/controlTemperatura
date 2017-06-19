package org.recoit.controlSensoresTemperatura;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class SensoresController {
	
	Serial serial;
		
	List<SensorTemperatura> sensores;
	
	public SensoresController() {
		serial = new Serial();
		(new Thread(serial)).start();
	}
	
	@Scheduled(fixedRate = 1000)
	public void readPort(){		
		
		/*
		 * Falta implementar como viene el mensaje de arduino
		 * */
		String value  = serial.readPort();

	}

	public SensorTemperatura getSensor(Integer id) {
		for (SensorTemperatura sensorTemperatura : sensores) {
			if(sensorTemperatura.getId().equals(id))
				return sensorTemperatura;
		}
		return null;
	}
	
	public void setSensorMinTemp(Integer id, Double valor) {
		for (SensorTemperatura sensorTemperatura : sensores) {
			if(sensorTemperatura.getId().equals(id)){
				if(serial.write(id+","+valor) == true )
					sensorTemperatura.setValorMin(valor);			 
			}
		
		}
	}	
	
	

}
