package blog.backend.main.post.controllerImpl;

import blog.backend.main.post.controller.PostController;
import blog.backend.main.post.dto.PostDTO;
import blog.backend.main.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PostControllerImpl implements PostController {

    private final PostService postService;


    @Autowired
    public PostControllerImpl(PostService postService)
    {
        this.postService = postService;
    }


    @Override
    public ResponseEntity<PostDTO> create(PostDTO postDTO) {
        return new ResponseEntity<PostDTO>(this.postService.create(postDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return new ResponseEntity<List<PostDTO>>(this.postService.getAllPosts(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDTO> getById(String id) {
        return ResponseEntity.ok(this.postService.getById(id));
    }

    @Override
    public ResponseEntity<PostDTO> update(String id, PostDTO postDTO) {
        return ResponseEntity.ok(this.postService.update(id, postDTO));
    }

    @Override
    public void delete(String id) {
        this.postService.delete(id);
    }


}
