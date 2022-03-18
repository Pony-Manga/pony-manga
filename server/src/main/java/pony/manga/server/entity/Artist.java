package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    private String name;
    @OneToMany(mappedBy="artist")
    List<Title> titles;
}
