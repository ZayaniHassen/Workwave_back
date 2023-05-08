package com.example.workwave.controllers;

import com.example.workwave.entities.User;
import com.example.workwave.entities.holiday;
import com.example.workwave.repositories.HolidayRepository;
import com.example.workwave.repositories.UserRepository;
import com.example.workwave.services.HolidayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController

public class HolidayController {
    @Autowired
    HolidayRepository holidayRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HolidayServiceImpl holidayService;

    @PostMapping("/addHoliday")
    public String addHoliday(@RequestBody Map<String, Object> payload) {

        User user = userRepository.findById(payload.get("username").toString())
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("the user");
        System.out.println(user.getUserName());
        holiday holidayy = new holiday();
        holidayy.setDescription((String) payload.get("description"));
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateEmission = formatter.parse(payload.get("dateEmission").toString());
            Date dateExpiration = formatter.parse(payload.get("dateExpiration").toString());
            holidayy.setDateEmission(dateEmission);
            holidayy.setDateExpiration(dateExpiration);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holidayy.setUser(user);
        holidayy.setEtat("pending");

        return holidayService.addHoliday(holidayy);
    }

    @DeleteMapping("/deleteHoliday/{id}")
    public String deleteHoliday(@PathVariable long id) {
        return holidayService.deleteHoliday(id);
    }

    @PutMapping("/updateHoliday")
    public holiday updateHoliday(@RequestBody holiday holidayy) {
        return holidayService.updateHoliday(holidayy);
    }

    @GetMapping("/holiday")//affichage+pagination
    public List<holiday> show() {
        return holidayService.GetAllHoliday();
    }
    @GetMapping("/holidaybyuser/{username}")
    public List<holiday> getHolidaysByUser(@PathVariable String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return holidayRepository.findByUser(user);
    }
}
