package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pony.manga.server.entity.TitleChapter;

import java.util.List;

public interface TitleChapterRepository extends JpaRepository<TitleChapter, Long> {
    @Query(value = "select * from title_chapter where title_id = ?1", nativeQuery = true)
    List<TitleChapter> getTitleChaptersByTitleId(Long titleId);
}
