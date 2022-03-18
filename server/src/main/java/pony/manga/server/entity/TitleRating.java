package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "title_rating")
public class TitleRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    int userRating;
    @ManyToOne
    User reviewer;
    @ManyToOne
    Title ratingTo;
}
