package pony.manga.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pony.manga.server.dto.UserDto;
import pony.manga.server.entity.User;
import pony.manga.server.payload.UserDtoPayload;
import pony.manga.server.repository.RoleRepository;
import pony.manga.server.service.UserService;

/**
 * The type User signup controller.
 */
@Controller
@RequestMapping("/api/signup")
public class UserSignupController {
    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    /**
     * Sign up new customer response entity.
     *
     * @param userDtoPayload the user dto payload
     * @return the response entity
     */
    @PostMapping("/")
    public ResponseEntity<?> signUpNewCustomer(@RequestBody UserDtoPayload userDtoPayload) {
        User user = new User();
        user.setMail(userDtoPayload.getEmail());
        user.setNickname(userDtoPayload.getUsername());
        user.setPassword(userDtoPayload.getPassword());
        user.setRole(roleRepository.getById(1L));
        user.setUsername(userDtoPayload.getEmail());
        user.setPasswordConfirm(userDtoPayload.getPassword());
        Boolean resp = userService.registerUser(user);
        return ResponseEntity.ok(resp);
    }


}
