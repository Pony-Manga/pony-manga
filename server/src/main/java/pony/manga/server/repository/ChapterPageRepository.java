package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pony.manga.server.entity.ChapterPage;

public interface ChapterPageRepository extends JpaRepository<ChapterPage, Long> {
    @Query(value = "select * from chapter_page where page_number = ?1 and chapter_id = ?2", nativeQuery = true)
    ChapterPage getChapterPageByPageNumber(int pageNumber, Long chapterId);
    @Query(value = "select count(*) from chapter_page where chapter_id = ?1", nativeQuery = true)
    Long  getChapterCount(Long chapterId);
}
