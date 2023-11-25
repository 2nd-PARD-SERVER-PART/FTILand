package club.pard.server.ftiland.oldnew.service.post;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import club.pard.server.ftiland.oldnew.dto.request.post.PostAddRequest;
import club.pard.server.ftiland.oldnew.dto.response.Response;
import club.pard.server.ftiland.oldnew.dto.response.post.PostAddResponse;
import club.pard.server.ftiland.oldnew.dto.response.post.PostRetrieveResponse;
import club.pard.server.ftiland.oldnew.entity.post.Post;
import club.pard.server.ftiland.oldnew.entity.room.Room;
import club.pard.server.ftiland.oldnew.entity.user.User;
import club.pard.server.ftiland.oldnew.repository.room.RoomRepository;
import club.pard.server.ftiland.oldnew.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    // private final PostRepository postRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Transactional
    public Response<PostRetrieveResponse> getPost(String roomCode, Long id)
    {
        try
        {
            Room room = roomRepository.findByCode(roomCode)
                .orElseThrow(() -> new NoSuchElementException("Non-existent room"));
            Post post = room.getPosts().get(id.intValue());

            PostRetrieveResponse response = new PostRetrieveResponse(post);
            return Response.setSuccess("Successfully retrieved post", response);
        }
        catch(NoSuchElementException e){ return Response.setFailure(e.getMessage()); }
        catch(Exception e){ e.printStackTrace(); return Response.setFailure("Internal DB Error"); }
    }

    @Transactional
    public Response<PostAddResponse> addPost(PostAddRequest request)
    {
        try
        {
            String roomCode = request.getRoomCode();
            Room room = roomRepository.findByCode(roomCode)
                .orElseThrow(() -> new NoSuchElementException("Non-existent room"));
            
            String authorId = request.getAuthorId();
            String title = request.getTitle();
            String sender = request.getSender();
            String content = request.getContent();
            // String imagePath = request.getImagePath();

            if(authorId == null)
                throw new IllegalArgumentException("Invalid nickname: this should not be null");
            User author = userRepository.findByUserid(authorId)
                .orElseThrow(() -> new NoSuchElementException("Non-existent author"));
            
            if(title == null)
                throw new IllegalArgumentException("Invalid title: this should not be null");
            if(sender == null)
                throw new IllegalArgumentException("Invalid sender: this should not be null");
            if(content == null)
                throw new IllegalArgumentException("Inavlid content: this should not be null");

            // Post post = new Post(author, title, sender, content, imagePath);
            Post post = new Post(author, room, title, sender, content);
            
            room.getPosts().add(post);

            PostAddResponse response = new PostAddResponse(post);
            return Response.setSuccess("Successfully posted a new one", response);
        }
        catch(NoSuchElementException | IllegalArgumentException e){ return Response.setFailure(e.getMessage()); }
        catch(Exception e){ return Response.setFailure("Internal DB Error"); }
    }
}
