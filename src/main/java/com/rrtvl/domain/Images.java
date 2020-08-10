package com.rrtvl.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="images")
public class Images {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String filename;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photogallery_id")
    private PhotoGallery gallery;

    public Images() {
    }

    public Images(PhotoGallery gallery) {
        this.gallery = gallery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public PhotoGallery getGallery() {
        return gallery;
    }

    public void setGallery(PhotoGallery gallery) {
        this.gallery = gallery;
    }
}
