package blog.backend.main.post.model;


import blog.backend.main.comment.model.Comment;
import blog.backend.main.user.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "POST_ID" , columnDefinition = "CHAR(36) NOT NULL DEFAULT(UUID())", nullable = false , unique = true)
    private String id;


    @Column(name = "CONTENT" , columnDefinition = "VARCHAR(100) NOT NULL ", nullable = false)
    private String content;

    @Column(name = "AUTHOR", columnDefinition = "NOT NULL" , unique = true, nullable = false)
    private UserDTO author;

    @Column(name = "COMMENTS")
    private List<Comment> comments;

    @Column(name = "CREATED_TIME" , columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private ZonedDateTime createdAt;

    @Column(name = "UPDATED_TIME", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private ZonedDateTime updatedAt;



}
