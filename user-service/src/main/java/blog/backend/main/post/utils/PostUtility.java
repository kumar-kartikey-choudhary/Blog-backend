package blog.backend.main.post.utils;

import blog.backend.main.post.dto.PostDTO;
import blog.backend.main.post.model.Post;
import org.springframework.beans.BeanUtils;

public class PostUtility {

    public static Post dtoToPost(PostDTO postDTO)
    {
        if (postDTO == null) return null;

        Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());

        // ✅ Convert UserDTO → String (username)
        if (postDTO.getAuthor() != null) {
            post.setAuthor(postDTO.getAuthor().getUsername());
        }

        return post;
    }

    public static PostDTO postToDto(Post post)
    {
        if (post == null) return null;

        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());

        // ✅ UserDTO will be fetched later from FeignClient (User service)
        postDTO.setAuthor(null);

        // ✅ Comments fetched separately (Comment service)
        postDTO.setComments(null);

        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUpdatedAt(post.getUpdatedAt());

        return postDTO;
    }
}
