package sample.dataAccess.service.impl;

import org.springframework.stereotype.Service;
import sample.dataAccess.pojo.Room;
import sample.dataAccess.repository.RoomRepository;
import sample.dataAccess.service.RoomService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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

        return rooms.isEmpty() ? null : rooms.get(0);
    }

    @Transactional
    @Override
    public Room findAvailableByRoomTypeWithExclude(String roomType, Date startDate, Date endDate, Long reservationId) {
        System.out.println("Parametry\n typ:" + roomType + " start:" + startDate + " end:" + endDate + " reservationId:" + reservationId);
        List<Room> rooms = repository.findAvailableByRoomTypeWithExclude(roomType, startDate, endDate, reservationId);

        return rooms.isEmpty() ? null : rooms.get(0);
    }

    @Override
    public Room findById(Long id) {
        List<Room> rooms = repository.findById(id);

        return rooms.isEmpty() ? null : rooms.get(0);
    }

}
