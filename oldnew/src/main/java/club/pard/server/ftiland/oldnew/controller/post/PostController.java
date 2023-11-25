package club.pard.server.ftiland.oldnew.controller.post;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.ftiland.oldnew.dto.request.post.PostAddRequest;
import club.pard.server.ftiland.oldnew.dto.response.Response;
import club.pard.server.ftiland.oldnew.dto.response.post.PostAddResponse;
import club.pard.server.ftiland.oldnew.dto.response.post.PostRetrieveResponse;
import club.pard.server.ftiland.oldnew.service.post.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "post", description = "편지 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Operation(summary = "편지 등록하기", description = "사용자가 작성한 편지를 등록합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "편지 추가 성공, 제목을 제외한 편지 번호, 편지 제목, 편지 작성자에 대한 정보를 반환합니다.",
            content = @Content(schema = @Schema(implementation = PostAddResponse.class))),
        @ApiResponse(responseCode = "400", description = "편지 추가 실패, 발생한 예외의 메세지를 반환합니다."),
        @ApiResponse(responseCode = "500", description = "편지 추가 실패, 서버 쪽에 에러가 발생한 상황이므로 서버 개발자를 문책하세요.")
    })
    @PostMapping
    public Response<PostAddResponse> addPost(@RequestBody PostAddRequest request){ return postService.addPost(request); }
    
    @Operation(summary = "편지 보기", description = "작성한 편지의 세부적인 내용을 보는 데에 사용합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "편지 조회 성공, 편지의 작성자, 제목, 내용, 게시 시간에 대한 정보를 반환합니다.",
            content = @Content(schema = @Schema(implementation = PostRetrieveResponse.class))),
        @ApiResponse(responseCode = "400", description = "편지 조회 실패, 발생한 예외의 메세지를 반환합니다."),
        @ApiResponse(responseCode = "500", description = "편지 조회 실패, 서버 쪽에 에러가 발생한 상황이므로 서버 개발자를 문책하세요.")
    })
    @GetMapping("/{roomCode}/{id}")
    public Response<PostRetrieveResponse> getPost(@PathVariable String roomCode, @PathVariable String id)
    {
        Long idLong = Long.parseLong(id);
        return postService.getPost(roomCode,idLong);
    }
}