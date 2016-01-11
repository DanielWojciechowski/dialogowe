package sample.dataAccess.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sample.dataAccess.pojo.Reservation;
import sample.dataAccess.repository.ReservationRepository;
import sample.dataAccess.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;

    @Inject
    public ReservationServiceImpl(ReservationRepository repository) {
	this.repository = repository;
    }

    @Transactional
    @Override
    public Reservation save(Reservation reservation) {
	return repository.save(reservation);
    }

    @Override
    public Reservation findById(Long id) {
	List<Reservation> reservation = repository.findById(id);

	return reservation.isEmpty() ? new Reservation() : reservation.get(0);
    }

}
