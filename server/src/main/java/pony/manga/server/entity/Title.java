package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "title")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    private String name;
    private String logoPath;
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
    @ManyToMany(mappedBy = "titles")
    List<TitleTag> tags;
    @ManyToMany(mappedBy="favourite")
    List<User> userAddedToFavourite;

}
