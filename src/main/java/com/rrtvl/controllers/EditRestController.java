package com.rrtvl.controllers;

import com.rrtvl.domain.Visits;
import com.rrtvl.repos.VisitsRepo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class EditRestController {
    private final VisitsRepo visitsRepo;

    public EditRestController(VisitsRepo visitsRepo) {
        this.visitsRepo = visitsRepo;
    }

//    @PutMapping("/editvisits/json") // не працює через scrf
    @GetMapping("/editvisits/json/put")
    public Visits update (@RequestParam(name="id", required=false, defaultValue="") String id,
                          @RequestParam(name="date", required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                          @RequestParam(name="btime", required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime btime,
                          @RequestParam(name="etime", required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime etime,
                          @RequestParam(name="name", required=true) String name){
        Visits visits = new Visits();
        if (!id.isEmpty()) visits.setId(Long.parseLong(id));
        visits.setDate(date);
        visits.setBtime(btime);
        visits.setEtime(etime);
        visits.setName(name);
        return visitsRepo.save(visits);
    }

//    @DeleteMapping("/editvisits/json/{id}") // не працює через scrf
    @GetMapping("/editvisits/json/delete/{id}")
    public Long delete(@PathVariable("id") Long id){
        visitsRepo.deleteById(id);
        return id;
    }
}
