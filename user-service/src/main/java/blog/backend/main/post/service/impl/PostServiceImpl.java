package blog.backend.main.post.service.impl;

import blog.backend.main.comment.controller.CommentController;
import blog.backend.main.comment.dto.CommentDto;
import blog.backend.main.post.dto.PostDTO;
import blog.backend.main.post.model.Post;
import blog.backend.main.post.repository.PostRepository;
import blog.backend.main.post.service.PostService;
import blog.backend.main.user.controller.UserController;
import blog.backend.main.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static blog.backend.main.post.utils.PostUtility.*;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserController userController;
    private final CommentController controller;


    @Autowired
    public PostServiceImpl(PostRepository postRepository , UserController userController, CommentController controller)
    {
        this.postRepository = postRepository;
        this.userController = userController;
        this.controller = controller;
    }


    @Override
    public PostDTO create(PostDTO postDTO) {
        log.info("Inside @class PostServiceImpl @method create Param postDto : {}", postDTO);
        try {
            Objects.requireNonNull(postDTO, "Post Object can not be null or empty");
            if(StringUtils.isNotBlank(postDTO.getId()))
            {
                this.update(postDTO.getId(),postDTO);
            }
            Post post = dtoToPost(postDTO);
            post = this.postRepository.saveAndFlush(post);
            log.info("Inside @Class PostServiceImpl @Method create @Param post: {}", post);
            PostDTO postDTO1 = postToDto(post);
            ResponseEntity<UserDTO> userDTOResponse = userController.findByUsername(post.getAuthor());
            if(userDTOResponse != null)
            {
                postDTO1.setAuthor(userDTOResponse.getBody());
            }
            return postDTO1;
        }catch (Exception ex) {
            log.error("Error Inside @Class PostServiceImpl @Method create errorMsg: {}, exceptionStackTrace: {}", ex.getMessage(), ex.getStackTrace());
            throw new IllegalCallerException("Something went wrong");
        }
    }

    @Override
    public List<PostDTO> getAllPosts() {
        log.info("Inside @class PostServiceImpl @method getAllPosts :");
        List<Post> all = this.postRepository.findAll();
        return all.stream().map(post -> {
            
            // convert entity to DTO
            PostDTO postDTO = postToDto(post);

            try {
                ResponseEntity<UserDTO> userResponse = userController.findByUsername(post.getAuthor());
                if (userResponse != null && userResponse.getStatusCode().is2xxSuccessful()) {
                    postDTO.setAuthor(userResponse.getBody());
                }
                ResponseEntity<List<CommentDto>> commentResponse = controller.findByPostId(post.getId());
                postDTO.setComments(commentResponse.getBody());
            } catch (Exception ex) {
                log.error("Failed to fetch author for postId: {} author: {}", post.getId(), post.getAuthor(), ex);
            }
            return postDTO;
        }).toList();
    }

    @Override
    public PostDTO getById(String id) {
        log.info("Inside @class PostServiceImpl @method getById param : {}", id);
        try {
            if(StringUtils.isBlank(id))
            {
                throw new IllegalAccessException("Id must not be null");
            }
            Post post = this.postRepository.findById(id).orElseThrow(()->new IllegalCallerException("POST_NOT_FOUND"));
            PostDTO postDTO = postToDto(post);
            try {
                assert post != null;
                ResponseEntity<UserDTO> userResponse = userController.findByUsername(post.getAuthor());
                if (userResponse != null && userResponse.getStatusCode().is2xxSuccessful()) {
                    postDTO.setAuthor(userResponse.getBody());
                }
                ResponseEntity<List<CommentDto>> commentResponse = controller.findByPostId(id);
                if (commentResponse != null && commentResponse.getStatusCode().is2xxSuccessful()) {
                    postDTO.setComments(commentResponse.getBody());
                } else {
                    postDTO.setComments(List.of()); // empty list instead of null
                }
            } catch (Exception ex) {
                log.error("Failed to fetch author for postId: {} author: {}", post.getId(), post.getAuthor(), ex);
            }
            return postDTO;
        }catch (Exception ex) {
            log.error("Error Inside @Class PostServiceImpl @Method getById errorMsg: {}, exceptionStackTrace: {}", ex.getMessage(), ex.getStackTrace());
            throw new IllegalCallerException("Something went wrong");
        }
    }

    @Override
    public PostDTO update(String id, PostDTO postDTO) {
        log.info("Inside @class PostServiceImpl @method update Param id:{} , postDto :{}", id , postDTO);
        try {
            Objects.requireNonNull(postDTO, "Post object must not be null or empty");
            if(StringUtils.isBlank(id))
            {
                throw new IllegalAccessException("Post id must not be null");
            }
            Post post = this.postRepository.findById(id).orElse(null);
            if(post == null)
            {
                throw new IllegalCallerException("POST_NOT_FOUND");
            }
            post.setContent(postDTO.getContent());
            post.setTitle(postDTO.getTitle());

            post = this.postRepository.saveAndFlush(post);
            return postToDto(post);
        }catch (Exception ex) {
            log.error("Error Inside @Class PostServiceImpl @Method update errorMsg: {}, exceptionStackTrace: {}", ex.getMessage(), ex.getStackTrace());
            throw new IllegalCallerException("Something went wrong");
        }
    }

    @Override
    public void delete(String id) {
        log.info("Inside @class PostServiceImpl @method delete Param id:{}",id);
        try {
            if(StringUtils.isBlank(id))
            {
                throw new IllegalAccessException("Post id must not be null");
            }
            Post post = this.postRepository.findById(id).orElse(null);
            if(post == null)
            {
                throw new IllegalCallerException("POST_NOT_FOUND");
            }
            this.postRepository.delete(post);
        } catch (Exception ex) {
            log.error("Error Inside @Class PostServiceImpl @Method delete errorMsg: {}, exceptionStackTrace: {}", ex.getMessage(), ex.getStackTrace());
            throw new IllegalCallerException("Something went wrong");
        }
    }
}
