package org.recoit.controlSensoresTemperatura.controller;

import java.util.List;

import org.recoit.controlSensoresTemperatura.model.MessageSockect;
import org.recoit.controlSensoresTemperatura.model.SensorTemperatura;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableScheduling
public class SensoresControllerREST {
	
	private static Logger logger = LoggerFactory.getLogger(SensoresControllerREST.class);

	
	@Autowired
	SensoresController sensoresControler;
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/sensores", method= RequestMethod.GET)
	public List<SensorTemperatura> sensor(){
		return sensoresControler.getSensores();
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/sensor/{id}", method= RequestMethod.GET)
	public SensorTemperatura sensor(@PathVariable("id") Integer id){
		return sensoresControler.getSensor(id);
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/sensor", method= RequestMethod.POST)
	public SensorTemperatura setMinValue(@RequestBody SensorTemperatura s){
		sensoresControler.setSensorModificar(s);	
		return sensoresControler.getSensor(s.getId());
	}
	
	@Autowired
    SimpMessagingTemplate template;
	
	 @MessageMapping(value = "/sensoresUpdate")
	 @SendTo("/topic/sensoresUpdate")
	 public MessageSockect sensores(MessageSockect msg){
		 System.out.println("UPDATE -->" + msg);
		 return new MessageSockect(sensoresControler.getSensores().toString());
	 }
	 
	 @Scheduled(fixedRate = 2000)
	 public void readPort(){
	
		 System.out.println("-->");
		 template.convertAndSend("/app/sensoresUpdate", new MessageSockect("nuevo"));
	 }

	
	

}
