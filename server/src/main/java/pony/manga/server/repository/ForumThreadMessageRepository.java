package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.ForumThreadMessage;

public interface ForumThreadMessageRepository extends JpaRepository<ForumThreadMessage, Long> {
}
