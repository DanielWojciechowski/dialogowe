package sample.dataAccess.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import sample.dataAccess.pojo.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    String FIND_NOT_RESERVED_ROOMS = "select r from Room r		 "
	    + "left join r.roomType rt                                   "
	    + "where r.id not in                                         "
	    + "(                                                         "
	    + "	select distinct ro.id from RoomsInReservation rir         "
	    + "		left join rir.reservation res                    "
	    + "		left join rir.room ro                             "
	    + "	where rt.roomType = :roomType                            "
	    + "	    and :startDate between res.startDate and res.endDate "
	    + "	    or  :endDate between res.startDate and res.endDate  "
	    + ")                                                         "
	    + "and rt.roomType = :roomType                               ";

    @Query(FIND_NOT_RESERVED_ROOMS)
    List<Room> findAvailableByRoomType(@Param("roomType") String roomType,
	    @Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
	    @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate);

    List<Room> findById(Long id);
}
