package blog.backend.main.post.utils;

import blog.backend.main.post.dto.PostDTO;
import blog.backend.main.post.model.Post;
import org.springframework.beans.BeanUtils;

public class PostUtility {

    public static Post dtoToPost(PostDTO postDTO)
    {
        Post post = new Post();
        BeanUtils.copyProperties(postDTO, post);
        return post;
    }

    public static PostDTO postToDto(Post post)
    {
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        return postDTO;
    }
}
