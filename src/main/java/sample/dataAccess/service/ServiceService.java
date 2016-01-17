package sample.dataAccess.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceService {

    List<sample.dataAccess.pojo.Service> getAll();

    sample.dataAccess.pojo.Service getByName(String name);
}
