package pony.manga.server.entity;

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
@Table(name = "title")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    private String name;
    @Lob
    @Column(columnDefinition="BLOB")
    @Type(type="org.hibernate.type.BinaryType")
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
    List<TitleReview> reviews;
    @OneToMany(mappedBy = "ratingTo")
    List<TitleRating> ratings;
    @OneToMany(mappedBy = "title")
    List<TitleChapter> chapters;
    @ManyToMany(mappedBy = "titles")
    List<TitleTag> tags;
    @ManyToMany(mappedBy="favourite")
    List<User> userAddedToFavourite;

}
