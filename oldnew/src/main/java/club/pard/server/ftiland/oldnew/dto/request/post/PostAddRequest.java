package club.pard.server.ftiland.oldnew.dto.request.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "편지 작성 시 보내는 요청")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostAddRequest {
    @Schema(description = "편지 작성자 ID")
    private String authorId;

    @Schema(description = "공간 (초대)코드")
    private String roomCode;

    @Schema(description = "편지 제목")
    private String title;

    @Schema(description = "편지 작성자(별칭)")
    private String sender;

    @Schema(description = "편지 내용")
    private String content;
    // private String imagePath;
}
