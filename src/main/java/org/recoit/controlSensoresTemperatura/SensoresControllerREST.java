package org.recoit.controlSensoresTemperatura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensoresControllerREST {
	
	@Autowired
	SensoresController sensoresControler;

	@RequestMapping(value = "/sensor/{id}", method= RequestMethod.GET)
	public SensorTemperatura sensor(@PathVariable("id") Integer id){
		System.out.println("Sensor a parsear "+ sensoresControler.getSensor(id));
		return sensoresControler.getSensor(id);
	}
	
	@RequestMapping(value = "/sensor/{id}/minvalue/{value}", method= RequestMethod.POST)
	public void setMinValue(@PathVariable("id") Integer id, @PathVariable("value") Double value){
		sensoresControler.setSensorMinTemp(id, value);
		System.out.println(sensoresControler.getSensor(id));
	}
	
	
	
}
