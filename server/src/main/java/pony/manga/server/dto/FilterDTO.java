package pony.manga.server.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import pony.manga.server.entity.Artist;
import pony.manga.server.entity.Author;
import pony.manga.server.entity.MangaType;
import pony.manga.server.entity.TitleTag;

import java.util.List;

/**
 * Данный класс - DTO. В него собираются данные о фильтрах, которые должны быть в произведении при выборке.
 * Используется в методе {@link pony.manga.server.controller.TitleGetInfoController#getListOfTitleByFilters(FilterDTO, Model)}
 */
@Getter
@Setter
public class FilterDTO {
    Author author;
    Artist artist;
    List<TitleTag> tags;
    MangaType mangaType;
}
