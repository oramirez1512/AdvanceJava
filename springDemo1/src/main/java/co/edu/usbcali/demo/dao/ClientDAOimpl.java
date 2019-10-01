package co.edu.usbcali.demo.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.domain.Client;

@Repository
@Scope("singleton")
public class ClientDAOimpl implements ClientDAO{

	@Override
	public Client save(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client update(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findall() {
		// TODO Auto-generated method stub
		return null;
	}

}
