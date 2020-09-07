package com.appchat.service;

import com.appchat.model.data.*;
import com.appchat.model.request.AddFriendRequest;
import com.appchat.model.request.LastMess;
import com.appchat.model.request.LoginRequest;
import com.appchat.model.request.RegisterRequest;
import com.appchat.model.response.AddFriendResponse;
import com.appchat.model.response.BaseResponse;
import com.appchat.model.response.MessageChatResponse;
import com.appchat.model.response.StoryChatResponse;
import com.appchat.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserProfileRepository userProfileRepository;
    private final FriendRepository friendRepository;
    private final MessageChatResponseRepository messageChatResponseRepository;
    private final RegisterRepository registerRepository;
    private final UpdateAvatarRepository updateAvatarRepository;
    private final FindNotFriendRepository findNotFriendRepository;
    private final MessFindLastMess messFindLastMess;
    private final StoryChatRepository storyChatRepository;
    private final FriendChatedRepository friendChatedRepository;
    private final AddFriendRepository addFriendRepository;
    private final FriendWaitAcceptRepository friendWaitAcceptRepository;
    private final DeclineFriend declineFriend;
    private final AcceptFriendRepository acceptFriendRepository;
    private final MessAllImage messAllImage;
    private final Unfriend unfriend;
    private final DeletMessWhenUnfriend deletMessWhenUnfriend;

    @Override
    public BaseResponse login(LoginRequest loginRequest) {
        UserProfile userProfile = userProfileRepository.findByUsername(loginRequest.getUsername());
        if (userProfile == null || !userProfile.getPassword().equals(loginRequest.getPassword())) {
            return BaseResponse.createResponse(0, "username or password is invalid");
        }
        return BaseResponse.createResponse(userProfile);
    }

    @Override
    public BaseResponse getAllFriends(int userId) {
        List<FriendChat> friends = friendRepository.findAllFriend(userId);
        if (friends == null) {
            return BaseResponse.createResponse(0, "id invalid");
        }
        return BaseResponse.createResponse(friends);
    }

    @Override
    public BaseResponse register(RegisterRequest registerRequest){
        UserProfile userProfile = userProfileRepository.findByUsername(registerRequest.getUsername());
        if (userProfile != null) {
            return BaseResponse.createResponse(0, "username is existed");
        }
        registerRepository.insertNewUser(registerRequest.getUsername(),registerRequest.getPassword(),registerRequest.getNameOfChat());
        registerRepository.save(registerRequest);
        return BaseResponse.createResponse(1,"register is successful");
    }

    @Override
    public BaseResponse getHistoryChat(int senderId, int receiverId) {
        return BaseResponse.createResponse(messageChatResponseRepository.getHistoryMessage(senderId, receiverId));
    }

    @Override
    public UserProfile changeAvatar(UpdateAvatar updateAvatar) {
        updateAvatarRepository.updateAvatar(updateAvatar.getPath(),updateAvatar.getId());
        updateAvatarRepository.save(updateAvatar);
        return userProfileRepository.findById(updateAvatar.getId());
    }

    @Override
    public Object findAllNotFriend(int userId){
        List<FriendToAdd> friendToAdds = findNotFriendRepository.findAllNotFriend(userId);
        if (friendToAdds == null){
            return 0;
        }
        return friendToAdds;
    }

    @Override
    public Object getAllLastMess(List<LastMess> lastMesses){
        List<MessageChatResponse> messageChatResponses = new ArrayList<>();
        for (LastMess i:lastMesses) {
            messageChatResponses.add(messFindLastMess.getLastMess(i.getSenderId(),i.getReceiverId()));
        }
        return messageChatResponses;
    }

    @Override
    public Object getAllFriendStoryChat(int userId) {
        List<StoryChatResponse> storyChatResponses = storyChatRepository.findAllFriendStory(userId);
        if(storyChatResponses == null){
            return BaseResponse.createResponse(0,"id invalid");
        }else {
            return BaseResponse.createResponse(storyChatResponses);
        }
    }

    @Override
    public Object getSenderMess(int userId){
        return friendChatedRepository.findSendedMess(userId);
    }

    @Override
    public Object sendRequestAddFriend(AddFriendRequest addFriendRequest){
        addFriendRepository.sendRequestAddFriend(addFriendRequest.getSender_id(),addFriendRequest.getReceiver_id(),addFriendRequest.getIs_send());
        return BaseResponse.createResponse(1,"send request friend is successful");
    }

    @Override
    public Object getAllFriendWaitResponse(int userId){
        return friendWaitAcceptRepository.allFriendWaitResponse(userId);
    }

    @Override
    public void declined(AddFriendResponse addFriendResponse){
        declineFriend.declined(addFriendResponse.getSender_id(),addFriendResponse.getReceive_id());
        BaseResponse.createResponse(0, "decline and removed");
    }

    @Override
    public Object accepted(AddFriendResponse addFriendResponse){
        acceptFriendRepository.accepted(addFriendResponse.getSender_id(),addFriendResponse.getReceive_id());
        return BaseResponse.createResponse(0,"accepted");
    }

    @Override
    public Object getAllImageInMess(String type,int sender_id,int receiver_id){
        return messAllImage.allImage(type,sender_id,receiver_id);
    }

    @Override
    public Object unfriend(int sender_id,int receive_id){
        unfriend.removeFriend(sender_id,receive_id);
        deletMessWhenUnfriend.unfriend(sender_id,receive_id);
        return BaseResponse.createResponse(0,"remove friend is successful");
    }
}
