package org.yavuz.post.comment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yavuz.post.comment.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
