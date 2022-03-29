package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.TitleChapter;

import java.util.List;

public interface TitleChapterRepository extends JpaRepository<TitleChapter, Long> {
}
