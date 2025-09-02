package blog.backend.main.user.service;

import blog.backend.main.user.dto.UserDTO;
import org.springframework.http.HttpStatusCode;

public interface UserService {

    UserDTO create(UserDTO userDTO);

    UserDTO find(String uuid);

    UserDTO findByUsername(String username);
}
