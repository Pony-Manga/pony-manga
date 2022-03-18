package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "title_review")
@Entity
public class TitleReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    @Column(columnDefinition="TEXT")
    String reviewText;
    @ManyToOne
    User reviewer;
    @ManyToOne
    Title reviewTo;
}
