package club.pard.server.ftiland.oldnew.repository.room;

import club.pard.server.ftiland.oldnew.entity.room.Room;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long>{
    public Boolean existsByCode(String targetRoomCode);
    public Optional<Room> findByCode(String targetRoomCode);
}