package com.rrtvl.controllers;

import com.rrtvl.domain.News;
import com.rrtvl.domain.PhotoGallery;
import com.rrtvl.repos.NewsRepo;
import com.rrtvl.repos.PhotoGalleryRepo;
import com.rrtvl.repos.VideoGalleryRepo;
import com.rrtvl.repos.VisitsRepo;
import com.rrtvl.service.MyMailSender;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class MainController {

    private final NewsRepo newsRepo;

    private final PhotoGalleryRepo photoGalleryRepo;

    private final VideoGalleryRepo videoGalleryRepo;

    private final VisitsRepo visitsRepo;

    private final MyMailSender sender;

    public MainController(NewsRepo newsRepo, PhotoGalleryRepo photoGalleryRepo, VideoGalleryRepo videoGalleryRepo, com.rrtvl.repos.VisitsRepo visitsRepo, MyMailSender sender) {
        this.newsRepo = newsRepo;
        this.photoGalleryRepo = photoGalleryRepo;
        this.videoGalleryRepo = videoGalleryRepo;
        this.visitsRepo = visitsRepo;
        this.sender = sender;
    }

    @GetMapping("/")
    public String root(Model model) {
        Iterable<News> news = newsRepo.findAll(PageRequest.of(0,3, Sort.by(Sort.Direction.DESC,"date")));
        model.addAttribute("news", news);
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/tariffs")
    public String tariffs() {
        return "tariffs";
    }

    @GetMapping("/tvbroadcasting")
    public String tvbroadcasting() {
        return "tvbroadcasting";
    }

    @GetMapping("/radio")
    public String radio() {
        return "radio";
    }

    @GetMapping("/distnetworks")
    public String distnetworks() {
        return "distnetworks";
    }

    @GetMapping("/otherservices")
    public String otherservices() {
        return "otherservices";
    }

    @GetMapping("/graph")
    public String graph() {
        return "graph";
    }

    @GetMapping("/tvchannels")
    public String tvchannels() {
        return "tvchannels";
    }

    @GetMapping("/fmradio")
    public String fmradio() {
        return "fmradio";
    }

    @GetMapping("/cellular")
    public String cellular() { return "cellular"; }

    @GetMapping("/telecomservices")
    public String telecomservices() { return "telecomservices"; }

    @GetMapping("/news")
    public String news(@RequestParam(name="page", required=false, defaultValue="1") String page, @RequestParam(name="id", required=false, defaultValue="") String id, Model model) {
        if (id.isEmpty()) { // show page of news
            return ControllerUtils.showlist(newsRepo,"news", page, 10, model);
        }
        else { // show news item
            Optional<News> news_item = newsRepo.findById(Long.parseLong(id));
            if (news_item.isPresent()) {
                model.addAttribute("news_item", news_item.get());
                return "news_item";
            }
            else{ // show page of news
                return ControllerUtils.showlist(newsRepo,"news", page, 10, model);
            }
        }
    }

    @GetMapping("/search")
    public String search(@RequestParam(name="page", required=false, defaultValue="1") String page, @RequestParam(name="q") String q, Model model) {
        return ControllerUtils.showsearchlist(newsRepo,"search", q, page, 20, model);
    }

    @GetMapping("/video")
    public String video(@RequestParam(name="page", required=false, defaultValue="1") String page, Model model) {
        return ControllerUtils.showlist(videoGalleryRepo,"video", page, 15, model);
    }

    @GetMapping("/photo")
    public String photo(@RequestParam(name="page", required=false, defaultValue="1") String page, @RequestParam(name="id", required=false, defaultValue="") String id, Model model) {
        if (id.isEmpty()) { // show page of photo
            return ControllerUtils.showlist(photoGalleryRepo,"photo", page, 15, model);
        }
        else { // show photo item
            Optional<PhotoGallery> photoGalery = photoGalleryRepo.findById(Long.parseLong(id));
            if (photoGalery.isPresent()) {
                model.addAttribute("title", photoGalery.get().getTitle());
                model.addAttribute("images", photoGalery.get().getImages());
                return "photo_items";
            }
            else{ // show page of photo
                return ControllerUtils.showlist(photoGalleryRepo,"photo", page, 15, model);
            }
        }
    }

    @GetMapping("/court")
    public String court(@RequestParam(name="page", required=false, defaultValue="1") String page, @RequestParam(name="id", required=false, defaultValue="") String id, Model model) {
        return ControllerUtils.showlist(visitsRepo,"court", page, 20, model);
    }

    @PostMapping("/backf")
    public @ResponseBody boolean contactsreq(@RequestParam(value="name") String name,
                                             @RequestParam(value="email") String email,
                                             @RequestParam(value="topic") String topic,
                                             @RequestParam(value="topictxt") String topictxt)
    {
        return sender.send(name,email,topic,topictxt);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
