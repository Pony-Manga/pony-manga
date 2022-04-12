package pony.manga.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pony.manga.server.dto.TitleDTO;
import pony.manga.server.entity.*;
import pony.manga.server.service.TitleService;
import pony.manga.server.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TitleCreationController {
    @Autowired
    TitleService titleService;
    @Autowired
    UserService userService;

    @PostMapping("/api/add/author")
    public String addAuthor(Author author, Model model){
        if (author.getName().equals("") || author.getName() == null){
            model.addAttribute("response", "ERROR_NAME_EMPTY");
            return "jsonTemplate";
        }
        if (titleService.addAuthor(author)){
            model.addAttribute("response", "SUCCESS");
        } else {
            model.addAttribute("response", "ERROR_ALREADY_EXISTS");
        }
        return "jsonTemplate";
    }

    @PostMapping("/api/add/artist")
    public String addArtist(Artist artist, Model model){
        if (artist.getName().equals("") || artist.getName() == null){
            model.addAttribute("response", "ERROR_NAME_EMPTY");
            return "jsonTemplate";
        }
        if (titleService.addArtist(artist)){
            model.addAttribute("response", "SUCCESS");
        } else {
            model.addAttribute("response", "ERROR_ALREADY_EXISTS");
        }
        return "jsonTemplate";
    }

    @PostMapping("/api/add/manga_type")
    public String addMangaType(MangaType mangaType, Model model){
        if (mangaType.getName().equals("") || mangaType.getName() == null){
            model.addAttribute("response", "ERROR_NAME_EMPTY");
            return "jsonTemplate";
        }
        if (titleService.saveMangaType(mangaType)){
            model.addAttribute("response", "SUCCESS");
        } else {
            model.addAttribute("response", "ERROR_ALREADY_EXISTS");
        }
        return "jsonTemplate";
    }

    @PostMapping("/api/add/title_status")
    public String addTitleStatus(TitleStatus titleStatus, Model model){
        if (titleStatus.getName().equals("") || titleStatus.getName() == null){
            model.addAttribute("response", "ERROR_NAME_EMPTY");
            return "jsonTemplate";
        }
        if (titleService.saveTitleStatus(titleStatus)){
            model.addAttribute("response", "SUCCESS");
        } else {
            model.addAttribute("response", "ERROR_ALREADY_EXISTS");
        }
        return "jsonTemplate";
    }

    @PostMapping("/api/add/title_tag")
    public String addTitleTag(TitleTag titleTag, Model model){
        if (titleTag.getName().equals("") || titleTag.getName() == null){
            model.addAttribute("response", "ERROR_NAME_EMPTY");
            return "jsonTemplate";
        }
        if (titleService.saveTitleTag(titleTag)){
            model.addAttribute("response", "SUCCESS");
        } else {
            model.addAttribute("response", "ERROR_ALREADY_EXISTS");
        }
        return "jsonTemplate";
    }

    @PostMapping("/api/add/title")
    public String addTitle(TitleDTO title, Model model, @RequestParam("logo") MultipartFile logo){
        List<String> errorList = new ArrayList<>();
        if (title.getName().equals("") || title.getName() == null){
            errorList.add("ERROR_NAME_EMPTY");
        }
        if (title.getDescription().equals("") || title.getDescription() == null){
            errorList.add("ERROR_DESCRIPTION_EMPTY");
        }
        if (title.getArtist() == null){
            errorList.add("ERROR_ARTIST_EMPTY");
        }
        if (title.getAuthor() == null){
            errorList.add("ERROR_AUTHOR_EMPTY");
        }
        if (logo == null){
            errorList.add("ERROR_LOGO_EMPTY");
        }
        if (title.getMangaType() == null){
            errorList.add("ERROR_MANGA_TYPE_EMPTY");
        }
        if (title.getTitleStatus() == null){
            errorList.add("ERROR_TITLE_STATUS_EMPTY");
        }
        if (title.getTags() == null){
            errorList.add("ERROR_TAGS_EMPTY");
        }
        if (errorList.size() == 0){
            model.addAttribute("response", "SUCCESS");
            Title result = new Title();
            try {
                result.setLogo(logo.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.setArtist(title.getArtist());
            result.setAuthor(title.getAuthor());
            result.setTags(title.getTags());
            result.setDescription(title.getDescription());
            result.setMangaType(title.getMangaType());
            result.setName(title.getName());
            result.setReleaseDate(title.getReleaseDate());
            result.setTitleStatus(title.getTitleStatus());
            titleService.saveTitle(result);
        }
        return "jsonTemplate";
    }


}
