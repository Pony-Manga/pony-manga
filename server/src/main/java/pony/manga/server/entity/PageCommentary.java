package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private ChapterPage page;
}
