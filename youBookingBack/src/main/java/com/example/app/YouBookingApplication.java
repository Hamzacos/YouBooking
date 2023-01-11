package com.example.app;

import com.example.app.Entity.Hotel;
import com.example.app.Entity.appRole;
import com.example.app.Entity.appUser;
import com.example.app.Sevice.AdminService;
import com.example.app.Sevice.Impl.AdminServiceImpl;
import com.example.app.Sevice.Impl.HotelServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class YouBookingApplication  {



    public static void main(String[] args) {
        SpringApplication.run(YouBookingApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    CommandLineRunner start(AdminServiceImpl accountService){
        return args -> {
            accountService.addNewRole(new appRole(null,"Client"));
            accountService.addNewRole(new appRole(null,"Admin"));
            accountService.addNewRole(new appRole(null,"Manger"));

            accountService.saveUser(new appUser(null,"hamza laqraa","user1","1234",new ArrayList<>()));
            accountService.saveUser(new appUser(null,"taha kanani","user2","1234",new ArrayList<>()));
            accountService.saveUser(new appUser(null,"Moad ghazi","Client3","1234",new ArrayList<>()));
            accountService.saveUser(new appUser(null,"salim ahmad","khrdala","1234",new ArrayList<>()));
            accountService.saveUser(new appUser(null,"Ayoub laqraa","Sniper","1234",new ArrayList<>()));
²
            accountService.addRoleToUser("user1","Client");
            accountService.addRoleToUser("user2","Client");
            accountService.addRoleToUser("Client3","Client");
            accountService.addRoleToUser("user1","Manger");
            accountService.addRoleToUser("Sniper","Admin");

        };
    }*/

}
