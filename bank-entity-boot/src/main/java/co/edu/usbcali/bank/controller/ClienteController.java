package co.edu.usbcali.bank.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.dto.ClienteDTO;
import co.edu.usbcali.bank.mapper.ClienteMapper;
import co.edu.usbcali.bank.service.ClienteService;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteController {

	private final static Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	ClienteService clienteService;

	@Autowired
	ClienteMapper clienteMapper;

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			clienteService.deleteById(id);
			return ResponseEntity.ok().body("");
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().body(new ResponseError(400,e.getMessage()));
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody ClienteDTO clienteDTO) {
		try {
			Cliente cliente = clienteMapper.clienteDTOtoCliente(clienteDTO);
			cliente = clienteService.update(cliente);
			return ResponseEntity.ok().body("");
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().body(new ResponseError(400,e.getMessage()));
		}
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ClienteDTO clienteDTO) {
		try {
			Cliente cliente = clienteMapper.clienteDTOtoCliente(clienteDTO);
			cliente = clienteService.save(cliente);
			return ResponseEntity.ok().body("");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400,e.getMessage()));
		}
	}

	@GetMapping("/findById/{id}")
	public ClienteDTO findById(@PathVariable("id") Long id) {
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		if (clienteOptional.isPresent() == false) {
			return null;
		}
		Cliente cliente = clienteOptional.get();
		return clienteMapper.clienteToClienteDTO(cliente);
	}

	@GetMapping("/findAll")
	public List<ClienteDTO> findAll() {
		List<Cliente> listaClientes = clienteService.findAll();
		List<ClienteDTO> listaClientesDTO = clienteMapper.toClientesDTO(listaClientes);
		return listaClientesDTO;
	}
}
