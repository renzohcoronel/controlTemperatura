package org.recoit.controlSensoresTemperatura.controller;

import com.fazecast.jSerialComm.SerialPort;

public class Serial implements Runnable {
	
	private String port = "/dev/ttyACM0";
	SerialPort comPort;
	
	public Serial(){
		connect();
	}
	
	public void connect ()
	{
			comPort = SerialPort.getCommPort(port);
			comPort.setBaudRate(9600);
			comPort.setNumDataBits(8);
			comPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
			comPort.setParity(SerialPort.NO_PARITY);
			
			if(!comPort.openPort()){
				System.err.println("El puerto "+port+" no pudo ser abierto");
			} else {
				System.out.println("Puerto abierto " + port);
			}
    }
		
	public String readPort(){
		
		String mgs = null;
		if(comPort.bytesAvailable()>0){
			System.out.println("Leido : ");
			byte[] readBuffer = new byte[comPort.bytesAvailable()];
		    int numRead = comPort.readBytes(readBuffer, readBuffer.length);
		    mgs = new String(readBuffer);
		    System.out.println(mgs);
			
		}
	    return mgs;
	}
	

	public Boolean write(String mgs){
		byte[] readBuffer = mgs.getBytes();
	    int numRead = comPort.writeBytes(readBuffer, readBuffer.length);
	    if(numRead>0){
	    	System.out.println("Enviado: "+ mgs);
	    	return true;
	    } else {
	    	return false;
	    }
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
