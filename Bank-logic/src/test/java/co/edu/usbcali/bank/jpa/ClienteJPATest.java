package co.edu.usbcali.bank.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;

class ClienteJPATest {
	
	private final static Long clieId=4560L;
	EntityManagerFactory entityManagerFactory= null;
	EntityManager entityManager=null;
	
	
	@BeforeEach
	void beforeEach() {
		entityManagerFactory= Persistence.createEntityManagerFactory("bank-logic");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@AfterEach
	void afterEach() {
		entityManager.close();
		entityManagerFactory.close();
	}
	@Test
	void test() {
		
		assertNotNull(entityManager,"entityManager is null");
		
	}
	
	
	
	@Test
	@DisplayName("save")
	void aTest() {
		assertNotNull(entityManager,"entityManager is null");
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
		
		entityManager.getTransaction().begin();
			entityManager.persist(cliente);
			entityManager.getTransaction().commit();
		
	}		
	@Test
	@DisplayName("findById")
	void bTest() {
		assertNotNull(entityManager,"entityManager is null");
		Cliente cliente= entityManager.find(Cliente.class,clieId);
		assertNotNull(cliente, "Client with id:"+clieId+" already exists");
	}
	@Test
	@DisplayName("update")
	void cTest() {
		assertNotNull(entityManager,"entityManager is null");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente,"Client with id:"+clieId+" not exists");
		
		cliente.setActivo("N");
		
		entityManager.getTransaction().begin();
			entityManager.merge(cliente);			
			entityManager.getTransaction().commit();
			entityManager.refresh(cliente);
		
	}	
	@Test
	@DisplayName("delete")
	void dTest() {
		assertNotNull(entityManager,"entityManager is null");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente,"Client with id:"+clieId+" not exists");
		
		
		
		entityManager.getTransaction().begin();
			entityManager.remove(cliente);			
			entityManager.getTransaction().commit();
		
	}
	
	private final static Logger log = LoggerFactory.getLogger(ClienteJPATest.class);
	
	@Test
	@DisplayName("findAll")
	void eTest() {
		assertNotNull(entityManager, "entityManager is null");
		String jpql= "SELECT cli FROM Cliente cli";
		Query query = entityManager.createQuery(jpql);
		List<Cliente> clients=query.getResultList();
		assertNotNull(clients);
		assertFalse(clients.isEmpty());
		
		for (Cliente cliente : clients) {
			log.info(cliente.getNombre());			
		}
		
		clients.forEach(cliente ->{
			log.info(cliente.getNombre());
		});
	}

}
