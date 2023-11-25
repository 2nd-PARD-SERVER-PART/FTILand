package club.pard.server.ftiland.oldnew.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "사용자 로그인 시 보내는 요청")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInRequest {
    @Schema(description = "사용자 ID")
    private String userid;

    @Schema(description = "사용자 비밀번호")
    private String password;
}
