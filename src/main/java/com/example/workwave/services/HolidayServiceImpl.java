package com.example.workwave.services;

import com.example.workwave.entities.holiday;
import com.example.workwave.repositories.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;

@Service

public class HolidayServiceImpl {
    @Autowired
    HolidayRepository holidayRepository;

    @Autowired
    ServletContext context;


    public List<holiday> GetAllHoliday() {
        return holidayRepository.findAll();
    }

    public String addHoliday(holiday c)  {
        holidayRepository.save(c);
        return "ok" ;
    }

    public String deleteHoliday(Long idHoliday) {
        holidayRepository.deleteById(idHoliday);
        return "Certif removed !! " + idHoliday;
    }


    //the update method
    public holiday updateHoliday(holiday Holiday) {
        holiday existingHoliday = holidayRepository.findById(Holiday.getId()).orElse(null);
        existingHoliday.setDateEmission(Holiday.getDateEmission());
        existingHoliday.setDateExpiration(Holiday.getDateExpiration());
        existingHoliday.setDescription(Holiday.getDescription());
        existingHoliday.setEtat(Holiday.getEtat());

        return holidayRepository.save(existingHoliday);
    }

}
