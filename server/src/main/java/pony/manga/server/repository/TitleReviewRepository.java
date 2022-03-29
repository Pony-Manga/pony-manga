package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.TitleReview;

public interface TitleReviewRepository extends JpaRepository<TitleReview, Long> {
}
