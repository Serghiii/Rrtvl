package com.rrtvl.domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="videogallery")
public class VideoGallery {

    @JsonView(View.PAGE.class)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @JsonView(View.PAGE.class)
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date date;

    @JsonView(View.PAGE.class)
    private String title;

    @JsonView(View.PAGE.class)
    private String filename;

    private String videofilename;

    public VideoGallery() {
    }

    public VideoGallery(Date date, String title, String filename, String videofilename) {
        this.date = date;
        this.title = title;
        this.filename = filename;
        this.videofilename = videofilename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getVideofilename() {
        return videofilename;
    }

    public void setVideofilename(String videofilename) {
        this.videofilename = videofilename;
    }

}
