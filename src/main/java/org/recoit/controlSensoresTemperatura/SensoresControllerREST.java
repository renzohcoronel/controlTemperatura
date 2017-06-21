package org.recoit.controlSensoresTemperatura;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensoresControllerREST {
	
	@Autowired
	SensoresController sensoresControler;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/sensores", method= RequestMethod.GET)
	public List<SensorTemperatura> sensor(){
		System.out.println("Sensorres" );
		return sensoresControler.getSensores();
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/sensor/{id}", method= RequestMethod.GET)
	public SensorTemperatura sensor(@PathVariable("id") Integer id){
		System.out.println("Sensor a parsear "+ sensoresControler.getSensor(id));
		return sensoresControler.getSensor(id);
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/sensor", method= RequestMethod.POST)
	public SensorTemperatura setMinValue(@RequestBody SensorTemperatura s){
		
		System.out.println("Antes " + s.toString());
		sensoresControler.setSensorModificar(s);
		System.out.println("Despues " + sensoresControler.getSensor(s.getId()).toString());
		
		return sensoresControler.getSensor(s.getId());
	}
	
	
	
}
