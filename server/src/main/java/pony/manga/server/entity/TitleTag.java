package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Данный класс отвечает за хранение информацию о тегах, которые можно присвоить манге(произведению).
 * В базе данных сущности данного класса хранятся в таблице title_tag.
 */
@Getter
@Setter
@Entity
@Table(name = "title_tag")
public class TitleTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    private String name;
    @ManyToMany()
    List<Title> titles;
}
