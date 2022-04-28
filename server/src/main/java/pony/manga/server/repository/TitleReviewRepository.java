package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pony.manga.server.entity.TitleReview;

import java.util.List;

public interface TitleReviewRepository extends JpaRepository<TitleReview, Long> {
    @Query(value = "select * from title_review where reviewer_id = ?1 and review_to_id = ?2",nativeQuery = true)
    TitleReview getTitleReviewByUserId(Long userId, Long titleId);
    @Query(value = "select * from title_review where review_to_id = ?1", nativeQuery = true)
    List<TitleReview> getTitleReviewByTitleId(Long titleId);
}
