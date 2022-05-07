package pony.manga.server.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import pony.manga.server.entity.Role;
import pony.manga.server.entity.User;
import pony.manga.server.repository.RoleRepository;
import pony.manga.server.service.UserService;


/**
 * Данный класс запускает параллельный процесс, который добавляет в базу данных стандартные роли приложения.
 */
@Component
public class RoleCreation implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public void run(String... strings) throws Exception{
        roleRepository.save(new Role(1L, "ROLE_USER"));
        roleRepository.save(new Role(2L, "ROLE_ADMINISTRATOR"));
    }

}
