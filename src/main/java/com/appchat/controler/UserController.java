package com.appchat.controler;

import com.appchat.component.UserManager;
import com.appchat.model.data.UpdateAvatar;
import com.appchat.model.request.AddFriendRequest;
import com.appchat.model.request.LastMess;
import com.appchat.model.request.LoginRequest;
import com.appchat.model.request.RegisterRequest;
import com.appchat.model.response.AddFriendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserManager userManager;

    @PostMapping(path = "/login")
    public Object login(
            @RequestBody LoginRequest login) {
        return userManager.login(login);
    }

    @PostMapping(path = "/register")
    public Object register(
            @RequestBody RegisterRequest registerRequest){
        return userManager.register(registerRequest);
    }

    @GetMapping(path = "/getAllFriend")
    public Object getAllFriend(
            @RequestParam int userId
    ) {
        return userManager.getAllFriends(userId);
    }

    @GetMapping(path = "/getHistoryChat")
    public Object getHistoryChat(
            @RequestParam("senderId") int senderId,
            @RequestParam("receiverId") int receiverId
    ) {
        return userManager.getHistoryChat(senderId, receiverId);
    }

    @PostMapping(path = "getAllLastMess")
    public Object getAllLastMess(
            @RequestBody List<LastMess> lastMess
    ){
        return userManager.getAllLastMess(lastMess);
    }
    @GetMapping(path="/getAllFriendStory")
    public Object getAllFriendStory(
            @RequestParam int userId
    ){
        return userManager.getAllFriendStoryChat(userId);
    }
    @PostMapping(path = "/changeAvatar")
    public Object changeAvatar(
            @RequestBody UpdateAvatar updateAvatar
            ){
        return userManager.changeAvatar(updateAvatar);
    }

    @GetMapping(path = "getFriendSendedMess")
    public Object getFriendSendedMess(
            @RequestParam("userId") int userId
    ){
        return userManager.getSenderMess(userId);
    }

    @GetMapping(path = "getAllNotFriend")
    public Object getAllNotFriend(
            @RequestParam("userId") int userId
    )
    {
        return userManager.findAllNotFriend(userId);
    }

    @PostMapping(path = "/requestAddFriend")
    public Object requestAddFriend(
            @RequestBody AddFriendRequest addFriendRequest
    ){
        return userManager.sendRequestAddFriend(addFriendRequest);
    }

    @GetMapping(path = "/getAllFriendWaitResponse")
    public Object getAllFriendWaitResponse(
            @Param("userID") int userId
    ){
        return userManager.getAllFriendWaitResponse(userId);
    }

    @PostMapping(path = "/decline")
    public void declined(
            @RequestBody AddFriendResponse addFriendResponse
    ){
        userManager.declined(addFriendResponse);
    }

    @PostMapping(path = "/accepted")
    public Object accepted(
            @RequestBody AddFriendResponse addFriendResponse
    ){
        return userManager.accepted(addFriendResponse);
    }
}
