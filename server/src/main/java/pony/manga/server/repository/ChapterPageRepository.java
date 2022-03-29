package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.ChapterPage;

public interface ChapterPageRepository extends JpaRepository<ChapterPage, Long> {
}
