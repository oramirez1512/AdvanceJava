package co.edu.usbcali.demo.vehiculo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContextFive.xml")
class AutomovilAppFiveTest {
	private final static Logger log = LoggerFactory.getLogger(AutomovilAppFiveTest.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private VehicleService vehicleService;
	@Test
	void test() {		
		assertNotNull(applicationContext, "applicationContext is null");	
		assertNotNull(vehicleService,"vehicleService is null");
		}
	}


