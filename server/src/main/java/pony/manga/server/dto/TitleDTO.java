package pony.manga.server.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import pony.manga.server.entity.*;

import java.util.Date;
import java.util.List;

/**
 * Данный класс - DTO. В него собираются данные о произведении, которе собираются добавить на сайт.
 * Используется в методе {@link pony.manga.server.controller.TitleCreationController#addTitle(TitleDTO, Model, MultipartFile)}
 */
@Getter
@Setter
public class TitleDTO {
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    private String description;
    Author author;
    MangaType mangaType;
    TitleStatus titleStatus;
    Artist artist;
    List<TitleTag> tags;
}
