package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Данный класс отвечает за хранение информации о треде на форуме.
 * В базе данных сущности данного класса хранятся в таблице forum_thread.
 */
@Getter
@Setter
@Entity
@Table(name = "forum_thread")
public class ForumThread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    private String threadTitle;
    private String threadText;
    @ManyToOne
    private User author;
    @ManyToMany(mappedBy = "forumThreads")
    List<ForumThreadMessage> messageList;
}
