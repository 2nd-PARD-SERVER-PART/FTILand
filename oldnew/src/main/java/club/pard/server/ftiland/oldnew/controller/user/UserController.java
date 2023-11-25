package club.pard.server.ftiland.oldnew.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.ftiland.oldnew.dto.request.user.UserSignInRequest;
import club.pard.server.ftiland.oldnew.dto.request.user.UserSignUpRequest;
import club.pard.server.ftiland.oldnew.dto.response.Response;
import club.pard.server.ftiland.oldnew.dto.response.user.UserSignInResponse;
import club.pard.server.ftiland.oldnew.dto.response.user.UserSignUpResponse;
import club.pard.server.ftiland.oldnew.service.user.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "user", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "사용자 회원가입 성공, 비밀번호를 제외한 사용자의 정보가 반환됩니다.",
            content = @Content(schema = @Schema(implementation = UserSignUpResponse.class))),
        @ApiResponse(responseCode = "400", description = "사용자 회원가입 실패, 발생한 예외의 메세지를 반환합니다."),
        @ApiResponse(responseCode = "500", description = "사용자 회원가입 실패, 서버 쪽에 에러가 발생한 상황이므로 서버 개발자를 문책하세요.")
    })
    public Response<UserSignUpResponse> signUp(@RequestBody UserSignUpRequest request){ return userService.signUp(request); }

    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "사용자 로그인 성공, 로그인에 성공한 사용자의 (비밀번호를 제외한) 정보가 반환됩니다.",
            content = @Content(schema = @Schema(implementation = UserSignInResponse.class))),
        @ApiResponse(responseCode = "400", description = "사용자 로그인 실패, 발생한 예외의 메세지를 반환합니다."),
        @ApiResponse(responseCode = "500", description = "사용자 로그인 실패, 서버 쪽에 에러가 발생한 상황이므로 서버 개발자를 문책하세요.")
    })
    public Response<UserSignInResponse> signIn(@RequestBody UserSignInRequest request){ return userService.signIn(request); }
}
