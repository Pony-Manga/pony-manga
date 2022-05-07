package pony.manga.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pony.manga.server.entity.Role;
import pony.manga.server.entity.User;
import pony.manga.server.exception.UserNotFoundExeption;
import pony.manga.server.repository.RoleRepository;
import pony.manga.server.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public
class UserService  {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    Logger log = LoggerFactory.getLogger("securityLogger");

    public UserService(){

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    public Optional<User> findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent())
            log.info("User " + optionalUser.get().toString() + "found by username " + username);
        else log.info("User with username '" + username + "' not found.");
        return userRepository.findByUsername(username);
    }

    /**
     * Find user by username user.
     *
     * @param username the username
     * @return the user
     */
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundExeption("User with this username not found!")
        );
    }

    /**
     * Find user by id user.
     *
     * @param userId the user id
     * @return the user
     */
    public User findUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundExeption("User with this id not found!")
        );
    }

    /**
     * Find by id optional.
     *
     * @param userId the user id
     * @return the optional
     */
    public Optional<User> findById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent())
            log.info("User " + optionalUser.get().toString() + "found by id " + userId);
        else log.info("User with id '" + userId + "' not found.");
        return userRepository.findById(userId);
    }


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByMail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean registerUser(User user) {
        User userFromDBSameMail = userRepository.findByMail(user.getUsername());

        if (userFromDBSameMail != null) {
            return false;
        }

        user.setEmail(user.getMail());
//        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setActive(true);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void saveUser(User user){userRepository.save(user);}

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
