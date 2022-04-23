package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Данный класс отвечает за хранение информации о сообщение в треде форума.
 * В базе данных сущности данного класса хранятся в таблице forum_thread_message.
 */
@Setter
@Getter
@Entity
@Table(name = "forum_thread_message")
public class ForumThreadMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Long.valueOf(0);
    @Column(columnDefinition="TEXT")
    String messageText;
    @ManyToOne
    User author;
    @ManyToMany
    private List<ForumThread> forumThreads;
}
