package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

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
import java.sql.Timestamp;

import co.edu.usbcali.bank.domain.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TipoDocumentoRepositoryTest {

	private final static Logger log = LoggerFactory.getLogger(TipoDocumentoRepositoryTest.class);

	private final static Long tipoDocumentoId = 4560L;

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;

	@Test
	@DisplayName("test")
	void test() {
		assertNotNull(tipoDocumentoRepository);
	}

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void aTest() {
		assertNotNull(tipoDocumentoRepository);

		Optional<TipoDocumento> tipoDocumentoOptional = tipoDocumentoRepository.findById(tipoDocumentoId);
		assertFalse(tipoDocumentoOptional.isPresent(), "Tipo de documento ya existe.");
		
		TipoDocumento tipoDocumento = new TipoDocumento();
		
		tipoDocumento = new TipoDocumento();
		tipoDocumento.setActivo("S");
		tipoDocumento.setFechaCreacion(new Timestamp(1L));
		tipoDocumento.setFechaModificacion(new Timestamp(1L));
		tipoDocumento.setNombre("DEFAULT_TEST");
		tipoDocumento.setTdocId(tipoDocumentoId);
		
		tipoDocumentoRepository.save(tipoDocumento);
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertNotNull(tipoDocumentoRepository);
		
		Optional<TipoDocumento> clienteOpcional = tipoDocumentoRepository.findById(tipoDocumentoId);
		assertTrue(clienteOpcional.isPresent(), "Cliente con id: " + tipoDocumentoId + " no existe.");
	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void cTest() {
		assertNotNull(tipoDocumentoRepository);
				
		Optional<TipoDocumento> clienteOpcional = tipoDocumentoRepository.findById(tipoDocumentoId);
		assertTrue(clienteOpcional.isPresent(), "Cliente con id: "+tipoDocumentoId+" no existe.");		

		TipoDocumento cliente = clienteOpcional.get();
		cliente.setActivo("N");
		
		tipoDocumentoRepository.save(cliente);
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void dTest() {
		assertNotNull(tipoDocumentoRepository);
		
		Optional<TipoDocumento> tipoDocumentoOpcional = tipoDocumentoRepository.findById(tipoDocumentoId);
		assertTrue(tipoDocumentoOpcional.isPresent(), "Tipo de documento con id: "+tipoDocumentoId+" no existe.");		

		TipoDocumento cliente = tipoDocumentoOpcional.get();
		cliente.setActivo("N");
		
		tipoDocumentoRepository.delete(cliente);
	}

	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = true)
	void eTest() {
		assertNotNull(tipoDocumentoRepository);
		
		List<TipoDocumento> clientes = tipoDocumentoRepository.findAll();
		
		assertNotNull(clientes);
		assertFalse(clientes.isEmpty());
		
		for (TipoDocumento cliente : clientes) {
			log.info(cliente.getNombre());
		}
		
		clientes.forEach(cliente -> {
			log.info(cliente.getNombre());
		});
	}
}
