package org.recoit.controlSensoresTemperatura.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.recoit.controlSensoresTemperatura.model.MessageSockect;
import org.recoit.controlSensoresTemperatura.model.SensorTemperatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/")
public class SensoresController {
	
	Serial serial;
	List<SensorTemperatura> sensores;
	


	public SensoresController() {
		serial = new Serial();
		/*
		 * Cuando se inicie si arduino no envio nada, deberian quedar vacio los sensores.6	
		 * */
		SensorTemperatura s1 = new SensorTemperatura(1, "None", Double.valueOf(0), Double.valueOf(0));
		SensorTemperatura s2 = new SensorTemperatura(2, "None", Double.valueOf(0), Double.valueOf(0));
		SensorTemperatura s3 = new SensorTemperatura(3, "None", Double.valueOf(0), Double.valueOf(0));
		sensores = new ArrayList<>();
		sensores.add(s1);
		sensores.add(s2);
		sensores.add(s3);
		
		(new Thread(serial)).start();
	}
	
	@Scheduled(fixedRate = 2000)
	public void readPort(){		
		 String json;
		 if((json  = serial.readPort())!=null){
			 ObjectMapper mapper = new ObjectMapper();
			try {
				
				JsonNode jsonNodes = mapper.readTree(json);
				sensores.clear();
				sensores.add(mapper.readValue(jsonNodes.get("s1").toString(), new TypeReference<SensorTemperatura>(){}));
				sensores.add(mapper.readValue(jsonNodes.get("s2").toString(), new TypeReference<SensorTemperatura>(){}));
				sensores.add(mapper.readValue(jsonNodes.get("s3").toString(), new TypeReference<SensorTemperatura>(){}));
				System.out.println(sensores.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} 
		 }

		

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
					ObjectMapper mapper = new ObjectMapper();
					String jsonInString;
					try {
						jsonInString = mapper.writeValueAsString(sensorTemperatura);
						System.out.println("Enviando a aruino : " + jsonInString);
						serial.write(jsonInString);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
		
		}
	}

	public List<SensorTemperatura> getSensores() {
		return sensores;
	}
	
	

	

}
