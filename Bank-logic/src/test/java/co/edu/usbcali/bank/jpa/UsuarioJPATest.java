package co.edu.usbcali.bank.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.domain.Usuario;

class UsuarioJPATest {
	
	private final static String usuID="4560L";
	private final static BigDecimal Id= new java.math.BigDecimal("45648451");
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
		Usuario usuario = entityManager.find(Usuario.class, usuID);
		assertNull(usuario,"User with id:"+usuID+" already exists");
		
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setUsuUsuario(usuID);
		usuario.setClave("xxx123");
		usuario.setNombre("Homero J Simpson");
		usuario.setIdentificacion(Id);
		usuario.setFechaCreacion(ts);
		
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, 1L);
		assertNotNull(tipoDocumento, "is null");
		
		entityManager.getTransaction().begin();
			entityManager.persist(usuario);
			entityManager.getTransaction().commit();
		
	}
	
	@Test
	@DisplayName("findById")
	void bTest() {
		assertNotNull(entityManager,"entityManager is null");
		Usuario usuario= entityManager.find(Usuario.class,usuID);
		assertNotNull(usuario, "USER with id:"+usuID+" already exists");
	}
	
	
	@Test
	@DisplayName("update")
	void cTest() {
		assertNotNull(entityManager,"entityManager is null");
		Usuario usuario = entityManager.find(Usuario.class, usuID);
		assertNotNull(usuario,"User with id:"+usuID+" not exists");
		
		usuario.setActivo("N");
		
		entityManager.getTransaction().begin();
			entityManager.merge(usuario);			
			entityManager.getTransaction().commit();
			entityManager.refresh(usuario);
		
	}	
	
	@Test
	@DisplayName("delete")
	void dTest() {
		assertNotNull(entityManager,"entityManager is null");
		Usuario usuario = entityManager.find(Usuario.class, usuID);
		assertNotNull(usuario,"Client with id:"+usuID+" not exists");
		
		
		
		entityManager.getTransaction().begin();
			entityManager.remove(usuario);			
			entityManager.getTransaction().commit();
		
	}

}
