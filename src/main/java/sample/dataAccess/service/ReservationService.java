package sample.dataAccess.service;

import org.springframework.stereotype.Service;
import sample.dataAccess.pojo.Reservation;

@Service
public interface ReservationService {
    Reservation save(Reservation reservation);

    Reservation findById(Long id);

}
