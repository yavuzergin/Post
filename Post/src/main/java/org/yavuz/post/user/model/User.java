package org.yavuz.post.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yavuz.post.comment.model.Comment;
import org.yavuz.post.post.model.Post;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_firstname")
    private String userFirstName;
    @Column(name = "user_lastname")
    private String userLastName;
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Post> postList;
    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<Comment> commentList;

}
