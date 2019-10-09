package co.edu.usbcali.bank.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.bank.dto.ClienteDTO;

class ClienteControllerTest {

	private final static Long clieId=7890L;
	private final static Logger log= LoggerFactory.getLogger(ClienteControllerTest.class);
	private final static String url="http://localhost:8080/bank-web/api/cliente/";
	
	@Test
	@DisplayName("save")
	void aTest() {
		RestTemplate restTemplate=new RestTemplate();
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setActivo("S");
		clienteDTO.setClieId(clieId);
		clienteDTO.setDireccion("Av siempre viva 123");
		clienteDTO.setEmail("123@htmail.com");
		clienteDTO.setNombre("Homero J Simpson");
		clienteDTO.setTdocId(1L);
		clienteDTO.setTelefono("123 44 5569");
		Object resultado = restTemplate.postForObject(url+"save", clienteDTO, Object.class);
		assertNotNull(resultado);
	}
	
	@Test
	@DisplayName("findById")
	void bTest()
	{
		RestTemplate restTemplate= new RestTemplate();
		ClienteDTO clienteDTO = restTemplate.getForObject(url+"findById/"+clieId, ClienteDTO.class);
		assertNotNull(clienteDTO, "Client doesn´t exists");
	}
	
	@Test
	@DisplayName("save")
	void cTest() {
		RestTemplate restTemplate=new RestTemplate();
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setActivo("S");
		clienteDTO.setClieId(clieId);
		clienteDTO.setDireccion("Av siempre viva 123");
		clienteDTO.setEmail("123@htmail.com");
		clienteDTO.setNombre("Homero J Simpson a");
		clienteDTO.setTdocId(1L);
		clienteDTO.setTelefono("123 44 5569");
		restTemplate.put(url+"update", clienteDTO);
	}
	
	@Test
	@DisplayName("Delete")
	void dTest()
	{
		RestTemplate restTemplate= new RestTemplate();
		restTemplate.delete(url+"delete/"+clieId); 
	}

}
