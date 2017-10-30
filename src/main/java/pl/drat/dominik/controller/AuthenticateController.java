package pl.drat.dominik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.drat.dominik.dao.entity.User;
import pl.drat.dominik.dto.request.UserAuthenticationDTO;
import pl.drat.dominik.dto.response.UserDTO;
import pl.drat.dominik.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("authenticate")
public class AuthenticateController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> authenticate(@RequestBody UserAuthenticationDTO userToAuthorizate) {
        User existingUser = userService.getByEmail(userToAuthorizate.getEmail());
        if (isAuthenticated(userToAuthorizate, existingUser)) {

            return new ResponseEntity<>(existingUser.toDTO(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    private boolean isAuthenticated(UserAuthenticationDTO userToAuthenticate, User existingUser) {
        if (existingUser == null) return false;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(userToAuthenticate.getPassword(), existingUser.getPassword());
    }
}
