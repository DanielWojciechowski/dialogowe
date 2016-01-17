package sample.dataAccess.service;

import org.springframework.stereotype.Service;
import sample.dataAccess.pojo.DictReservationStatus;

@Service
public interface DictReservationStatusService {

    DictReservationStatus getByCode(String code);
}
