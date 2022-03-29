package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.TitleRating;

public interface TitleRatingRepository extends JpaRepository<TitleRating, Long> {
}
