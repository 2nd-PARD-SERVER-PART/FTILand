package club.pard.server.ftiland.oldnew.controller.room;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.ftiland.oldnew.dto.request.room.RoomJoinRequest;
import club.pard.server.ftiland.oldnew.dto.response.Response;
import club.pard.server.ftiland.oldnew.dto.response.room.RoomCreateResponse;
import club.pard.server.ftiland.oldnew.dto.response.room.RoomJoinResponse;
import club.pard.server.ftiland.oldnew.dto.response.room.RoomFeedRetrieveResponse;
import club.pard.server.ftiland.oldnew.service.room.RoomService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "room", description = "공간 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "공간 초대코드 생성 성공. 초대코드가 담긴 정보를 반환합니다.",
            content = @Content(schema = @Schema(implementation = RoomCreateResponse.class))),
    })
    public Response<RoomCreateResponse> createRoom(){ return roomService.createRoom(); }

    @PatchMapping()
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "공간 초대 성공, 초대된 공간의 초대코드가 담긴 정보를 반환합니다.",
            content = @Content(schema = @Schema(implementation = RoomJoinResponse.class))),
        @ApiResponse(responseCode = "400", description = "공간 초대 실패, 발생한 예외의 메세지를 반환합니다."),
        @ApiResponse(responseCode = "500", description = "공간 초대 실패, 서버 쪽에 에러가 발생한 상황이므로 서버 개발자를 문책하세요.")
    })
    public Response<RoomJoinResponse> joinRoom(@RequestBody RoomJoinRequest request){ return roomService.joinRoom(request); }

    @GetMapping("/{roomCode}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "공간 조회 성공, 공간에 존재하는 편지들의 정보를 반환합니다.",
            content = @Content(schema = @Schema(implementation = RoomFeedRetrieveResponse.class))),
        @ApiResponse(responseCode = "400", description = "공간 조회 실패, 발생한 예외의 메세지를 반환합니다."),
        @ApiResponse(responseCode = "500", description = "공간 조회 실패, 서버 쪽에 에러가 발생한 상황이므로 서버 개발자를 문책하세요.")
    })
    public Response<List<RoomFeedRetrieveResponse>> getRoomFeed(@PathVariable String roomCode){ return roomService.getRoomFeed(roomCode); }
}
