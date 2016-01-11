package sample.dataAccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sample.dataAccess.pojo.RoomsInReservation;

@Repository
public interface RoomsInReservationRepository extends JpaRepository<RoomsInReservation, Long> {

}