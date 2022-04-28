package pony.manga.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Данный класс отвечает за хранение информации о манге (произведении).
 * В базе данных сущности данного класса хранятся в таблице title.
 */
@Getter
@Setter
@Entity
@Table(name = "titles")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    private String name;
    private byte[] logo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    @Column(columnDefinition="TEXT")
    private String description;
    @ManyToOne
    Author author;
    @ManyToOne
    MangaType mangaType;
    @ManyToOne
    TitleStatus titleStatus;
    @ManyToOne
    Artist artist;
    @ManyToOne
    User uploader;
    private int rating;
    @OneToMany(mappedBy="reviewTo")
    @JsonBackReference
    List<TitleReview> reviews;
    @OneToMany(mappedBy = "ratingTo")
    @JsonBackReference
    List<TitleRating> ratings;
    @OneToMany(mappedBy = "title")
    @JsonBackReference
    List<TitleChapter> chapters;
    @ManyToMany(mappedBy = "titles")
    List<TitleTag> tags;
    @ManyToMany(mappedBy="favourite")
    @JsonBackReference
    List<User> userAddedToFavourite;

}
