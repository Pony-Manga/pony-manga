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

/**
 * Данный класс - контроллер. Он отвечает за заполнение информации о произведении на сайте. Тоесть позволяет
 * добавлять авторов, художников и т.п. которые являются составной частью информации о произведении.
 */
@Controller
public class TitleCreationController {
    @Autowired
    TitleService titleService;
    @Autowired
    UserService userService;

    /**
     * Метод с эндпоинтом /api/add/author. На данный эндпоинтом POST запросом отправляют данные об авторе, где поля с
     * информацией названы так же как и в классе {@link pony.manga.server.entity.Author}.
     * @param author - содержит информацию об авторе
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return Возвращает фактически json, который собиарется из данных, записанных в model. Ответ либо содрежит сообщение
     * SUCCESS, если запись прошла успешно, ERROR_NAME_EMPTY, если нет данных и ERROR_ALREADY_EXISTS, если в базе
     * уже есть поле с таким названием.
     */
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

    /**
     * Метод с эндпоинтом /api/add/artist. На данный эндпоинтом POST запросом отправляют данные о художнике, где поля с
     * информацией названы так же как и в классе {@link pony.manga.server.entity.Artist}.
     * @param artist - содержит информацию о художнике
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return Возвращает фактически json, который собиарется из данных, записанных в model. Ответ либо содрежит сообщение
     * SUCCESS, если запись прошла успешно, ERROR_NAME_EMPTY, если нет данных и ERROR_ALREADY_EXISTS, если в базе
     * уже есть поле с таким названием.
     */
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

    /**
     * Метод с эндпоинтом /api/add/manga_type. На данный эндпоинтом POST запросом отправляют данные о типе произведения,
     * где поля с информацией названы так же как и в классе {@link pony.manga.server.entity.MangaType}.
     * @param mangaType - содержит информацию о типе манги
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return Возвращает фактически json, который собиарется из данных, записанных в model. Ответ либо содрежит сообщение
     * SUCCESS, если запись прошла успешно, ERROR_NAME_EMPTY, если нет данных и ERROR_ALREADY_EXISTS, если в базе
     * уже есть поле с таким названием.
     */
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

    /**
     * Метод с эндпоинтом /api/add/manga_status. На данный эндпоинтом POST запросом отправляют данные о состоянии
     * произведения, где поля с информацией названы так же как и в классе {@link pony.manga.server.entity.TitleStatus}.
     * @param titleStatus - содержит информацию о сосоянии произведения
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return Возвращает фактически json, который собиарется из данных, записанных в model. Ответ либо содрежит сообщение
     * SUCCESS, если запись прошла успешно, ERROR_NAME_EMPTY, если нет данных и ERROR_ALREADY_EXISTS, если в базе
     * уже есть поле с таким названием.
     */
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

    /**
     * Метод с эндпоинтом /api/add/title_tag. На данный эндпоинтом POST запросом отправляют данные о новом
     * теге, где поля с информацией названы так же как и в классе {@link pony.manga.server.entity.TitleTag}.
     * @param titleTag - содержит инфомрацию о новом теге
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return Возвращает фактически json, который собиарется из данных, записанных в model. Ответ либо содрежит сообщение
     * SUCCESS, если запись прошла успешно, ERROR_NAME_EMPTY, если нет данных и ERROR_ALREADY_EXISTS, если в базе
     * уже есть поле с таким названием.
     */
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

