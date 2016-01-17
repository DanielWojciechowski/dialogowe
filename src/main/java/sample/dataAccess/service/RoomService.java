package sample.dataAccess.service;

import org.springframework.stereotype.Service;
import sample.dataAccess.pojo.Room;

import java.util.Date;

@Service
public interface RoomService {
    Room findAvailableByRoomType(String roomType, Date startDate, Date endDate);

    Room findAvailableByRoomTypeWithExclude(String roomType, Date startDate, Date endDate, Long reservationId);

    Room findById(Long id);
}
