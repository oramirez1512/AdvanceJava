package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.usbcali.bank.domain.Usuario;

public class UserRepositoryImpl implements UserRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Usuario save(Usuario entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Optional<Usuario> findById(String pk) {
		Usuario usuario = entityManager.find(Usuario.class, pk);
		Optional<Usuario> optional = Optional.ofNullable(usuario);
		return optional;
	}

	@Override
	public List<Usuario> findAll() {
		return entityManager.createQuery("FROM Usuario", Usuario.class).getResultList();
	}

	@Override
	public void delete(Usuario entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public void deleteById(String pk) {
		Optional<Usuario> UsuarioOpcional = this.findById(pk);
		UsuarioOpcional.ifPresent(usuario -> delete(usuario));
		
	}

}
