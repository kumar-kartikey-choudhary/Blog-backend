package blog.backend.main.user.dto;


import blog.backend.main.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String uuid;
    private String username;
    private Role role;
    private String email;
    private String password;
}
