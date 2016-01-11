package sample.dataAccess.service;

import org.springframework.stereotype.Service;

import sample.dataAccess.pojo.RoomsInReservation;

@Service
public interface RoomsInReservationService {
    RoomsInReservation save(RoomsInReservation reservation);

}
