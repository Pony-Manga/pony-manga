package pony.manga.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Данный класс отвечает за хранение информации о текущем состоянии манги(произведении). Например, "Выход приостановлен".
 * В базе данных сущности данного класса хранятся в таблице title_status.
 */
@Setter
@Getter
@Entity
@Table(name = "title_status")
public class TitleStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    private String name;
    @OneToMany(mappedBy = "titleStatus")
    @JsonBackReference
    List<Title> titles;
}
