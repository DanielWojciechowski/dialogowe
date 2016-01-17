package sample.dataAccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.dataAccess.pojo.DictReservationStatus;

import java.util.List;

@Repository
public interface DictReservationStatusRepository extends JpaRepository<DictReservationStatus, Long> {

    List<DictReservationStatus> findByCodeIgnoreCase(String code);

}