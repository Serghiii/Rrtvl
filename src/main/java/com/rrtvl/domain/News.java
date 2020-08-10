package com.rrtvl.domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="news")
public class News {

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
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] img;

    public News() {
    }

    public News(String title, String description, String content, byte[] img) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.img = img;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}