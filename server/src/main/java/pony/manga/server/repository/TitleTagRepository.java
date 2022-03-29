package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.TitleTag;

public interface TitleTagRepository extends JpaRepository<TitleTag, Long> {
    boolean existsByName(String name);
}
