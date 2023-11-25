package club.pard.server.ftiland.oldnew.dto.request.room;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "공간 입장 시 보내는 요청")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomJoinRequest {
    @Schema(description = "사용자 ID")
    private String userid;

    @Schema(description = "공간 (초대)코드")
    private String roomCode;
}
