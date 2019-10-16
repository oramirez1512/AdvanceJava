package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.TipoDocumento;

public interface ITipoDcoumentoRepository extends JpaRepository<TipoDocumento, Long>{

}
