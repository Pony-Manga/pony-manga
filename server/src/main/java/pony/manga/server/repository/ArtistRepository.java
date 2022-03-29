package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    boolean existsByName(String name);
}
