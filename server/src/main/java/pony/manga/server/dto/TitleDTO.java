package pony.manga.server.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import pony.manga.server.entity.*;

import javax.persistence.*;
import java.io.File;
import java.util.Date;
import java.util.List;

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
