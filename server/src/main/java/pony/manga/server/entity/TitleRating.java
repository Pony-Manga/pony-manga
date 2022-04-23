package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Данный класс отвечает за хранение информации об оценке, которую оставил пользователь манге (произведению).
 * В базе данных сущности данного класса хранятся в таблице title_rating.
 */
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
