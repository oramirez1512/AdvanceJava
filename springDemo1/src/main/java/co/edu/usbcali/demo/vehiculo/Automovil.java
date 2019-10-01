package co.edu.usbcali.demo.vehiculo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Automovil {
	
	private final static Logger log = LoggerFactory.getLogger(Automovil.class);

	public Automovil() {
		super();
		log.info("entered the default");
	}
	
	
	public Automovil(Motor motor, String color, Integer serial) {
		super();
		this.motor = motor;
		this.color = color;
		this.serial = serial;
		log.info("entered the full");
	}


	private Motor motor;
	private String color;
	private Integer serial;
	
	//Setters and Getters
	public Motor getMotor() {
		return motor;
	}
	public void setMotor(Motor motor) {
		this.motor = motor;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getSerial() {
		return serial;
	}
	public void setSerial(Integer serial) {
		this.serial = serial;
	}
}
