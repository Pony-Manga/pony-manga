package pony.manga.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Данный класс отвечает за хранение информации о типах манги (произведений) на сайте.
 * В базе данных сущности данного класса хранятся в таблице manga_type.
 */
@Getter
@Setter
@Entity
@Table(name = "manga_type")
public class MangaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    private String name;
    @OneToMany(mappedBy = "mangaType")
    @JsonBackReference
    List<Title> titles;
}
