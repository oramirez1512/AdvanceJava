package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.domain.Usuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long>{

}
