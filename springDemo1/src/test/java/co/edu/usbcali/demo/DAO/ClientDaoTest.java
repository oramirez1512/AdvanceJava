package co.edu.usbcali.demo.DAO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import co.edu.usbcali.demo.dao.ClientDAO;

@ContextConfiguration("/applicationContextDAO.xml")
class ClientDaoTest {
	
	@Autowired
	private ClientDAO clientDAO;
	
	@Test
	void test() {
		assertNotNull(clientDAO, "Client DAO is null");
	}

}
