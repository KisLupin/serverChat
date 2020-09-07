package com.appchat.service;

import com.appchat.model.data.UpdateAvatar;
import com.appchat.model.data.UserProfile;
import com.appchat.model.request.AddFriendRequest;
import com.appchat.model.request.LastMess;
import com.appchat.model.request.LoginRequest;
import com.appchat.model.request.RegisterRequest;
import com.appchat.model.response.AddFriendResponse;
import com.appchat.model.response.BaseResponse;

import java.util.List;

public interface UserService {
    BaseResponse login(LoginRequest loginRequest);
    BaseResponse getAllFriends(int userId);
    BaseResponse register(RegisterRequest registerRequest);
    BaseResponse getHistoryChat(int senderId, int receiverId);
    UserProfile changeAvatar(UpdateAvatar updateAvatar);
    Object findAllNotFriend(int userId);
    Object getAllLastMess(List<LastMess> lastMesses);
    Object getAllFriendStoryChat(int userId);
    Object getSenderMess(int userId);
    Object sendRequestAddFriend(AddFriendRequest addFriendRequest);
    Object getAllFriendWaitResponse(int userId);
    void declined(AddFriendResponse addFriendResponse);
    Object accepted(AddFriendResponse addFriendResponse);
    Object getAllImageInMess(String type,int sender_id,int receiver_id);
    Object unfriend(int sender_id,int receive_id);
}
