package co.edu.usbcali.bank.service;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@SpringBootTest
@Rollback(false)
class ClienteServiceTest {

	private final static Logger log = LoggerFactory.getLogger(ClienteServiceTest.class);
	private final static Long clieId = 4560L;

	@Autowired
	ClienteService clienteService;

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;

	@Test
	@DisplayName("save")
	void test() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);

		Cliente cliente = new Cliente();
		cliente.setClieId(clieId);
		cliente.setActivo("S");
		cliente.setDireccion("Calle falsa 123");
		cliente.setEmail("HomeroJSimpson@gmail.com");
		cliente.setNombre("Homero J. Simpson");
		cliente.setTelefono("555 555 555");

		assertTrue(tipoDocumentoRepository.findById(1L).isPresent(), "Tipo de documento no existe.");
		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());

		try {
			clienteService.save(cliente);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("findById")
	void bTest() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);

		Optional<Cliente> clienteOpcional = clienteService.findById(clieId);
		assertTrue(clienteOpcional.isPresent(), "Cliente con id: " + clieId + " no existe.");
	}

	@Test
	@DisplayName("update")
	@Transactional
	void cTest() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);

		Optional<Cliente> clienteOpcional = clienteService.findById(clieId);
		assertTrue(clienteOpcional.isPresent(), "Cliente con id: " + clieId + " no existe.");

		Cliente cliente = clienteOpcional.get();
		cliente.setActivo("N");

		try {
			clienteService.update(cliente);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("delete")
	void dTest() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);

		Optional<Cliente> clienteOpcional = clienteService.findById(clieId);
		assertTrue(clienteOpcional.isPresent(), "Cliente con id: " + clieId + " no existe.");

		Cliente cliente = clienteOpcional.get();
		cliente.setActivo("N");

		try {
			clienteService.delete(cliente);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("findAll")
	void eTest() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);

		List<Cliente> clientes = clienteService.findAll();

		assertNotNull(clientes);
		assertFalse(clientes.isEmpty());

		for (Cliente cliente : clientes) {
			log.info(cliente.getNombre());
		}

		clientes.forEach(cliente -> {
			log.info(cliente.getNombre());
		});
	}
	
	
	//private final static Logger log= LoggerFactory.getLogger(ClienteServiceTest.class);
	
	@Test
	@DisplayName("findByName")
	void findByName() {
		assertNotNull(clienteService, "clienteService is null");
		List<Cliente> Clients = clienteService.findByNombre("Humfried Downes");
		assertNotNull(Clients);
		assertFalse(Clients.isEmpty());
		for (Cliente cliente : Clients) {
			log.info("id:"+cliente.getClieId());
			log.info("Name: "+cliente.getNombre());
		}
		
	}
	
	@Test
	@DisplayName("findByName")
	void findByNameLike() {
		assertNotNull(clienteService, "clienteService is null");
		List<Cliente> Clients = clienteService.findByNombreLike("K%");
		assertNotNull(Clients);
		assertFalse(Clients.isEmpty());
		for (Cliente cliente : Clients) {
			log.info("id:"+cliente.getClieId());
			log.info("Name: "+cliente.getNombre());
		}
		
	}

}
