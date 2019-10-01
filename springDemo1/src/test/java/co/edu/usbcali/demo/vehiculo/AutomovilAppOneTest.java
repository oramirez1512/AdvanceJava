package co.edu.usbcali.demo.vehiculo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class AutomovilAppOneTest {

	@Test
	void test() {
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContextOne.xml");
		
		assertNotNull(applicationContext, "applicationContext is null");
		
		Automovil automovil =(Automovil)applicationContext.getBean("automovil");
		
		assertNotNull(automovil);
		assertNotNull(automovil.getMotor());
		assertFalse(automovil.getMotor().getElectric());
	}

}
