package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.PageCommentary;

public interface PageCommentaryRepository extends JpaRepository<PageCommentary, Long> {
}
