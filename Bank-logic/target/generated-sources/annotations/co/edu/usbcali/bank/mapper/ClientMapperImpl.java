package co.edu.usbcali.bank.mapper;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.dto.ClienteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.0.Final, compiler: Eclipse JDT (IDE) 3.17.0.v20190306-2240, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClienteDTO clientToClientDTO(Cliente client) {
        if ( client == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setTdocId( clientTipoDocumentoTdocId( client ) );
        clienteDTO.setClieId( client.getClieId() );
        clienteDTO.setActivo( client.getActivo() );
        clienteDTO.setDireccion( client.getDireccion() );
        clienteDTO.setEmail( client.getEmail() );
        clienteDTO.setNombre( client.getNombre() );
        clienteDTO.setTelefono( client.getTelefono() );

        return clienteDTO;
    }

    @Override
    public Cliente clientDTOtoClient(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setTipoDocumento( clienteDTOToTipoDocumento( clienteDTO ) );
        cliente.setClieId( clienteDTO.getClieId() );
        cliente.setActivo( clienteDTO.getActivo() );
        cliente.setDireccion( clienteDTO.getDireccion() );
        cliente.setEmail( clienteDTO.getEmail() );
        cliente.setNombre( clienteDTO.getNombre() );
        cliente.setTelefono( clienteDTO.getTelefono() );

        return cliente;
    }

    @Override
    public List<Cliente> toClients(List<ClienteDTO> DTOClients) {
        if ( DTOClients == null ) {
            return null;
        }

        List<Cliente> list = new ArrayList<Cliente>( DTOClients.size() );
        for ( ClienteDTO clienteDTO : DTOClients ) {
            list.add( clientDTOtoClient( clienteDTO ) );
        }

        return list;
    }

    @Override
    public List<ClienteDTO> toDTOClients(List<Cliente> Clients) {
        if ( Clients == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( Clients.size() );
        for ( Cliente cliente : Clients ) {
            list.add( clientToClientDTO( cliente ) );
        }

        return list;
    }

    private Long clientTipoDocumentoTdocId(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }
        TipoDocumento tipoDocumento = cliente.getTipoDocumento();
        if ( tipoDocumento == null ) {
            return null;
        }
        Long tdocId = tipoDocumento.getTdocId();
        if ( tdocId == null ) {
            return null;
        }
        return tdocId;
    }

    protected TipoDocumento clienteDTOToTipoDocumento(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        TipoDocumento tipoDocumento = new TipoDocumento();

        tipoDocumento.setTdocId( clienteDTO.getTdocId() );

        return tipoDocumento;
    }
}
