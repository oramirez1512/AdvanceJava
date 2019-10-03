package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import javax.validation.constraints.AssertTrue;

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

	
	private final static Long clieId=4560L;
	private final static Logger log = LoggerFactory.getLogger(ClienteRepositoryTest.class);
	@Autowired
	IClienteRepository iClienteRepository;
	
	@Autowired
	ITipoDcoumentoRepository iTipoDcoumentoRepository;
	
	@Test
	@DisplayName("save")
	void aTest() {
		assertNotNull(iClienteRepository,"iClienteRepository is null");
		assertNotNull(iTipoDcoumentoRepository,"iTipoDcoumentoRepository is null");
		assertFalse(iClienteRepository.findById(clieId).isPresent());
		
		Cliente cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("avenida siempreviva 123");
		cliente.setDireccion("HomeroJSimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("555 555 555");
		
		assertTrue(iTipoDcoumentoRepository.findById(1l).isPresent(), "El tipo de documento no existe");
		cliente.setTipoDocumento(iTipoDcoumentoRepository.findById(1L).get());
		
		iClienteRepository.save(cliente);
		
	}
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertNotNull(iClienteRepository,"iClienteRepository is null");
		Optional<Cliente> clienOptional=iClienteRepository.findById(clieId);
		assertTrue(clienOptional.isPresent());
	}
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void cTest() {
		assertNotNull(iClienteRepository,"iClienteRepository is null");
		Optional<Cliente> clienteOptional = iClienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent(),"Client with id:"+clieId+" not exists");
		
		Cliente cliente= clienteOptional.get();
		cliente.setActivo("N");
		iClienteRepository.save(cliente);
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void dTest() {
		assertNotNull(iClienteRepository,"iClienteRepository is null");
		Optional<Cliente> clienteOptional = iClienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent(),"Client with id:"+clieId+" not exists");		
		Cliente cliente= clienteOptional.get();
		iClienteRepository.save(cliente);
	}

}
