package club.pard.server.ftiland.oldnew.dto.response.post;

import java.sql.Timestamp;

import club.pard.server.ftiland.oldnew.entity.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "편지를 (간단하게/자세하게) 조회할 때 받아오는 응답")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRetrieveResponse {
    @Schema(description = "편지 작성자 이름")
    private String author;

    @Schema(description = "편지 제목")
    private String title;

    @Schema(description = "편지 내용")
    private String content;

    @Schema(description = "편지 작성 시간")
    private Timestamp timeUploaded;
    // private String imagePath;

    public PostRetrieveResponse(Post post)
    {
        this.author = post.getSender();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.timeUploaded = post.getTimeUploaded();
        // this.imagePath = post.getImagePath();
    }
}
