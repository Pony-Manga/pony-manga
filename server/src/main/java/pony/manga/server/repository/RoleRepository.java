package pony.manga.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pony.manga.server.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
