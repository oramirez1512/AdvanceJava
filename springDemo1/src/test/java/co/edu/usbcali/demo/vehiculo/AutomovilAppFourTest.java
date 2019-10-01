package co.edu.usbcali.demo.vehiculo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContextFour.xml")
class AutomovilAppFourTest {
	private final static Logger log = LoggerFactory.getLogger(AutomovilAppFourTest.class);
	@Test
	void test() {
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContextFour.xml");
		
		assertNotNull(applicationContext, "applicationContext is null");
		
		for (int i = 0; i <10; i++) {
			
			Motor motor =(Motor)applicationContext.getBean("motor");
			log.info(""+motor);			
		}
	}

}
