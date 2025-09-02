package blog.backend.main.post.dto;

import blog.backend.main.comment.dto.CommentDto;
import blog.backend.main.comment.model.Comment;
import blog.backend.main.user.dto.UserDTO;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class PostDTO {

    private String id;
    private String title;
    private String content;
    private UserDTO author;
    private List<CommentDto> comments;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}
