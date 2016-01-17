package sample.dataAccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.dataAccess.pojo.Reservation;
import sample.dataAccess.pojo.RoomsInReservation;

import java.util.List;

@Repository
public interface RoomsInReservationRepository extends JpaRepository<RoomsInReservation, Long> {

    List<RoomsInReservation> findByReservation(Reservation reservation);
}