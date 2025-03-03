package org.yavuz.post.post.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPostRequest {
    private Long memberId;
    private String postText;
    private LocalDate postDate;
}
