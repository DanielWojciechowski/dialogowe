package sample.dataAccess.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sample.dataAccess.pojo.Room;
import sample.dataAccess.repository.RoomRepository;
import sample.dataAccess.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository repository;

    @Inject
    public RoomServiceImpl(RoomRepository repository) {
	this.repository = repository;
    }

    @Transactional
    @Override
    public Room findAvailableByRoomType(String roomType, Date startDate, Date endDate) {
	System.out.println("Parametry\n typ:" + roomType + " start:" + startDate + " end:" + endDate);
	List<Room> rooms = repository.findAvailableByRoomType(roomType, startDate, endDate);

	return rooms.isEmpty() ? new Room() : rooms.get(0);
    }

    @Override
    public Room findById(Long id) {
	List<Room> rooms = repository.findById(id);

	return rooms.isEmpty() ? new Room() : rooms.get(0);
    }

}
