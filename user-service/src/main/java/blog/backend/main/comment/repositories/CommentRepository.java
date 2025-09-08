package blog.backend.main.comment.repositories;

import blog.backend.main.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment , String> {

    List<Comment> findByPostId(String postId);

}
