package com.example.app.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_hotel;
    private String name;
    private String city;
    private String address;
    private Boolean approved;
    @JsonManagedReference
    @OneToMany(mappedBy = "hotel",fetch = FetchType.LAZY)
    private List<Room> rooms;
    @OneToOne
    private appUser manger;

}
