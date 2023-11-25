package club.pard.server.ftiland.oldnew.dto.response.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "사용자 회원가입 시 받아오는 응답")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpResponse {
    @Schema(description = "사용자 고유 번호")
    private Long id;

    @Schema(description = "사용자 ID")
    private String userid;

    @Schema(description = "사용자 이름")
    private String username;
    // private String password; // Not sending this for security purpose.
}
