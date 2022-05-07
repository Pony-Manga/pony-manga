package pony.manga.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import pony.manga.server.dto.AuthenticationRequestDto;
import pony.manga.server.dto.JwtAuthDto;
import pony.manga.server.entity.User;
import pony.manga.server.exception.JwtInvalidRefreshTokenException;
import pony.manga.server.security.JwtTokenProvider;
import pony.manga.server.service.UserService;

/**
 * The type Authentication controller.
 */
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Login response entity.
     *
     * @param authenticationRequestDto the authentication request dto
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<JwtAuthDto> login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        try {
            User user = userService.findUserByUsername(authenticationRequestDto.getEmail());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), authenticationRequestDto.getPassword()));

            String accessToken = jwtTokenProvider.createAccessToken(user);
            String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());

            JwtAuthDto jwtAuthDto = new JwtAuthDto();
            jwtAuthDto.setUsername(user.getUsername());
            jwtAuthDto.setAccessToken(accessToken);
            jwtAuthDto.setRefreshToken(refreshToken);

            return ResponseEntity.ok(jwtAuthDto);
        }
        catch (IllegalArgumentException e) {
            throw new JwtInvalidRefreshTokenException("");
        }
    }

    /**
     * Refresh response entity.
     *
     * @param tokenPairToRefresh the token pair to refresh
     * @return the response entity
     */
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthDto> refresh(@RequestBody JwtAuthDto tokenPairToRefresh) {
        JwtAuthDto jwtAuthDto = jwtTokenProvider.refreshPairOfTokens(tokenPairToRefresh.getRefreshToken());
        return ResponseEntity.ok(jwtAuthDto);
    }

    /**
     * Activate user response entity.
     *
     * @param activationCode the activation code
     * @return the response entity
     */
    @GetMapping("/activate/{activationCode}")
    public ResponseEntity activateUser(@PathVariable String activationCode) {
//        userService.activateUser(activationCode);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
