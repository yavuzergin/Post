package org.yavuz.post.post.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yavuz.post.comment.model.Comment;
import org.yavuz.post.member.model.Member;

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
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "post_text")
    private String postText;

    @Column(name = "post_date")
    private LocalDate postDate;
/*
    @OneToMany(mappedBy = "post")
    private List<Comment> commentListonPost;
 */

}

