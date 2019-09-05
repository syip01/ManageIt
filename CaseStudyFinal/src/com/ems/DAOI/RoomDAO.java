package com.ems.DAOI;

import java.util.List;

import com.ems.entities.Room;

public interface RoomDAO {

	List<Room> getAllRooms();

	Room getRoomById(int pId);

	boolean addRoom(Room room);

	boolean updateRoom(Room room);

	boolean deleteRoom(Room room);

	Room getRoomByName(String name);

}
