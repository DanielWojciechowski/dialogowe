package sample.dataAccess.service.impl;

import org.springframework.stereotype.Service;
import sample.dataAccess.pojo.DictReservationStatus;
import sample.dataAccess.repository.DictReservationStatusRepository;
import sample.dataAccess.service.DictReservationStatusService;

import javax.inject.Inject;
import java.util.List;

@Service
public class DictReservationStatusServiceImpl implements DictReservationStatusService {

    private final DictReservationStatusRepository repository;

    @Inject
    public DictReservationStatusServiceImpl(DictReservationStatusRepository repository) {
	    this.repository = repository;
    }

    @Override
    public DictReservationStatus getByCode(String code) {
        List<DictReservationStatus> reservationStatuses = repository.findByCodeIgnoreCase(code);
        return reservationStatuses.isEmpty() ? null : reservationStatuses.get(0);
    }

}
