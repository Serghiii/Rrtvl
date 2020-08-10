package com.rrtvl.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.rrtvl.domain.*;
import com.rrtvl.repos.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
public class MainRestController {

    private final NewsRepo newsRepo;
    private final VideoGalleryRepo videoGalleryRepo;
    private final PhotoGalleryRepo photoGalleryRepo;
    private final ImagesRepo imagesRepo;
    private final VisitsRepo visitsRepo;

    public MainRestController(NewsRepo newsRepo, VideoGalleryRepo videoGalleryRepo, PhotoGalleryRepo photoGalleryRepo, ImagesRepo imagesRepo, VisitsRepo visitsRepo) {
        this.newsRepo = newsRepo;
        this.videoGalleryRepo = videoGalleryRepo;
        this.photoGalleryRepo = photoGalleryRepo;
        this.imagesRepo = imagesRepo;
        this.visitsRepo = visitsRepo;
    }

    @JsonView(View.PAGE.class)
    @GetMapping("/news/json/page")
    public Iterable<News> getNewsPage(@RequestParam(name="page", required=false, defaultValue="0") String page, @RequestParam(name="pagesize", required=false, defaultValue="0") String pagesize){
        if (Integer.parseInt(page) <= 0 || Integer.parseInt(pagesize) <= 0)return null;
        else return newsRepo.findAll(PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(pagesize), Sort.by(Sort.Direction.DESC, "date"))).getContent();
    }

    @GetMapping("/news/json/id")
    public Optional<News> getNewsById(@RequestParam(name="id", required=false, defaultValue="") String id){
        return newsRepo.findById(Long.parseLong(id));
    }

    @JsonView(View.PAGE.class)
    @GetMapping("/video/json/page")
    public Iterable<VideoGallery> getVideoPage(@RequestParam(name="page", required=false, defaultValue="0") String page, @RequestParam(name="pagesize", required=false, defaultValue="0") String pagesize){
        if (Integer.parseInt(page) <= 0 || Integer.parseInt(pagesize) <= 0)return null;
        else return videoGalleryRepo.findAll(PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(pagesize), Sort.by(Sort.Direction.DESC, "date"))).getContent();
    }

    @GetMapping("/video/json/id")
    public Optional<VideoGallery> getVideoById(@RequestParam(name="id", required=false, defaultValue="") String id){
        return videoGalleryRepo.findById(Long.parseLong(id));
    }

    @JsonView(View.PAGE.class)
    @GetMapping("/photo/json/page")
    public Iterable<PhotoGallery> getPhotoPage(@RequestParam(name="page", required=false, defaultValue="0") String page, @RequestParam(name="pagesize", required=false, defaultValue="0") String pagesize){
        if (Integer.parseInt(page) <= 0 || Integer.parseInt(pagesize) <= 0)return null;
        else return photoGalleryRepo.findAll(PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(pagesize), Sort.by(Sort.Direction.DESC, "date"))).getContent();
    }

    @GetMapping("/photo/json/id")
    public Optional<PhotoGallery> getImagesByGallary(@RequestParam(name="id", required=false, defaultValue="") String id){
        return photoGalleryRepo.findById(Long.parseLong(id));
    }

    @GetMapping("/visits/json/page")
    public Iterable<Visits> getVisitsPage(@RequestParam(name="page", required=false, defaultValue="0") String page, @RequestParam(name="pagesize", required=false, defaultValue="0") String pagesize){
        if (Integer.parseInt(page) <= 0 || Integer.parseInt(pagesize) <= 0)return null;
        else return visitsRepo.findAll(PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(pagesize), Sort.by("date").descending().and(Sort.by("btime").ascending().and(Sort.by("etime").ascending())))).getContent();
    }

    @GetMapping("/visits/json/id")
    public Optional<Visits> getVisitsById(@RequestParam(name="id", required=false, defaultValue="") String id){
        return visitsRepo.findById(Long.parseLong(id));
    }

    @GetMapping("/visits/json/date")
    public Iterable<Visits> getVisitsByDate(@RequestParam(name="date", required=true) String date){
        // Тупо, але по іншому ніяк
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        //-------------
        return visitsRepo.findByDate(sql);
    }

    /*
    public static <T> Iterable<T> toIterable(Optional<T> o) {
        return o.map(Collections::singleton)
                .orElseGet(Collections::emptySet);
    }
    */
}
