package org.yavuz.post.comment.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AddCommentRequest {
    private Long memberId;
    private Long postId;
    private String commentText;
    private LocalDate commentDate;
}
