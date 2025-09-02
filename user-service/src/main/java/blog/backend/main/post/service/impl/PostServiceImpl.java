package blog.backend.main.post.service.impl;

import blog.backend.main.post.dto.PostDTO;
import blog.backend.main.post.model.Post;
import blog.backend.main.post.repository.PostRepository;
import blog.backend.main.post.service.PostService;
import blog.backend.main.post.utils.PostUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static blog.backend.main.post.utils.PostUtility.*;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;


    @Autowired
    public PostServiceImpl(PostRepository postRepository)
    {
        this.postRepository = postRepository;
    }


    @Override
    public PostDTO create(PostDTO postDTO) {
        log.info("Inside @class PostServiceImpl @method create Param postDto : {}", postDTO);
        try {
            Objects.requireNonNull(postDTO, "Post Object can not be null or empty");
            if(StringUtils.isBlank(postDTO.getId()))
            {
                this.update(postDTO.getId());
            }
            Post post = dtoToPost(postDTO);
            post = this.postRepository.saveAndFlush(post);
            log.info("Inside @Class PostServiceImpl @Method create @Param post: {}", post);
            return postToDto(post);
        }catch (Exception ex) {
            log.error("Error Inside @Class PostServiceImpl @Method create errorMsg: {}, exceptionStackTrace: {}", ex.getMessage(), ex.getStackTrace());
            throw new IllegalCallerException("Something went wrong");
        }
    }

    @Override
    public List<PostDTO> getAllPosts() {
        log.info("Inside @class PostServiceImpl @method getAllPosts :");
        return this.postRepository.findAll().stream().map(PostUtility::postToDto).toList();
    }

    @Override
    public PostDTO getById(String id) {
        return null;
    }

    @Override
    public PostDTO update(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
