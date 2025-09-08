package blog.backend.main.comment.service.impl;

import blog.backend.main.comment.dto.CommentDto;
import blog.backend.main.comment.model.Comment;
import blog.backend.main.comment.repositories.CommentRepository;
import blog.backend.main.comment.service.CommentService;
import blog.backend.main.comment.util.CommentUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static blog.backend.main.comment.util.CommentUtils.*;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository)
    {
        this.commentRepository = commentRepository;
    }


    @Override
    public CommentDto create(CommentDto commentDto) {
        log.info("Inside @class CommentServiceImpl @method create Param commentDto :{}", commentDto);
        try{
            Objects.requireNonNull(commentDto, "Comment Object can not be null");
            if(StringUtils.isNotBlank(commentDto.getId()))
            {
                throw new IllegalCallerException("Id must be null");
            }
            Comment comment = dtoToComment(commentDto);

            comment = this.commentRepository.saveAndFlush(comment);

            return commentToDto(comment);
        }catch (Exception ex) {
            log.error("Error Inside @Class CommentServiceImpl @Method create errorMsg: {}, exceptionStackTrace: {}", ex.getMessage(), ex.getStackTrace());
            throw new IllegalCallerException("Something went wrong");
        }
    }

    @Override
    public CommentDto find(String uuid) {

        log.info("Inside @class CommentServiceImpl @method find Param uuid:{}",uuid);
        try{
            if(StringUtils.isNotBlank(uuid))
            {
                throw new IllegalCallerException("Comment Id must not be blank or empty");
            }
            Comment comment = this.commentRepository.findById(uuid).orElse(null);
            return commentToDto(comment);
        }catch (Exception ex) {
            log.error("Error Inside @Class CommentServiceImpl @Method find errorMsg: {}, exceptionStackTrace: {}", ex.getMessage(), ex.getStackTrace());
            throw new IllegalCallerException("Something went wrong");
        }
    }

    @Override
    public List<CommentDto> findByPostId(String postId) {
        log.info("Inside CommentServiceImpl @method findByPostId param: {}", postId);

        if (StringUtils.isBlank(postId)) {
            throw new IllegalArgumentException("Post ID must not be null or empty");
        }

        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream()
                .map(CommentUtils::commentToDto) // you can write your mapper here
                .toList();
    }

    @Override
    public void delete(String uuid) {
        log.info("Inside CommentServiceImpl @method delete param uuid: {}", uuid);

        if(StringUtils.isNotBlank(uuid))
        {
            throw new IllegalCallerException("Id can not be null or empty");
        }
        CommentDto commentDto = find(uuid);
        Comment comment = dtoToComment(commentDto);
        this.commentRepository.delete(comment);
    }
}
