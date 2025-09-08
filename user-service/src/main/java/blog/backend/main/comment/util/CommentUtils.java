package blog.backend.main.comment.util;

import blog.backend.main.comment.dto.CommentDto;
import blog.backend.main.comment.model.Comment;
import org.springframework.beans.BeanUtils;

public class CommentUtils {

    public static CommentDto commentToDto(Comment comment)
    {
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(comment, commentDto);
        return commentDto;
    }


    public static Comment dtoToComment(CommentDto commentDto)
    {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        return comment;
    }
}
