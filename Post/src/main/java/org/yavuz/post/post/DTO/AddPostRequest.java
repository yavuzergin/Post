package org.yavuz.post.post.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AddPostRequest {
    private Long memberId;
    private String postText;
    private LocalDate postDate;
}
