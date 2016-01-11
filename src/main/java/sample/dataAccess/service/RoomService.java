package sample.dataAccess.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import sample.dataAccess.pojo.Room;

@Service
public interface RoomService {
    Room findAvailableByRoomType(String roomType, Date startDate, Date endDate);

    Room findById(Long id);
}
