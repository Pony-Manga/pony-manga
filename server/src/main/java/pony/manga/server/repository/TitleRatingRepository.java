package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pony.manga.server.entity.TitleRating;

import java.util.List;

public interface TitleRatingRepository extends JpaRepository<TitleRating, Long> {
    @Query(value = "select * from title_rating where rating_to_id = ?1", nativeQuery = true)
    List<TitleRating> getTitleRatingByTitleId(Long titleId);
    @Query(value = "select * from title_rating where reviewer_id = ?1 and rating_to_id = ?2", nativeQuery = true)
    TitleRating getTitleRatingByUserId(Long userId, Long titleId);
}
