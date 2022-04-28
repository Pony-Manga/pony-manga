package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pony.manga.server.entity.PageCommentary;

import java.util.List;

public interface PageCommentaryRepository extends JpaRepository<PageCommentary, Long> {
    @Query(value = "select * from page_commentary where page_id = ?1 order by id desc", nativeQuery = true)
    List<PageCommentary> getPageCommentaryByPageId(Long pageID);
}
