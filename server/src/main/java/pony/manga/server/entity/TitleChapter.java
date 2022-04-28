package pony.manga.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Данный класс отвечает за хранение информации о главе произведения.
 * В базе данных сущности данного класса хранятся в таблице title_chapter.
 */
@Getter
@Setter
@Entity
@Table(name = "title_chapter")
public class TitleChapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    private String chapterName;
    @ManyToOne
    private User uploader;
    @ManyToOne
    private Title title;
    private Date dateUploaded;
    @ManyToMany(mappedBy = "unreadChapters")
    @JsonBackReference
    List<User> unreadByUsers;
    @OneToMany(mappedBy = "chapter")
    @JsonBackReference
    List<ChapterPage> pages;
}
