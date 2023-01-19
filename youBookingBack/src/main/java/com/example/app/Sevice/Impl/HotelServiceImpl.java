package com.example.app.Sevice.Impl;

import com.example.app.DTO.HotelDTO;
import com.example.app.Entity.Hotel;
import com.example.app.Repositry.HotelReposetry;
import com.example.app.Repositry.UserRepository;
import com.example.app.Sevice.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelReposetry hotelReposetry;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Hotel saveHotel(Hotel hotel) {
        hotel.setManger(userRepository.findByUsername(currentUser()));
        hotel.setApproved(Boolean.FALSE);
        return hotelReposetry.save(hotel);
    }

    @Override
    public Hotel updateHotel(Hotel hotel,Long hotelId) {
        Hotel existingHotel =hotelReposetry.findById(hotelId).get();
        if (Objects.nonNull(hotel.getName())){
            existingHotel.setName(hotel.getName());
        }
        if (Objects.nonNull(hotel.getAddress())){
            existingHotel.setAddress(hotel.getAddress());
        }
        if (Objects.nonNull(hotel.getCity())){
            existingHotel.setCity(hotel.getCity());
        }
        return hotelReposetry.save(existingHotel);
    }

    @Override
    public List<HotelDTO> listHotel() {
        List<Hotel> hotels = hotelReposetry.findAll();
        return hotels.stream()
                .map(hotel -> modelMapper.map(hotel, HotelDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public Hotel HotelhasProved(Long hotelId) {
        Hotel existingHotel =hotelReposetry.findById(hotelId).get();
        existingHotel.setApproved(Boolean.TRUE);
        return hotelReposetry.save(existingHotel);
    }

    @Override
    public HotelDTO getById(Long id) {
        Optional<Hotel> hotel = hotelReposetry.findById(id);
        if(!hotel.isPresent()){
            throw new IllegalStateException("Hotel non trouvée");
        }
        return modelMapper.map(hotel.get(), HotelDTO.class);
    }

    @Override
    public void deletHotel(Long id) {
        Optional<Hotel> hotel = hotelReposetry.findById(id);
        if(hotel.isPresent()){
            hotelReposetry.deleteById(id);
        }else{
            System.out.println("not found");
        }
    }

    @Override
    public String currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


}
