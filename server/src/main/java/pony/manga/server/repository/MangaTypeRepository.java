package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.MangaType;

public interface MangaTypeRepository extends JpaRepository<MangaType, Long> {
    boolean existsByName(String name);
}
