package org.recoit.controlSensoresTemperatura;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class SensoresController {
	
	Serial serial;
		
	List<SensorTemperatura> sensores;
	
	public SensoresController() {
		serial = new Serial();
		
		SensorTemperatura s1 = new SensorTemperatura(1, "Heladera 1", Double.valueOf(15), Double.valueOf(19));
		SensorTemperatura s2 = new SensorTemperatura(2, "Heladera 2", Double.valueOf(16), Double.valueOf(13));
		SensorTemperatura s3 = new SensorTemperatura(3, "Heladera 3", Double.valueOf(12), Double.valueOf(14));
		sensores = new ArrayList<>();
		sensores.add(s1);
		sensores.add(s2);
		sensores.add(s3);
		
		(new Thread(serial)).start();
	}
	
	@Scheduled(fixedRate = 1000)
	public void readPort(){		
		
		String value  = serial.readPort();
		
		

	}

	public SensorTemperatura getSensor(Integer id) {
		for (SensorTemperatura sensorTemperatura : sensores) {
			if(sensorTemperatura.getId().equals(id))
				return sensorTemperatura;
		}
		return null;
	}
	
	public void setSensorModificar(SensorTemperatura sensor) {
		for (SensorTemperatura sensorTemperatura : sensores) {
			if(sensorTemperatura.getId().equals(sensor.getId())){
					sensorTemperatura.setValorSet(sensor.getValorSet());
					sensorTemperatura.setDescripcion(sensor.getDescripcion());
			}
		
		}
	}

	public List<SensorTemperatura> getSensores() {
		return sensores;
	}	
	
	

}
