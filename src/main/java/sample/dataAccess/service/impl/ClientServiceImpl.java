package sample.dataAccess.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sample.dataAccess.pojo.Client;
import sample.dataAccess.repository.ClientRepository;
import sample.dataAccess.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Inject
    public ClientServiceImpl(ClientRepository repository) {
	this.repository = repository;
    }

    @Override
    @Transactional
    public Client getByName(String name) {
	List<Client> clients = repository.findByFirstNameIgnoreCase(name);
	return clients.isEmpty() ? new Client() : clients.get(0);
    }

    @Override
    public List<Client> listAll() {
	return repository.findAll();
    }

    @Override
    public Client getByFullNameAndPhoneNumber(String firstName, String lastName, String phoneNumber) {
	List<Client> clients = repository.findByFirstNameAndLastNameAndPhoneNumber(firstName, lastName, phoneNumber);
	System.out.println(clients.isEmpty() ? "Nie znalazł clienta" : clients.get(0).getFirstName());
	return clients.isEmpty() ? new Client() : clients.get(0);
    }

}
