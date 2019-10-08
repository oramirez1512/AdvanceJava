package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

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

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class ClienteRepositoryTest {

	private final static Logger log = LoggerFactory.getLogger(ClienteRepositoryTest.class);

	private final static Long clieId = 4560L;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;

	@Test
	@DisplayName("test")
	void test() {
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
	}

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void aTest() {
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		
		assertFalse(clienteRepository.findById(clieId).isPresent(), "Cliente con id: " + clieId + " ya existe.");

		Cliente cliente = new Cliente();
		cliente.setClieId(clieId);
		cliente.setActivo("S");
		cliente.setDireccion("Calle falsa 123");
		cliente.setEmail("HomeroJSimpson@gmail.com");
		cliente.setNombre("Homero J. Simpson");
		cliente.setTelefono("555 555 555");

		assertTrue(tipoDocumentoRepository.findById(1L).isPresent(), "Tipo de documento con id 1 ya existe.");

		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());
		clienteRepository.save(cliente);
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		
		Optional<Cliente> clienteOpcional = clienteRepository.findById(clieId);
		assertTrue(clienteOpcional.isPresent(), "Cliente con id: " + clieId + " no existe.");
	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void cTest() {
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
				
		Optional<Cliente> clienteOpcional = clienteRepository.findById(clieId);
		assertTrue(clienteOpcional.isPresent(), "Cliente con id: "+clieId+" no existe.");		

		Cliente cliente = clienteOpcional.get();
		cliente.setActivo("N");
		
		clienteRepository.save(cliente);
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void dTest() {
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		
		Optional<Cliente> clienteOpcional = clienteRepository.findById(clieId);
		assertTrue(clienteOpcional.isPresent(), "Cliente con id: "+clieId+" no existe.");		

		Cliente cliente = clienteOpcional.get();
		cliente.setActivo("N");
		
		clienteRepository.delete(cliente);
	}

	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = true)
	void eTest() {
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		
		List<Cliente> clientes = clienteRepository.findAll();
		
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
