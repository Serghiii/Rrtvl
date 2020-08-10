package com.rrtvl.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="visits")
public class Visits {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;
    @DateTimeFormat(pattern = "HH:ss")
    private LocalTime btime;
    @DateTimeFormat(pattern = "HH:ss")
    private LocalTime etime;
    private String name;

    public Visits() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getBtime() {
        return btime;
    }

    public void setBtime(LocalTime btime) {
        this.btime = btime;
    }

    public LocalTime getEtime() {
        return etime;
    }

    public void setEtime(LocalTime etime) {
        this.etime = etime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
