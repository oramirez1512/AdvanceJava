package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, String>{

}
