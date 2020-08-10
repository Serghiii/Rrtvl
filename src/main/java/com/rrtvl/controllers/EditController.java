package com.rrtvl.controllers;

import com.rrtvl.domain.Images;
import com.rrtvl.domain.News;
import com.rrtvl.domain.PhotoGallery;
import com.rrtvl.domain.VideoGallery;
import com.rrtvl.repos.ImagesRepo;
import com.rrtvl.repos.NewsRepo;
import com.rrtvl.repos.PhotoGalleryRepo;
import com.rrtvl.repos.VideoGalleryRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
public class EditController {

    private final NewsRepo newsRepo;

    private final PhotoGalleryRepo photoGalleryRepo;

    private final ImagesRepo imagesRepo;

    private final VideoGalleryRepo videoGalleryRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public EditController(NewsRepo newsRepo, PhotoGalleryRepo photoGalleryRepo, ImagesRepo imagesRepo, VideoGalleryRepo videoGalleryRepo) {
        this.newsRepo = newsRepo;
        this.photoGalleryRepo = photoGalleryRepo;
        this.imagesRepo = imagesRepo;
        this.videoGalleryRepo = videoGalleryRepo;
    }

    @GetMapping("/editvideo")
    public String getvideo(@RequestParam(name="page", required=false, defaultValue="1") String page, Model model) {
        return ControllerUtils.showlist(videoGalleryRepo,"editvideo", page, 10, model);
    }

    @PostMapping("/editvideo/item")
    public String postvideo(Model model) {
        model.addAttribute("editvideo_item", new VideoGallery());
        return "editvideo_item";
    }

    @PostMapping("/editvideo/item/{id}")
    public String postvideo(@PathVariable("id") VideoGallery videoGallery, Model model) {
        model.addAttribute("editvideo_item", videoGallery);
        return "editvideo_item";
    }

    @PostMapping("/editvideo/item/put")
    public String putvideo(@ModelAttribute VideoGallery videoGalery, @RequestParam("file") MultipartFile file, @RequestParam("videofile") MultipartFile videofile) {

        if (videoGalery.getId() == null) { // если новая запись, то сохраняем для получения id
            videoGalleryRepo.save(videoGalery);
        }

        String filename = StringUtils.getFilename(file.getOriginalFilename());
        if (!filename.isEmpty()) { // если есть что загружать, то загружаем и удаляем старый файл если расширения файлов разные
            String filenameext = StringUtils.getFilenameExtension(filename);
            if (!videoGalery.getFilename().isEmpty()) {
                if (!StringUtils.getFilenameExtension(videoGalery.getFilename()).contains(filenameext)) {
                    ControllerUtils.deleteFile(uploadPath + "/video/" + videoGalery.getId() + '/' + videoGalery.getFilename());
                }
            }
            videoGalery.setFilename(Long.toString(videoGalery.getId()) + '.' + filenameext);
            ControllerUtils.uploadFile(file, uploadPath + "/video/" + videoGalery.getId(), videoGalery.getFilename());
        }
        videoGalleryRepo.save(videoGalery);

        filename = StringUtils.getFilename(videofile.getOriginalFilename());
        if (!filename.isEmpty()) { // если есть что загружать, то загружаем и удаляем старый файл если расширения файлов разные
            String filenameext = StringUtils.getFilenameExtension(filename);
            if (!videoGalery.getVideofilename().isEmpty()) {
                if (!StringUtils.getFilenameExtension(videoGalery.getVideofilename()).contains(filenameext)) {
                    ControllerUtils.deleteFile(uploadPath + "/video/" + videoGalery.getId() + '/' + videoGalery.getVideofilename());
                }
            }
            videoGalery.setVideofilename(Long.toString(videoGalery.getId()) + '.' + filenameext);
            ControllerUtils.uploadFile(videofile, uploadPath + "/video/" + videoGalery.getId(), videoGalery.getVideofilename());
        }
        videoGalleryRepo.save(videoGalery);

        return "redirect:/editvideo";
    }

    @PostMapping("/editvideo/delete/{id}")
    public String deletevideo(@PathVariable String id) {
        Optional<VideoGallery> videoGallery = videoGalleryRepo.findById(Long.parseLong(id));
        if (ControllerUtils.deleteFile(uploadPath + "/video/" + videoGallery.get().getId())) {
            videoGalleryRepo.delete(videoGallery.get());
        }
        return "redirect:/editvideo";
    }

    @GetMapping("/editphoto")
    public String getphoto(@RequestParam(name="page", required=false, defaultValue="1") String page, Model model) {
        return ControllerUtils.showlist(photoGalleryRepo,"editphoto", page, 3, model);
    }

    @PostMapping("/editphoto/item")
    public String postphoto(Model model) {
        model.addAttribute("editphoto_item", new PhotoGallery());
        return "editphoto_item";
    }

