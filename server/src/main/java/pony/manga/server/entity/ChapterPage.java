package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "chapter_page")
public class ChapterPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    private File pagePic;
    private int pageNumber;
    @OneToMany(mappedBy = "page")
    List<PageCommentary> commentaries;
    @ManyToOne
    TitleChapter chapter;
}
