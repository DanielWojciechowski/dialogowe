package sample.dataAccess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sample.dataAccess.pojo.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByFirstNameIgnoreCase(String name);

    List<Client> findByFirstNameAndLastNameAndPhoneNumber(String firstName, String lastName, String phoneNumber);
}