    @PostMapping("/editphoto/item/{id}")
    public String postphoto(@PathVariable("id") PhotoGallery photoGallery, Model model) {
        model.addAttribute("editphoto_item", photoGallery);
        return "editphoto_item";
    }

    @PostMapping("/editphoto/item/put")
    public String putphoto(@ModelAttribute PhotoGallery photoGalery, @RequestParam("file") MultipartFile file) {
        if (photoGalery.getId() == null) { // если новая запись, то сохраняем для получения id
            photoGalleryRepo.save(photoGalery);
        }
        String filename = StringUtils.getFilename(file.getOriginalFilename());
        if (!filename.isEmpty()) { // если есть что загружать, то загружаем и удаляем старый файл если расширения файлов разные
            String filenameext = StringUtils.getFilenameExtension(filename);
            if (!photoGalery.getFilename().isEmpty()) {
                if (!StringUtils.getFilenameExtension(photoGalery.getFilename()).contains(filenameext)) {
                    ControllerUtils.deleteFile(uploadPath + "/photo/" + photoGalery.getId() + '/' + photoGalery.getFilename());
                }
            }
            photoGalery.setFilename(Long.toString(photoGalery.getId()) + '.' + filenameext);
            ControllerUtils.uploadFile(file, uploadPath + "/photo/" + photoGalery.getId(), photoGalery.getFilename());
        }
        photoGalleryRepo.save(photoGalery);
        return "redirect:/editphoto";
    }

    @PostMapping("/editphoto/delete/{id}")
    public String deletephoto(@PathVariable String id) {
        Optional<PhotoGallery> photoGalery = photoGalleryRepo.findById(Long.parseLong(id));
        if (ControllerUtils.deleteFile(uploadPath + "/photo/" + photoGalery.get().getId())) {
            photoGalleryRepo.delete(photoGalery.get());
        }
        return "redirect:/editphoto";
    }

    @PostMapping("/editphoto/image/newitem/{id}")
    public String postnewimage(@PathVariable("id") PhotoGallery photoGallery, Model model) {
        model.addAttribute("editimage_item", new Images(photoGallery));
        return "editimage_item";
    }

    @PostMapping("/editphoto/image/item/{id}")
    public String postimage(@PathVariable("id") Images images, Model model) {
        model.addAttribute("editimage_item", images);
        return "editimage_item";
    }

    @PostMapping("/editphoto/image/item/put")
    public String putimage(@ModelAttribute Images Image, @RequestParam("file") MultipartFile file) {
        if (Image.getId() == null) { // если новая запись, то сохраняем для получения id
            imagesRepo.save(Image);
        }
        String filename = StringUtils.getFilename(file.getOriginalFilename());
        if (!filename.isEmpty()) { // если есть что загружать, то загружаем и удаляем старый файл если расширения файлов разные
            String filenameext = StringUtils.getFilenameExtension(filename);
            if (!Image.getFilename().isEmpty()) {
                if (!StringUtils.getFilenameExtension(Image.getFilename()).contains(filenameext)) {
                    ControllerUtils.deleteFile(uploadPath + "/photo/" + Image.getGallery().getId() + "/image/" + Image.getFilename());
                }
            }
            Image.setFilename(Long.toString(Image.getId()) + '.' + filenameext);
            ControllerUtils.uploadFile(file, uploadPath + "/photo/" + Image.getGallery().getId() + "/image/", Image.getFilename());
        }
        imagesRepo.save(Image);
        return "redirect:/editphoto";
    }

    @PostMapping("/editphoto/image/delete/{id}")
    public String deleteimage(@PathVariable String id) {
        Optional<Images> image = imagesRepo.findById(Long.parseLong(id));
        if (ControllerUtils.deleteFile(uploadPath + "/photo/" + image.get().getGallery().getId() + "/image/" + image.get().getFilename())) {
            imagesRepo.delete(image.get());
        }
        return "redirect:/editphoto";
    }

    @GetMapping("/editnews")
    public String getnews(@RequestParam(name="page", required=false, defaultValue="1") String page, Model model) {
        return ControllerUtils.showlist(newsRepo,"editnews", page, 10, model);
    }

    @PostMapping("/editnews/item")
    public String postnews(Model model) {
        model.addAttribute("editnews_item", new News());
        return "editnews_item";
    }

    @PostMapping("/editnews/item/{id}")
    public String postnews(@PathVariable("id") News news, Model model) {
        model.addAttribute("editnews_item", news);
        return "editnews_item";
    }

    @PostMapping("/editnews/item/put")
    public String putnews(@ModelAttribute News news) {
        if (news.getImg().length == 0) {
            news.setImg(null);
        } else {
            news.setImg(Base64Utils.decode(news.getImg()));
        }
        newsRepo.save(news);
        return "redirect:/editnews";
    }

    @PostMapping("/editnews/delete/{id}")
    public String deletenews(@PathVariable String id) {
        newsRepo.deleteById(Long.parseLong(id));
        return "redirect:/editnews";
    }

}