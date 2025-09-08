package blog.backend.main.comment.controller.impl;

import blog.backend.main.comment.controller.CommentController;
import blog.backend.main.comment.dto.CommentDto;
import blog.backend.main.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CommentControllerImpl implements CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentControllerImpl(CommentService commentService)
    {
        this.commentService = commentService;
    }


    @Override
    public ResponseEntity<CommentDto> create(CommentDto commentDto) {
        return new ResponseEntity<CommentDto>(this.commentService.create(commentDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommentDto> find(String uuid) {
        return ResponseEntity.ok(this.commentService.find(uuid));
    }

    @Override
    public ResponseEntity<List<CommentDto>> findByPostId(String postId) {
        return ResponseEntity.ok(this.commentService.findByPostId(postId));
    }

    @Override
    public void delete(String uuid) {
            this.commentService.delete(uuid);
    }
}
