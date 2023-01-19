package com.example.app.DTO;

import com.example.app.Entity.Hotel;
import com.example.app.Entity.Room;

public class RoomDTO {
    private Long roomId;
    private String name;
    private int numberOfRoom;
    private int floor;
    private int capacity;
    private double price;




    public Room toEntity(){
        Room room = new Room();
        room.setCapacity(this.capacity);
        room.setName(this.name);
        room.setNumberOfRoom(this.numberOfRoom);
        room.setPrice(this.price);
        room.setRoomId(this.roomId);
        //room.setRoomResarvation(this.roomResarvation.stream().map(ReservationDTO::toEntity).collect(Collectors.toList()));
        return room;
    }


    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }


    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomId=" + roomId +
                "name=" + name +
                ", numberOfRoom=" + numberOfRoom +
                ", floor=" + floor +
                ", capacity=" + capacity +
                ", price=" + price +
                '}';
    }
}
