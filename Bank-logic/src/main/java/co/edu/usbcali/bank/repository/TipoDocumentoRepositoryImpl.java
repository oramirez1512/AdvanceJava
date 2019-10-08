package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.TipoDocumento;

@Repository
@Scope("singleton")
public class TipoDocumentoRepositoryImpl implements TipoDocumentoRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public TipoDocumento save(TipoDocumento entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Optional<TipoDocumento> findById(Long pk) {
		return Optional.ofNullable(entityManager.find(TipoDocumento.class, pk));
	}

	@Override
	public List<TipoDocumento> findAll() {
		return entityManager.createQuery("FROM TipoDocumento", TipoDocumento.class).getResultList();
	}

	@Override
	public void delete(TipoDocumento entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(Long pk) {
		findById(pk).ifPresent(tipoDocumento -> delete(tipoDocumento));
	}

}
