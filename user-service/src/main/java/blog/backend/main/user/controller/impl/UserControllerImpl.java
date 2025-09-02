package blog.backend.main.user.controller.impl;

import blog.backend.main.user.controller.UserController;
import blog.backend.main.user.dto.UserDTO;
import blog.backend.main.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users" , produces = MediaType.APPLICATION_JSON_VALUE)
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService)
    {
        this.userService = userService;
    }


    @Override
    public ResponseEntity<UserDTO> create(UserDTO userDTO) {
        return new ResponseEntity<>(this.userService.create(userDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDTO> find(String uuid) {
        return ResponseEntity.ok(this.userService.find(uuid));
    }

    @Override
    public ResponseEntity<UserDTO> findByUsername(String username) {
        return ResponseEntity.ok(this.userService.findByUsername(username));
    }
}
