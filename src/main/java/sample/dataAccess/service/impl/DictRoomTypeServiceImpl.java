package sample.dataAccess.service.impl;

import org.springframework.stereotype.Service;
import sample.dataAccess.pojo.DictRoomType;
import sample.dataAccess.repository.DictRoomTypeRepository;
import sample.dataAccess.service.DictRoomTypeService;

import javax.inject.Inject;
import java.util.List;

@Service
public class DictRoomTypeServiceImpl implements DictRoomTypeService {

    private final DictRoomTypeRepository repository;

    @Inject
    public DictRoomTypeServiceImpl(DictRoomTypeRepository repository) {
	    this.repository = repository;
    }

    @Override
    public List<DictRoomType> listAll() {
	    return repository.findAll();
    }

    @Override
    public DictRoomType getByRoomType(String roomType) {
        List<DictRoomType> roomTypes = repository.findByRoomTypeIgnoreCase(roomType);

        return roomTypes.isEmpty() ? null : roomTypes.get(0);
    }

}
