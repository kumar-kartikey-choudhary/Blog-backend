package blog.backend.main.post.repository;

import blog.backend.main.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post , String> {
}
