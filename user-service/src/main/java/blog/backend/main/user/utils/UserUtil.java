package blog.backend.main.user.utils;

import blog.backend.main.user.dto.UserDTO;
import blog.backend.main.user.model.User;
import org.springframework.beans.BeanUtils;

public class UserUtil {
    public static UserDTO userToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    public static User dtoToUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

    public static UserDTO userToDTO(User user, String... exclusion) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO, exclusion);
        return userDTO;
    }

    public static User dtoToUser(UserDTO userDTO, String... exclusion) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user, exclusion);
        return user;
    }
}
