package sample.dataAccess.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sample.dataAccess.pojo.Client;

@Service
public interface ClientService {
    Client getByName(String name);

    List<Client> listAll();

    Client getByFullNameAndPhoneNumber(String firstName, String lastName, String phoneNumber);
}
