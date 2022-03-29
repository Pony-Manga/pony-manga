package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByMail(String mail);
}
