package co.edu.usbcali.bank.mapper;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.dto.ClienteDTO;
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
        clienteDTO.setActivo( client.getActivo() );
        clienteDTO.setClieId( client.getClieId() );
        clienteDTO.setDireccion( client.getDireccion() );
        clienteDTO.setEmail( client.getEmail() );
        clienteDTO.setNombre( client.getNombre() );
        clienteDTO.setTelefono( client.getTelefono() );

        return clienteDTO;
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
}
