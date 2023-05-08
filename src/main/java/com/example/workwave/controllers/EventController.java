package com.example.workwave.controllers;




import com.example.workwave.entities.Event;
import com.example.workwave.exception.BadDateFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.workwave.repositories.EventJpaRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
class EventController {

    @Autowired
    private EventJpaRepository eventRespository;

    @RequestMapping(value="/events", method= RequestMethod.GET)
    public List<Event> events() {
        return eventRespository.findAll();
    }

    @RequestMapping(value="/events/{start}/{end}", method=RequestMethod.GET)
    public List<Event> getEventsInRange(@PathVariable(value = "start", required = true) String start,
                                        @PathVariable(value = "end", required = true) String end) {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad start date: " + start);
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad end date: " + end);
        }

        LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(),
                ZoneId.systemDefault());

        LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(),
                ZoneId.systemDefault());

        return eventRespository.findByDateBetween(startDateTime, endDateTime);
    }

    /*CRUD OPERATIONS*/

    @RequestMapping(value="/event", method=RequestMethod.POST)
    public Event addEvent(@RequestBody Event event) {
        Event created = eventRespository.save(event);
        return created;
    }

    @RequestMapping(value="/event", method=RequestMethod.PUT)
    public Event updateEvent(@RequestBody Event event) {
        return eventRespository.save(event);
    }

    @RequestMapping(value="/event", method=RequestMethod.DELETE)
    public void removeEvent(@RequestBody Event event) {
        eventRespository.delete(event);
    }

    @RequestMapping(value="/event/{id}", method=RequestMethod.DELETE)
    public void removeEvent(@PathVariable long id) {
        Event event = eventRespository.findById(id);
        eventRespository.delete(event);
    }
}