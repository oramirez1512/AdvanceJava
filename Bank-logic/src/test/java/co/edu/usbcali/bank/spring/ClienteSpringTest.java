package co.edu.usbcali.bank.spring;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class ClienteSpringTest {

	
	private final static Long clieId=4560L;
	
	@PersistenceContext
	EntityManager entityManager;	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@DisplayName("Save")
	void aTest() {
		assertNotNull(entityManager, "entityManager is null");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNull(cliente,"Client with id:"+clieId+" already exists");
		
		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("avenida siempreviva 123");
		cliente.setDireccion("HomeroJSimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("555 555 555");
		
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, 1L);
		assertNotNull(tipoDocumento, "is null");
		
		cliente.setTipoDocumento(tipoDocumento);
		entityManager.persist(cliente);
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertNotNull(entityManager,"entityManager is null");
		Cliente cliente= entityManager.find(Cliente.class,clieId);
		assertNotNull(cliente, "Client with id:"+clieId+" already exists");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void cTest() {
		assertNotNull(entityManager,"entityManager is null");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente,"Client with id:"+clieId+" not exists");
		
		cliente.setActivo("N");
		
		entityManager.merge(cliente);					
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void dTest() {
		assertNotNull(entityManager,"entityManager is null");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente,"Client with id:"+clieId+" not exists");
		
			entityManager.remove(cliente);
		
	}
		
	private final static Logger log = LoggerFactory.getLogger(ClienteSpringTest.class);
	
	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = true)
	void eTest() {
		assertNotNull(entityManager, "entityManager is null");
		String jpql= "SELECT cli FROM Cliente cli";
		Query query = entityManager.createQuery(jpql);
		List<Cliente> clients=query.getResultList();
		assertNotNull(clients);
		assertFalse(clients.isEmpty());
		
		clients.forEach(cliente ->{
			log.info(cliente.getNombre());
		});
	}

}
