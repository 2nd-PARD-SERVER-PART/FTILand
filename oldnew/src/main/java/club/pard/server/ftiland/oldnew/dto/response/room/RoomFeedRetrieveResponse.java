package club.pard.server.ftiland.oldnew.dto.response.room;

import java.sql.Timestamp;

import club.pard.server.ftiland.oldnew.entity.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "공간 속 피드 조회 시 받아오는 응답, 정확히는 공간 안에 있는 편지 개별에 대한 응답입니다.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomFeedRetrieveResponse {
    @Schema(description = "편지 작성자 이름")
    private String author;

    @Schema(description = "편지 제목")
    private String title;

    @Schema(description = "편지 내용")
    private String content;

    @Schema(description = "편지 작성 시간")
    private Timestamp timeUploaded;
    // private String imagePath;

    public RoomFeedRetrieveResponse(Post post)
    {
        this.author = post.getAuthor().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.timeUploaded = post.getTimeUploaded();
        // this.imagePath = post.getImagePath();
    }
}
