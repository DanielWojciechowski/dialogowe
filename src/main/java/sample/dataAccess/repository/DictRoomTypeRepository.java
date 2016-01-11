package sample.dataAccess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sample.dataAccess.pojo.DictRoomType;

@Repository
public interface DictRoomTypeRepository extends JpaRepository<DictRoomType, Long> {

    List<DictRoomType> findByRoomTypeIgnoreCase(String roomType);

}