package blog.backend.main.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID", columnDefinition = "VARCHAR(36) NOT NULL DEFAULT (UUID())" , unique = true, nullable = false)
    private String userId;

    @Column(name = "USERNAME" , columnDefinition = "VARCHAR(20) NOT NULL" , nullable = false , unique = true)
    private String username;

    @Column(name = "ROLE" , columnDefinition = "VARCHAR(20) NOT NULL" , nullable = false)
    private Role role;

    @Column(name = "EMAIL" , columnDefinition = "VARCHAR(30) NOT NULL" , unique = true , nullable = false)
    private String email;

    @Column(name = "PASSWORD" , columnDefinition = "VARCHAR(20) NOT NULL" , nullable = false)
    private String password;
}
