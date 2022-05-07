package pony.manga.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByMail(String mail);
    Optional<User> findByUsername(String username);

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findByEmail(String email);
}
