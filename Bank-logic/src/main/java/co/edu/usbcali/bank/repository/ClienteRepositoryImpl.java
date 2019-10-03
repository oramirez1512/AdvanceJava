package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.Cliente;
@Repository
@Scope("singleton")
public class ClienteRepositoryImpl implements IClienteRepository {

	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Cliente save(Cliente entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		Cliente cliente= entityManager.find(Cliente.class,id);
		Optional<Cliente> optional = Optional.ofNullable(cliente);
		return optional;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findall() {
		return entityManager.createQuery("FROM Cliente").getResultList();
	}

	@Override
	public void delete(Cliente entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(Long id) {
		/*
		Optional<Cliente> clienteOptional = findById(id);
		if(clienteOptional.isPresent()) {
			Cliente cliente = clienteOptional.get();
			delete(cliente);
		}
		*/
		findById(id).ifPresent(cliente->delete(cliente));
	}

}
