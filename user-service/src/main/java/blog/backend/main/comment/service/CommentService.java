package blog.backend.main.comment.service;

import blog.backend.main.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto create(CommentDto commentDto);

    CommentDto find(String uuid);

    List<CommentDto> findByPostId(String postId);

    void delete(String uuid);
}
