package com.example.app.DTO;

import com.example.app.Entity.Room;
import com.example.app.Entity.appUser;

import java.util.List;


public class HotelDTO {

    private Long id_hotel;
    private String name;
    private String city;
    private String address;
    private Boolean approved;
    private List<RoomDTO> rooms;
    private appUser manger;


    public Long getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(Long id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public appUser getManger() {
        return manger;
    }

    public void setManger(appUser manger) {
        this.manger = manger;
    }

    @Override
    public String toString() {
        return "HotelDTO{" +
                "id_hotel=" + id_hotel +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", approved=" + approved +
                ", rooms=" + rooms +
                ", manger=" + manger +
                '}';
    }
}