    /**
     * Метод с эндпоинтом /api/add/title. На данный эндпоинтом POST запросом отправляют данные о новом
     * произведении, где основные поля названы как в классе {@link pony.manga.server.dto.TitleDTO}, а также поле logo - файл
     * с изображением обложки произведения
     * @param title - основные поля с инфомрацией о произведении
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @param logo - файл с изображением обложки произведения
     * @return Возвращает JSON со списком ошибок если такие есть: <br>
     * ERROR_NAME_EMPTY - название произведения пустое <br>
     * ERROR_DESCRIPTION_EMPTY - описание пустое <br>
     * ERROR_ARTIST_EMPTY - поле с художником пустое <br>
     * ERROR_AUTHOR_EMPTY - поле с автором пустое <br>
     * ERROR_LOGO_EMPTY - нет файла с обложкой <br>
     * ERROR_MANGA_TYPE_EMPTY - поле с типом манги пустое <br>
     * ERROR_TITLE_STATUS_EMPTY - поле с состоянием произведения пустое <br>
     * Когда все поля проверены, проверется наличие  в базе манги с таким же названием. Если есть, возвращается сообщение
     * ERROR_ALREADY_EXIST. <br>
     * Когда запрос успешен возвращается SUCCESS.
     */
    @PostMapping("/api/add/title")
    public String addTitle(TitleDTO title, Model model, @RequestParam("logo") MultipartFile logo){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        if (user == null){
            return "jsonTemplate";
        }
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
            result.setUploader(user);
            if (titleService.saveTitle(result)){
                model.addAttribute("response", "SUCCESS");
            } else {
                model.addAttribute("response", "ERROR_ALREADY_EXIST");
            }
        }
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/add/chapter. На данный эндпоинтом POST запросом отправляют данные о новой
     * главе, где основные поля названы как в классе {@link pony.manga.server.entity.TitleChapter}, а также поле pages - файлы
     * с изображением страницы
     * @param chapter - информация о главе
     * @param pages - список страниц главы
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return Возвращает JSON со списком ошибок если такие есть: <br>
     * ERROR_NAME_EMPTY - название главы пустое <br>
     * ERROR_DATE_UPLOADED_EMPTY - отсутсвует дата загрузки <br>
     * Когда запрос успешен возвращается SUCCESS.
     */
    @PostMapping("/api/add/chapter")
    public String addChapter(TitleChapter chapter, @RequestParam("files") MultipartFile[] pages, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        if (user == null){
            return "jsonTemplate";
        }
        List<String> errorList = new ArrayList<>();
        if (chapter.getChapterName().equals("") || chapter.getChapterName() == null){
            errorList.add("ERROR_NAME_EMPTY");
        }
        if (chapter.getDateUploaded() == null){
            errorList.add("ERROR_DATE_UPLOADED_EMPTY");
        }
        if (pages.length == 0){
            errorList.add("ERROR_NO_PAGES");
        }
        if (errorList.size() == 0){
            chapter.setUploader(user);
            List<ChapterPage> pagesList = new ArrayList<>();
            for (int i = 0; i < pages.length; i ++){
                ChapterPage page = new ChapterPage();
                page.setPageNumber(i + 1);
                try {
                    page.setPagePic(pages[i].getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pagesList.add(page);
            }
            chapter.setPages(pagesList);
            titleService.saveTitleChapter(chapter);
            model.addAttribute("response", "SUCCESS");
        } else {
            model.addAttribute("response", errorList);
        }
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/add/page/commentary. На данный метод POST отправляют данные о комментарии к
     * сранице, где основные поля названы как в классе {@link pony.manga.server.entity.PageCommentary}
     * @param pageCommentary - информация о комментарии
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return Возвращает JSON со списком ошибок если такие есть: <br>
     * ERROR_TEXT_EMPTY - текс коммментария пустой <br>
     * Когда запрос успешен возвращается SUCCESS.
     */
    @PostMapping("/api/add/page/commentary")
    public String addPageCommentary(PageCommentary pageCommentary, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        if (user == null){
            return "jsonTemplate";
        }
        List<String> errorList = new ArrayList<>();
        if (pageCommentary.getCommentaryText().equals("") || pageCommentary.getCommentaryText()==null){
            errorList.add("ERROR_TEXT_EMPTY");
        }
        if (errorList.size()!=0){
            pageCommentary.setCommentator(user);
            titleService.savePageCommentary(pageCommentary);
            model.addAttribute("response", "SUCCESS");
        } else {
            model.addAttribute("response", errorList);
        }
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/add/title/review. На данный метод POST отправляют данные об отзыве к
     * произведению, где основные поля названы как в классе {@link pony.manga.server.entity.TitleReview}
     * @param review - информация об отзыве
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return Возвращает JSON со списком ошибок если такие есть: <br>
     * ERROR_TEXT_EMPTY - текс отзыва пустой <br>
     * Когда запрос успешен возвращается SUCCESS.
     */
    @PostMapping("/api/add/title/review")
    public String addTitleReview(TitleReview review, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        if (user == null){
            return "jsonTemplate";
        }
        List<String> errorList = new ArrayList<>();
        if (review.getReviewText().equals("") || review.getReviewText() == null){
            errorList.add("ERROR_TEXT_EMPTY");
        }
        if (errorList.size()!=0){
            review.setReviewer(user);
            titleService.saveTitleReview(review);
            model.addAttribute("response", "SUCCESS");
        } else {
            model.addAttribute("response", errorList);
        }
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/add/title/rating. На данный метод POST отправляют данные о рейтинге к
     * произведению, где основные поля названы как в классе {@link pony.manga.server.entity.TitleRating}
     * @param titleRating - данные о рейтинге
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return Возвращает JSON со списком ошибок если такие есть: <br>
     * ERROR_RATING_EMPTY - оценка пользователья пустая <br>
     * Когда запрос успешен возвращается SUCCESS.
     */
    @PostMapping("/api/add/title/rating")
    public String addTitleRating(TitleRating titleRating, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        if (user == null){
            return "jsonTemplate";
        }
        List<String> errorList = new ArrayList<>();
        if (titleRating.getUserRating() == 0){
            errorList.add("ERROR_RATING_EMPTY");
        }
        if (errorList.size()!=0){
            titleRating.setReviewer(user);
            titleService.saveTitleRating(titleRating);
            model.addAttribute("response", "SUCCESS");
        } else {
            model.addAttribute("response", errorList);
        }
        return "jsonTemplate";
    }
}
