package club.pard.server.ftiland.oldnew.dto.response.room;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "공간 생성 시 받아오는 응답")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomCreateResponse {
    @Schema(description = "공간 (초대)코드")
    private String roomCode;
}
