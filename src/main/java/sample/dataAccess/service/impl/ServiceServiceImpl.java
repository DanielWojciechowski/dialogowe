package sample.dataAccess.service.impl;

import org.springframework.stereotype.Service;
import sample.dataAccess.repository.ServiceRepository;
import sample.dataAccess.service.ServiceService;

import javax.inject.Inject;
import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository repository;

    @Inject
    public ServiceServiceImpl(ServiceRepository repository) {
	    this.repository = repository;
    }


    @Override
    public List<sample.dataAccess.pojo.Service> getAll() {
        return repository.findAll();
    }

    @Override
    public sample.dataAccess.pojo.Service getByName(String name) {
        List<sample.dataAccess.pojo.Service> services = repository.findByNameIgnoreCase(name);
        return services.isEmpty() ? null : services.get(0);
    }
}
