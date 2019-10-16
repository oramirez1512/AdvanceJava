package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long>{
	
	

}
