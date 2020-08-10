package com.rrtvl.controllers;

import com.rrtvl.common.Pagination;
import com.rrtvl.domain.News;
import com.rrtvl.repos.NewsRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class ControllerUtils {

    private static Pagination BuildPagination(int Page, int Pagesize, int Count, String maket) {
        if (Page < 1) Page = 1;
        int Pages = Count != 0 ? (Count / Pagesize) + (Count % Pagesize != 0 ? 1 : 0) : 0;
        if (Page > Pages) Page = Pages;
        return new Pagination(Pages, Page, (Pages >= 70 ? 7 : Pages >= 60 ? 6 : Pages >= 50 ? 5 : Pages >= 40 ? 4 : Pages >= 30 ? 3 : Pages >= 10 ? 2 : 1), "/" + maket);
    }

    static String showlist(PagingAndSortingRepository<?,?> Repo, String maket, String page, int pagesize, Model model) {
        model.addAttribute("pageband",BuildPagination(Integer.parseInt(page), pagesize, (int)Repo.count(), maket).getCode());
        Page<?> repo = Repo.findAll(PageRequest.of(Integer.parseInt(page)-1, pagesize, Sort.by(Sort.Direction.DESC, "date")));
        model.addAttribute(maket, repo);
        return maket;
    }

    static String showsearchlist(NewsRepo Repo, String maket, String filter, String page, int pagesize, Model model) {
        model.addAttribute("q", filter);
        int сount = 1;
        if (!filter.isEmpty()) {
            Page<News> repo = Repo.findAllByTitleContainingOrDescriptionContainingOrContentContainingIgnoreCaseOrderByDateDesc(filter, filter, filter, PageRequest.of(Integer.parseInt(page)-1, 20));
            сount = (int)repo.getTotalElements();
            if (сount < 1)сount = 1;
            model.addAttribute(maket, repo);
        }
        model.addAttribute("pageband", BuildPagination(Integer.parseInt(page), pagesize, сount, maket).getCode());
        return maket;
    }

    static Boolean uploadFile(MultipartFile file, String uploadPath, String filename) {
        String fname = file.getOriginalFilename();
        fname = StringUtils.getFilename(fname);
        if (!fname.isEmpty()) {
            File targetDir = new File(uploadPath);
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            try {
                file.transferTo(new File(uploadPath + '/' + filename));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    static boolean deleteFile(String filename) {
        return deleteDirectory(new File(filename));
    }

    static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    } else {
                        files[i].delete();
                    }
                }
            }
        }
        return path.delete();
    }

}
