package co.edu.usbcali.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	@Mapping(source = "tipoUsuario.tiusId", target ="tiusId")
	UserDTO userToUserDTO(Usuario user);

	@Mapping(source = "tiusId", target = "tipoUsuario.tiusId")
	Usuario userDTOtoUser(UserDTO userDTO);
	
	List<Usuario> toUsers(List<UserDTO> userDTO);
	
	List<UserDTO> toDTOUsers(List<Usuario> users);

}
