package org.yavuz.post.post.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yavuz.post.post.model.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
}
