package pony.manga.server.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pony.manga.server.entity.Role;
import pony.manga.server.entity.User;
import pony.manga.server.repository.RoleRepository;
import pony.manga.server.service.UserService;

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
        User user = new User();
        user.setId(1L);
        user.setMail("iesubbotin@gmail.com");
        user.setNickname("admin");
        user.setPassword(encoder.encode("123"));
        user.setActive(true);
        userService.registerUser(user);
    }

}
