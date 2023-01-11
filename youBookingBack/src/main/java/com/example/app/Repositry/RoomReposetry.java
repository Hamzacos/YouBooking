package com.example.app.Repositry;

import com.example.app.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomReposetry extends JpaRepository<Room, Long>{

    Room findRoomByRoomId(Long roomId);
}
