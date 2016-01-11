package sample.dataAccess.service.impl;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sample.dataAccess.pojo.RoomsInReservation;
import sample.dataAccess.repository.RoomsInReservationRepository;
import sample.dataAccess.service.RoomsInReservationService;

@Service
public class RoomsInReservationServiceImpl implements RoomsInReservationService {

    private final RoomsInReservationRepository repository;

    @Inject
    public RoomsInReservationServiceImpl(RoomsInReservationRepository repository) {
	this.repository = repository;
    }

    @Transactional
    @Override
    public RoomsInReservation save(RoomsInReservation reservation) {
	return repository.save(reservation);
    }

}
