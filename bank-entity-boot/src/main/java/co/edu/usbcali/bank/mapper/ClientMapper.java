package co.edu.usbcali.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.dto.ClienteDTO;

@Mapper
public interface ClientMapper {
	
	@Mapping(source = "tipoDocumento.tdocId", target ="tdocId")
	ClienteDTO clientToClientDTO(Cliente client);

	@Mapping(source = "tdocId", target = "tipoDocumento.tdocId")
	Cliente clientDTOtoClient(ClienteDTO clienteDTO);
	
	List<Cliente> toClients(List<ClienteDTO> DTOClients);
	
	List<ClienteDTO> toDTOClients(List<Cliente> Clients);
}
