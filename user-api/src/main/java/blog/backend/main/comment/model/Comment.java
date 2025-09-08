package blog.backend.main.comment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;


@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID" , columnDefinition = "CHAR(36) NOT NULL DEFAULT(UUID())", updatable = false,unique = true,nullable = false)
    private String id;

    @Column(name = "CONTENT", columnDefinition = "VARCHAR(1000) NOT NULL")
    private String content;

    @Column(name = "AUTHOR_NAME", columnDefinition = "CHAR(20) NOT NULL", nullable = false)
    private String author;

    @Column(name = "POST_ID", columnDefinition = "CHAR(50) NOT NULL", nullable = false)
    private String postId;

    @Column(name = "CREATED_TIME",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime createdAt;
}
