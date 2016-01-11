package sample.dataAccess.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sample.dataAccess.pojo.DictRoomType;

@Service
public interface DictRoomTypeService {
    List<DictRoomType> listAll();

    DictRoomType getByRoomType(String roomType);
}
