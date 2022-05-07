package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.RefreshToken;

/**
 * The interface Refresh token repository.
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
