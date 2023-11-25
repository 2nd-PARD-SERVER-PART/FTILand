package club.pard.server.ftiland.oldnew.dto.response.post;

import club.pard.server.ftiland.oldnew.entity.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "편지 추가 요청에 대한 응답")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostAddResponse {
    @Schema(description = "편지 고유 번호")
    private Long id;

    @Schema(description = "편지 제목")
    private String title;

    @Schema(description = "편지 작성자 ID")
    private String sender;

    public PostAddResponse(Post post)
    {
        this.id = post.getId();
        this.title = post.getTitle();
        this.sender = post.getSender();
    }
}
