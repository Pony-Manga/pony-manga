package pony.manga.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Данный класс отвечает за хранение информации о комментарии под страницей главы.
 * В базе данных сущности данного класса хранятся в таблице page_commentary.
 */
@Getter
@Setter
@Entity
@Table(name = "page_commentary")
public class PageCommentary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    private String commentaryText;
    private int rating;
    @ManyToOne
    private User commentator;
    @ManyToOne
    @JsonBackReference
    private ChapterPage page;
}
