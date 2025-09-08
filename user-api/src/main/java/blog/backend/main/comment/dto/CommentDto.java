package blog.backend.main.comment.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CommentDto {

    private String id;
    private String content;
    private String author;
    private String postId;
    private ZonedDateTime createdAt;
}
