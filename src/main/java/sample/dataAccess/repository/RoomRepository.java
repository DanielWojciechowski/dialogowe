package sample.dataAccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import sample.dataAccess.pojo.Room;

import java.util.Date;
import java.util.List;

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
	    + "	    and (:startDate between res.startDate and res.endDate "
	    + "	    or  :endDate between res.startDate and res.endDate)  "
	    + ")                                                         "
	    + "and rt.roomType = :roomType                               ";

    @Query(FIND_NOT_RESERVED_ROOMS)
    List<Room> findAvailableByRoomType(@Param("roomType") String roomType,
	    @Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
	    @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate);

	String FIND_NOT_RESERVED_ROOMS_WITH_EXCLUDE = "select r from Room r		 "
			+ "left join r.roomType rt                                   "
			+ "where r.id not in                                         "
			+ "(                                                         "
			+ "	select distinct ro.id from RoomsInReservation rir         "
			+ "		left join rir.reservation res                    "
			+ "		left join rir.room ro                             "
			+ "	where rt.roomType = :roomType                            "
			+ "	    and res.id <> :reservationId 						 "
			+ "	    and (:startDate between res.startDate and res.endDate "
			+ "	    or  :endDate between res.startDate and res.endDate)  "
			+ ")                                                         "
			+ "and rt.roomType = :roomType                               ";

	@Query(FIND_NOT_RESERVED_ROOMS_WITH_EXCLUDE)
	List<Room> findAvailableByRoomTypeWithExclude(@Param("roomType") String roomType,
									   @Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
									   @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
									   @Param("reservationId") Long reservationId);

    List<Room> findById(Long id);
}
