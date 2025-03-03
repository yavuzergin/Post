package org.yavuz.post.member.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Join;
import org.yavuz.post.comment.model.Comment;
import org.yavuz.post.post.model.Post;

import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_firstname")
    private String memberFirstName;

    @Column(name = "member_lastname")
    private String memberLastName;

    /*@OneToMany
    @JoinColumn(name = "post_id")
    private List<Post> postList;

    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<Comment> commentList;
     */
}

