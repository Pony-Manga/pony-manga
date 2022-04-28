package pony.manga.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pony.manga.server.dto.FilterDTO;
import pony.manga.server.entity.TitleRating;
import pony.manga.server.service.TitleService;

import java.io.File;
import java.util.List;

/**
 * Данный контроллер выдает ответы на запросы по поиску информации о произведениях и их составляющих
 */
@Controller
public class TitleGetInfoController {
    @Autowired
    TitleService titleService;

    /**
     * Метод с эндпоинтом /api/get/author/list. Ответом на данный запрос будет список всех авторов в базе.
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return - JSON со списком авторов {@link pony.manga.server.entity.Author}
     */
    @GetMapping("/api/get/author/list")
    public String getListOfAuthors(Model model){
        model.addAttribute("result", titleService.getAuthors());
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/author/titles. Ответом на данный запрос будет список произведений с определенным автором.
     * @param id - id автора
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return - JSON со списком произведений {@link pony.manga.server.entity.Title}
     */
    @GetMapping("/api/get/author/titles")
    public String getListOfAuthorTitles(@RequestParam("authorId") Long id, Model model){
        model.addAttribute("result", titleService.getTitlesByAuthor(id));
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/artist/list. Ответом на запос будет список всех худодников в базе.
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return - JSON со списком художников {@link pony.manga.server.entity.Artist}
     */
    @GetMapping("/api/get/artist/list")
    public String getListOfArtists(Model model){
        model.addAttribute("result", titleService.getArtists());
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/artist/titles. Ответом на запрос будет список произведений с определенным художником.
     * @param id - id художника
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON со списком произведений {@link pony.manga.server.entity.Title}
     */
    @GetMapping("/api/get/artist/titles")
    public String getListOfArtistTitles(@RequestParam("artistId") Long id, Model model){
        model.addAttribute("result", titleService.getTitlesByArtist(id));
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/manga-type/list. Ответом на запрос будет список всех типов произведений.
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON со списком типов произведений {@link pony.manga.server.entity.MangaType}
     */
    @GetMapping("/api/get/manga-type/list")
    public String getListOfMangaType(Model model){
        model.addAttribute("result", titleService.getMangaTypes());
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/manga-type/titles. Ответом на запрос будет список произведений с определенным типом
     * @param id - id типа
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON со списком произведений {@link pony.manga.server.entity.Title}
     */
    @GetMapping("/api/get/manga-type/titles")
    public String getListOfMangaTypeTitles(@RequestParam("typeId") Long id, Model model){
        model.addAttribute("result", titleService.getTitleByType(id));
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/title-status/list. Ответом на запрос будет список всех статусов произведения
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON со списком всех статусов {@link pony.manga.server.entity.TitleStatus}
     */
    @GetMapping("/api/get/title-status/list")
    public String getListOfTitleStatus(Model model){
        model.addAttribute("result", titleService.getTitleStatuses());
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/title-status/titles. Ответом на запрос будет список всех произведений с определеннм
     * статусом
     * @param id - id статуса
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON со списком произведений {@link pony.manga.server.entity.Title}
     */
    @GetMapping("/api/get/title-status/titles")
    public String getListOfTitleStatusTitles(@RequestParam("statusId") Long id, Model model){
        model.addAttribute("result", titleService.getTitlesByStatus(id));
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/tag/list. Ответом на запрос будет список всех тегов в базы
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON со списком тегов {@link pony.manga.server.entity.TitleTag}
     */
    @GetMapping("/api/get/tag/list")
    public String getListOfTag(Model model){
        model.addAttribute("result", titleService.getTitleTags());
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоином /api/get/tag/titles. Ответом на запрос будет список произведений с определенным тегом
     * @param id - id тега
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON со списком произведений {@link pony.manga.server.entity.Title}
     */
    @GetMapping("/api/get/tag/titles")
    public String getListOfTagTitles(@RequestParam("tagId") Long id, Model model){
        model.addAttribute("result", titleService.getTitlesByTag(id));
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/title/list/by-name. Ответом на запрос будет список произведений, в название которых
     * входит некая подстрока
     * @param name - подстрока для поиска
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON со списком произведений {@link pony.manga.server.entity.Title}
     */
    @GetMapping("/api/get/title/list/by-name")
    public String getListOfTitlesByName(@RequestParam("name") String name, Model model){
        model.addAttribute("result", titleService.getTitlesByName(name));
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/title/list/by-filter. Ответом на запрос будет список произведений, которые соответсвуют
     * выбранным фильтрам
     * @param filterDTO - содержит информацию по фильтрам
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON со списком произведений {@link pony.manga.server.entity.Title}
     */
    @GetMapping("/api/get/title/list/by-filter")
    public String getListOfTitleByFilters(FilterDTO filterDTO, Model model){
        model.addAttribute("result", titleService.getTitlesByFilters(filterDTO));
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/title/chapters. Ответом на запрос будет список глав конкретного произведения
     * @param id - id произведения
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON со списком произведений {@link pony.manga.server.entity.TitleChapter}
     */
    @GetMapping("/api/get/title/chapters")
    public String getListOfTitleChapters(@RequestParam("titleId") Long id, Model model){
        model.addAttribute("result", titleService.getTitleChapters(id));
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/chapter/page. Ответом на запрос будет страница под конкретным номера контретной главы
     * @param id - id главы
     * @param pageNumber - номер страницы
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON с информацией о странице {@link pony.manga.server.entity.ChapterPage}
     */
    @GetMapping("/api/get/chapter/page")
    public String getListOfChapterPages(@RequestParam("chapterId") Long id, @RequestParam("pageNumber") int pageNumber
            , Model model){
        model.addAttribute("result", titleService.getChapterPage(pageNumber, id));
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/chapter/pages/count. Возвращает число страниц в главе.
     * @param id - id главы
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON с числом страниц в главе
     */
    @GetMapping("/api/get/chapter/pages/count")
    public String getCountOfChapterPages(@RequestParam("chapterId") Long id, Model model){
        model.addAttribute("result", titleService.getChapterPageCount(id));
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/page/commentaries. Возвращает список комментариев под страницей.
     * @param id - id страницы
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON со списком комментариев {@link pony.manga.server.entity.PageCommentary}
     */
    @GetMapping("/api/get/page/commentaries")
    public String getPageCommentaries(@RequestParam("pageId") Long id, Model model){
        model.addAttribute("result", titleService.getPageCommentaries(id));
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/title/rating. Возвращает числовое значение  рейтинга произведения.
     * @param id - id произведения
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON с числовым значением рейтинга
     */
    @GetMapping("/api/get/title/rating")
    public String getTitleRating(@RequestParam("titleId") Long id, Model model){
        List<TitleRating> ratings = titleService.getTitleRatings(id);
        float rating = 0;
        if (ratings.size()!= 0){
            for (int i = 0; i < ratings.size(); i++){
                rating += ratings.get(i).getUserRating();
            }
            rating/=ratings.size();
        }
        model.addAttribute("result", rating);
        return "jsonTemplate";
    }

    /**
     * Метод с эндпоинтом /api/get/title/reviews. Метод возвращает список всех обзоров на произведение.
     * @param id - id произведения
     * @param model - модель из Spring, в нее заносится ответ сервера. Его подбирает jsonTemplate и отправляет как ответ
     * @return JSON со списком отзывов {@link pony.manga.server.entity.TitleReview}
     */
    @GetMapping("/api/get/title/reviews")
    public String getTitleReview(@RequestParam("titleId") Long id, Model model){
        model.addAttribute("result", titleService.getTitleReviews(id));
        return "jsonTemplate";
    }
}
