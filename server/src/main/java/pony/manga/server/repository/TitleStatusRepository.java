package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.TitleStatus;

public interface TitleStatusRepository extends JpaRepository<TitleStatus, Long> {
    boolean existsByName(String name);
}
