package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

public interface JPAGenericRepository<T, ID> {

	T save(T entity);
	Optional<T> findById(ID pk);
	List<T> findAll();
	void delete(T entity);
	void deleteById(ID pk);
}
