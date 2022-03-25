package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
