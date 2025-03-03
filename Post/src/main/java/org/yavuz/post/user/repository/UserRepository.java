package org.yavuz.post.user.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yavuz.post.user.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
