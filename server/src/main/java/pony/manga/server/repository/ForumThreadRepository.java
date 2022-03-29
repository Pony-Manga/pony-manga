package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.ForumThread;

public interface ForumThreadRepository extends JpaRepository<ForumThread, Long> {
}
