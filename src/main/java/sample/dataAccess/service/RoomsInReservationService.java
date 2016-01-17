package sample.dataAccess.service;

import org.springframework.stereotype.Service;
import sample.dataAccess.pojo.Reservation;
import sample.dataAccess.pojo.RoomsInReservation;

import java.util.List;

@Service
public interface RoomsInReservationService {
    RoomsInReservation save(RoomsInReservation reservation);

    List<RoomsInReservation> findByReservation(Reservation reservation);

}
