package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.Usuario;

public interface UserRepository extends JpaRepository<Usuario, String>{

}
