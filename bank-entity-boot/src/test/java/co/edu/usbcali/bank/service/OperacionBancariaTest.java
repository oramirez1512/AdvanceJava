package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class OperacionBancariaTest {

	private final static Logger log = LoggerFactory.getLogger(OperacionBancariaTest.class);
	
	@Autowired
	OperacionBancaria operacionBancaria;
	
	@Test
	void retirar() {
		try {
			String cuenId ="4640-0341-9387-5781";
			BigDecimal valor= new BigDecimal(1000);
			String usuUsuario="callbrook0";
			Long numerTransaccion = operacionBancaria.retirar(cuenId, valor, usuUsuario);
			log.info("Id: "+numerTransaccion);
		} catch (Exception e) {
			log.error(e.getMessage());
			assertNull(e,e.getMessage());
		}
	}

}
