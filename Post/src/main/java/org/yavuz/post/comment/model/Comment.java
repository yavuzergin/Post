package org.yavuz.post.comment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yavuz.post.post.model.Post;
import org.yavuz.post.user.model.User;

import java.time.LocalDate;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "comment_date")
    private LocalDate commentDate;

    @Column(name = "comment_text")
    private String commentText;
}
