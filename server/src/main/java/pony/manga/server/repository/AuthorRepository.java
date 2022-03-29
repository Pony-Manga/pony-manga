package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByName(String name);
}
