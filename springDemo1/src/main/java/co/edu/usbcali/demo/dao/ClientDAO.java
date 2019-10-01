package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.domain.Client;

public interface ClientDAO {
	
	public Client save(Client client);
	public Client update (Client client);
	public void delete(Client client);
	public Client findById(Integer id);
	public List<Client> findall();

}
