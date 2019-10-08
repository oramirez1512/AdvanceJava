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
import org.springframework.beans.factory.annotation.Autowired;
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

	private final static Long clieId = 4560L; 
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void test() {
		assertNotNull(entityManager, "El entity manager es nulo.");		

		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNull(cliente, "Cliente con id: "+clieId+" ya existe.");
		
		cliente = new Cliente();
		cliente.setClieId(clieId);
		cliente.setActivo("S");
		cliente.setDireccion("Calle falsa 123");
		cliente.setEmail("HomeroJSimpson@gmail.com");
		cliente.setNombre("Homero J. Simpson");
		cliente.setTelefono("555 555 555");
		
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, 1L);
		assertNotNull(tipoDocumento, "El tipo de documento con id: 1 no existe.");
		
		cliente.setTipoDocumento(tipoDocumento);
		entityManager.persist(cliente);
	}	

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		//fail("Not yet implemented");
		assertNotNull(entityManager, "Entity manager es nulo.");
		
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Cliente con id: "+clieId+" no existe.");
	}	

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void cTest() {
		//fail("Not yet implemented");
		assertNotNull(entityManager, "Entity manager es nulo.");
		
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Cliente con id: "+clieId+" no existe.");		

		cliente.setActivo("N");
		
		entityManager.refresh(cliente);
		entityManager.merge(cliente);
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void dTest() {
		//fail("Not yet implemented");
		assertNotNull(entityManager, "Entity manager es nulo.");
		
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Cliente con id: "+clieId+" no existe.");		

		cliente.setActivo("N");
		
		entityManager.remove(cliente);
	}

	private final static Logger log = LoggerFactory.getLogger(ClienteSpringTest.class);

	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = true)
	void eTest() {
		//fail("Not yet implemented");
		assertNotNull(entityManager, "Entity manager es nulo.");
		
		String jpql = "SELECT cli FROM Cliente cli";
		Query query = entityManager.createQuery(jpql);
		
		List<Cliente> clientes = query.getResultList();
		assertNotNull(clientes);
		assertFalse(clientes.isEmpty());
		
		for (Cliente cliente : clientes) {
			log.info(cliente.getNombre());
		}
		
		clientes.forEach(cliente -> {
			log.info(cliente.getNombre());
		});
	}
	
}
