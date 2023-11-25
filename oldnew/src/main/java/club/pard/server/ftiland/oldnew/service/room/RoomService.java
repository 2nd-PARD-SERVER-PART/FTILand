package club.pard.server.ftiland.oldnew.service.room;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.stereotype.Service;

import club.pard.server.ftiland.oldnew.dto.request.room.RoomJoinRequest;
import club.pard.server.ftiland.oldnew.dto.response.Response;
import club.pard.server.ftiland.oldnew.dto.response.room.RoomCreateResponse;
import club.pard.server.ftiland.oldnew.dto.response.room.RoomJoinResponse;
import club.pard.server.ftiland.oldnew.dto.response.room.RoomFeedRetrieveResponse;
import club.pard.server.ftiland.oldnew.entity.post.Post;
import club.pard.server.ftiland.oldnew.entity.room.Room;
import club.pard.server.ftiland.oldnew.entity.user.User;
import club.pard.server.ftiland.oldnew.repository.room.RoomRepository;
import club.pard.server.ftiland.oldnew.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Transactional
    public Response<RoomCreateResponse> createRoom()
    {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        String generatedCode = new String();
        do
        {
            generatedCode = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        }while(roomRepository.existsByCode(generatedCode));
        
        roomRepository.save(new Room(generatedCode));
        
        RoomCreateResponse response = new RoomCreateResponse(generatedCode);
        return Response.setSuccess("Successfully generated room code", response);
    }

    @Transactional
    public Response<RoomJoinResponse> joinRoom(RoomJoinRequest request)
    {
        String targetRoomCode = request.getRoomCode();
        String targetuserid = request.getUserid();
        try
        {
            Room room = roomRepository.findByCode(targetRoomCode)
                .orElseThrow(() -> new NoSuchElementException("Invalid/Non-existent room."));
            User user = userRepository.findByUserid(targetuserid)
                .orElseThrow(() -> new NoSuchElementException("Invalid/Non-existent user."));

            room.getUsers().add(user);
            

            RoomJoinResponse response = new RoomJoinResponse(targetRoomCode);

            return Response.setSuccess("Successfully joined to the room", response);
        }
        catch(NoSuchElementException e){ return Response.setFailure(e.getMessage()); }
        catch(Exception e){ return Response.setFailure("Internal DB error."); }
    }

    public Response<List<RoomFeedRetrieveResponse>> getRoomFeed(String roomCode)
    {
        List<RoomFeedRetrieveResponse> response = new ArrayList<>();
        try
        {
            Room room = roomRepository.findByCode(roomCode)
                .orElseThrow(() -> new NoSuchElementException("Invalid/Non-existent room."));
            for(Post post: room.getPosts())
                response.add(new RoomFeedRetrieveResponse(post));

            return Response.setSuccess("Successfully retrieved room feed", response);
        }
        catch(NoSuchElementException e){ return Response.setFailure(e.getMessage()); }
        catch(Exception e){ return Response.setFailure("Internal DB Error"); }
    }
}
