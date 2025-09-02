package blog.backend.main.post.dto;

import blog.backend.main.comment.model.Comment;
import blog.backend.main.user.dto.UserDTO;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class PostDTO {

    private String id;
    private String content;
    private UserDTO author;
    private List<Comment> comments;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}
