package com.rrtvl.domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="photogallery")
public class PhotoGallery {

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

    @OneToMany(mappedBy = "gallery", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy ("id asc")
    private Set<Images> images;

    public PhotoGallery() {
    }

    public PhotoGallery(Date date, String title) {
        this.date = date;
        this.title = title;
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

    public void setDate(Date date) { this.date = date; }

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

    public Set<Images> getImages() {
        return images;
    }

    public void setImages(Set<Images> images) {
        this.images = images;
    }
}
