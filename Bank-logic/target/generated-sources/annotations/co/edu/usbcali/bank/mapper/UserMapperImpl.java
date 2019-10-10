package co.edu.usbcali.bank.mapper;

import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.0.Final, compiler: Eclipse JDT (IDE) 3.17.0.v20190306-2240, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userToUserDTO(Usuario user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setTiusId( userTipoUsuarioTiusId( user ) );
        userDTO.setUsuUsuario( user.getUsuUsuario() );
        userDTO.setActivo( user.getActivo() );
        userDTO.setClave( user.getClave() );
        userDTO.setIdentificacion( user.getIdentificacion() );
        userDTO.setNombre( user.getNombre() );

        return userDTO;
    }

    @Override
    public Usuario userDTOtoUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setTipoUsuario( userDTOToTipoUsuario( userDTO ) );
        usuario.setUsuUsuario( userDTO.getUsuUsuario() );
        usuario.setActivo( userDTO.getActivo() );
        usuario.setClave( userDTO.getClave() );
        usuario.setIdentificacion( userDTO.getIdentificacion() );
        usuario.setNombre( userDTO.getNombre() );

        return usuario;
    }

    @Override
    public List<Usuario> toUsers(List<UserDTO> userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        List<Usuario> list = new ArrayList<Usuario>( userDTO.size() );
        for ( UserDTO userDTO1 : userDTO ) {
            list.add( userDTOtoUser( userDTO1 ) );
        }

        return list;
    }

    @Override
    public List<UserDTO> toDTOUsers(List<Usuario> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( Usuario usuario : users ) {
            list.add( userToUserDTO( usuario ) );
        }

        return list;
    }

    private Long userTipoUsuarioTiusId(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }
        TipoUsuario tipoUsuario = usuario.getTipoUsuario();
        if ( tipoUsuario == null ) {
            return null;
        }
        Long tiusId = tipoUsuario.getTiusId();
        if ( tiusId == null ) {
            return null;
        }
        return tiusId;
    }

    protected TipoUsuario userDTOToTipoUsuario(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        TipoUsuario tipoUsuario = new TipoUsuario();

        tipoUsuario.setTiusId( userDTO.getTiusId() );

        return tipoUsuario;
    }
}
