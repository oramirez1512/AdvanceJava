package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.TipoUsuarioRepository;
import co.edu.usbcali.bank.repository.UserRepository;

@Service
@Scope("singleton")
public class UserServiceImpl implements UserService{

	@Autowired
	Validator validator;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario save(Usuario usuario) throws Exception {
		validar(usuario);

		if(userRepository.findById(usuario.getUsuUsuario()).isPresent()) {
			throw new Exception("El cliente con id: "+usuario.getUsuUsuario()+" ya existe.");
		} 
		if(tipoUsuarioRepository.findById(usuario.getTipoUsuario().getTiusId()).isPresent() == false) {
			throw new Exception("El tipo de usuario con id: "+usuario.getTipoUsuario().getTiusId()+" no existe.");
		}
		
		return userRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario update(Usuario usuario) throws Exception {
		if(userRepository.findById(usuario.getUsuUsuario()).isPresent()) {
			throw new Exception("El cliente con id: "+usuario.getUsuUsuario()+" ya existe.");
		} 
		if(tipoUsuarioRepository.findById(usuario.getTipoUsuario().getTiusId()).isPresent() == false) {
			throw new Exception("El tipo de usuario con id: "+usuario.getTipoUsuario().getTiusId()+" no existe.");
		}
		
		Usuario entity=userRepository.findById(usuario.getUsuUsuario()).get();
		entity.setActivo(usuario.getActivo());
		entity.setClave(usuario.getClave());
		entity.setIdentificacion(usuario.getIdentificacion());
		entity.setNombre(usuario.getNombre());
		entity.setTipoUsuario(usuario.getTipoUsuario());
		
		
		return userRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(String pk) {
		// TODO Auto-generated method stub
		return userRepository.findById(pk);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Usuario usuario) throws Exception {
		validar(usuario);
		
		if(userRepository.findById(usuario.getUsuUsuario()).isPresent() == false) {
			throw new Exception("El usuario con id: "+usuario.getUsuUsuario()+" no existe.");
		}
		
		usuario = findById(usuario.getUsuUsuario()).get();
		
		if(usuario.getTransaccions().size() > 0) {
			throw new Exception("No se puede borrar porque tiene transacciones registradas.");
		}
		
		userRepository.delete(usuario);
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String pk) throws Exception {
		if(pk==null) {
			throw new Exception("El id no es valido.");
		}
		
		if (findById(pk).isPresent() == false) {
			throw new Exception("El usuario con id: "+pk+" no existe.");
		}
		
		delete(findById(pk).get());
		
	}
	
	public void validar(Usuario usuario) throws Exception {

		if (usuario == null)
			throw new Exception("El cliente es nulo.");
		
		Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);

		if (constraintViolations.size() > 0) {
			StringBuilder strMessage = new StringBuilder();

			for (ConstraintViolation<Usuario> constraintViolation : constraintViolations) {
				strMessage.append(constraintViolation.getPropertyPath().toString());
				strMessage.append(" - ");
				strMessage.append(constraintViolation.getMessage());
				strMessage.append(". \n");
			}

			throw new Exception(strMessage.toString());
		}
	}
	

}
