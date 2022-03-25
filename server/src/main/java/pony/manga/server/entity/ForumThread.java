package pony.manga.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
