package com.appchat.controler;

import com.appchat.service.UserService;
import com.appchat.model.data.UpdateAvatar;
import com.appchat.model.request.AddFriendRequest;
import com.appchat.model.request.LastMess;
import com.appchat.model.request.LoginRequest;
import com.appchat.model.request.RegisterRequest;
import com.appchat.model.response.AddFriendResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
@Transactional
public class UserController {
    private final UserService userService;

    @PostMapping(path = "/login")
    public Object login(
            @RequestBody LoginRequest login) {
        return userService.login(login);
    }

    @PostMapping(path = "/register")
    public Object register(
            @RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    @GetMapping(path = "/getAllFriend")
    public Object getAllFriend(
            @RequestParam int userId
    ) {
        return userService.getAllFriends(userId);
    }

    @GetMapping(path = "/getHistoryChat")
    public Object getHistoryChat(
            @RequestParam("senderId") int senderId,
            @RequestParam("receiverId") int receiverId
    ) {
        return userService.getHistoryChat(senderId, receiverId);
    }

    @PostMapping(path = "getAllLastMess")
    public Object getAllLastMess(
            @RequestBody List<LastMess> lastMess
    ){
        return userService.getAllLastMess(lastMess);
    }
    @GetMapping(path="/getAllFriendStory")
    public Object getAllFriendStory(
            @RequestParam int userId
    ){
        return userService.getAllFriendStoryChat(userId);
    }
    @PostMapping(path = "/changeAvatar")
    public Object changeAvatar(
            @RequestBody UpdateAvatar updateAvatar
            ){
        return userService.changeAvatar(updateAvatar);
    }

    @GetMapping(path = "getFriendSendedMess")
    public Object getFriendSendedMess(
            @RequestParam("userId") int userId
    ){
        return userService.getSenderMess(userId);
    }

    @GetMapping(path = "getAllNotFriend")
    public Object getAllNotFriend(
            @RequestParam("userId") int userId
    )
    {
        return userService.findAllNotFriend(userId);
    }

    @PostMapping(path = "/requestAddFriend")
    public Object requestAddFriend(
            @RequestBody AddFriendRequest addFriendRequest
    ){
        return userService.sendRequestAddFriend(addFriendRequest);
    }

    @GetMapping(path = "/getAllFriendWaitResponse")
    public Object getAllFriendWaitResponse(
            @Param("userID") int userId
    ){
        return userService.getAllFriendWaitResponse(userId);
    }

    @PostMapping(path = "/decline")
    public void declined(
            @RequestBody AddFriendResponse addFriendResponse
    ){
        userService.declined(addFriendResponse);
    }

    @PostMapping(path = "/accepted")
    public Object accepted(
            @RequestBody AddFriendResponse addFriendResponse
    ){
        return userService.accepted(addFriendResponse);
    }

    @GetMapping(path = "/allImg")
    public Object allImg(
            @RequestParam("type") String type,
            @RequestParam("senderId") int senderId,
            @RequestParam("receiverId") int receiverId
    ){
        return userService.getAllImageInMess(type,senderId,receiverId);
    }

    @PostMapping(path = "/removeFriend")
    public Object removeFriend(
            @RequestParam("sender_id") int sender_id,
            @RequestParam("receiver_id") int receiver_id
    ){
        return userService.unfriend(sender_id,receiver_id);
    }
}
