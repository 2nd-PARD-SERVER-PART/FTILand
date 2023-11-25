package club.pard.server.ftiland.oldnew.service.user;

import org.springframework.stereotype.Service;

import club.pard.server.ftiland.oldnew.dto.request.user.UserSignInRequest;
import club.pard.server.ftiland.oldnew.dto.request.user.UserSignUpRequest;
import club.pard.server.ftiland.oldnew.dto.response.Response;
import club.pard.server.ftiland.oldnew.dto.response.user.UserSignUpResponse;
import club.pard.server.ftiland.oldnew.dto.response.user.UserSignInResponse;
import club.pard.server.ftiland.oldnew.entity.user.User;
import club.pard.server.ftiland.oldnew.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Response<UserSignUpResponse> signUp(UserSignUpRequest request)
    {
        String userid = request.getUserid();
        String username = request.getUsername();
        String password = request.getPassword();
        String passwordCheck = request.getPasswordCheck();

        if(userid == null)
            return Response.setFailure("Invalid user nickname: this should not be null");
        else if(userRepository.existsByUserid(userid))
            return Response.setFailure("Inavlid user nickname: the nickname already exists");
        if(username == null)
            return Response.setFailure("Invalid username: this should not be null");
        if(password == null)
            return Response.setFailure("Inavlid password: this should not be null");
        if(!password.equals(passwordCheck))
            return Response.setFailure("Inavlid password: the first and second password does not match");

        User user = new User(userid, username, password);
        userRepository.save(user);

        UserSignUpResponse response = new UserSignUpResponse(user.getId(), userid, username);
        return Response.setSuccess("Successfully created user", response);        
    }

    @Transactional
    public Response<UserSignInResponse> signIn(UserSignInRequest request)
    {
        String userid = request.getUserid();
        String password = request.getPassword();

        if(!userRepository.existsByUserid(userid))
            return Response.setFailure("Non-existent user nickname");
        if(password == null)
            return Response.setFailure("Invalid password: this should not be null");

        User user = userRepository.findByUserid(userid).get();
        if(!user.getPassword().equals(password))
            return Response.setFailure("Invalid password");
    

        UserSignInResponse response = new UserSignInResponse(user);

        return Response.setSuccess("Successfully signed In", response);
    }
}
