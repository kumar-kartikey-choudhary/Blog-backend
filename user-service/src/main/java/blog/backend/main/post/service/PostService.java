package blog.backend.main.post.service;

import blog.backend.main.post.dto.PostDTO;

import java.util.List;

public interface PostService {


    PostDTO create(PostDTO postDTO);

    List<PostDTO> getAllPosts();

    PostDTO getById(String id);

    PostDTO update(String id);

    void delete(String id);
}
