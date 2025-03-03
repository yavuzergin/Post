package org.yavuz.post.post.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yavuz.post.comment.model.Comment;
import org.yavuz.post.user.model.User;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "post_text")
    private String postText;
    @Column(name = "post_date")
    private LocalDate postDate;
    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<Comment> commentList;




}

