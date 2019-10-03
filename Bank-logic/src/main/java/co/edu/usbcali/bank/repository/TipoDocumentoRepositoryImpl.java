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
public class TipoDocumentoRepositoryImpl implements ITipoDcoumentoRepository{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public TipoDocumento save(TipoDocumento entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Optional<TipoDocumento> findById(Long id) {
		return Optional.ofNullable(entityManager.find(TipoDocumento.class,id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoDocumento> findall() {
		return entityManager.createQuery("FROM TipoDocumento").getResultList();
	}

	@Override
	public void delete(TipoDocumento entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public void deleteById(Long id) {
		findById(id).ifPresent(t->delete(t));
	}

}
