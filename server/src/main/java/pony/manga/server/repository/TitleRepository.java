package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.Title;

public interface TitleRepository extends JpaRepository<Title, Long> {
    boolean existsByName(String name);
}
