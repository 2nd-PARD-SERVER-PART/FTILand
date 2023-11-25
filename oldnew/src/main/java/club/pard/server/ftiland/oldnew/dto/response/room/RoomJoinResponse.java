package club.pard.server.ftiland.oldnew.dto.response.room;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "공간 입장 시 받아오는 응답")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomJoinResponse {
    @Schema(description = "공간 (초대)코드")
    private String code;
}
