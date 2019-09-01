package com.appchat.component;

import com.appchat.model.data.*;
import com.appchat.model.request.LastMess;
import com.appchat.model.request.RegisterRequest;
import com.appchat.model.response.BaseResponse;
import com.appchat.model.response.MessageChatResponse;
import com.appchat.repository.*;
import com.appchat.model.request.LoginRequest;
import com.appchat.model.response.FriendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserManager {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private MessageChatResponseRepository messageChatResponseRepository;
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private UpdateAvatarRepository updateAvatarRepository;
    @Autowired
    private FindNotFriendRepository findNotFriendRepository;
    @Autowired
    private MessFindLastMess messFindLastMess;
    @Autowired
    private FriendChatedRepository friendChatedRepository;

    public Object login(LoginRequest loginRequest) {
        UserProfile userProfile = userProfileRepository.findByUsername(loginRequest.getUsername());
        if (userProfile == null || !userProfile.getPassword().equals(loginRequest.getPassword())) {
            return BaseResponse.createResponse(0, "username or password is invalid");
        }
        return BaseResponse.createResponse(userProfile);
    }

    public Object getAllFriends(int userId) {
        List<FriendResponse> friends = friendRepository.findAllFriend(userId);
        if (friends == null) {
            return BaseResponse.createResponse(0, "id invalid");
        }
        return BaseResponse.createResponse(friends);
    }

    @Transactional
    public Object register(RegisterRequest registerRequest){
        UserProfile userProfile = userProfileRepository.findByUsername(registerRequest.getUsername());
        if (userProfile != null)
            return BaseResponse.createResponse(0, "username is existed");
        registerRepository.insertNewUser(registerRequest.getUsername(),registerRequest.getPassword(),registerRequest.getNameofchat());
        registerRepository.save(registerRequest);
        return BaseResponse.createResponse(1,"register is successful");
    }

    public Object getHistoryChat(int senderId, int receiverId) {
        return BaseResponse.createResponse(messageChatResponseRepository.getHistoryMessage(senderId, receiverId));
    }

    public Object changeAvatar(UpdateAvatar updateAvatar)
    {
        updateAvatarRepository.updateAvatar(updateAvatar.getPath(),updateAvatar.getId());
        updateAvatarRepository.save(updateAvatar);
        UserProfile userProfile = userProfileRepository.findById(updateAvatar.getId());
        return userProfile;
    }

    public Object findAllNotFriend(int userId){
        List<FriendToAdd> friendToAdds = findNotFriendRepository.findAllNotFriend(userId);
        if (friendToAdds == null){
            return 0;
        }
        return friendToAdds;
    }

    public Object getAllLastMess(List<LastMess> lastMesses){
        List<MessageChatResponse> messageChatResponses = new ArrayList<>();
        for (LastMess i:lastMesses) {
            messageChatResponses.add(messFindLastMess.getLastMess(i.getSenderId(),i.getReceiverId()));
        }
        if (messageChatResponses == null){
            return 0;
        }
        return messageChatResponses;
    }

    public Object getSenderMess(int userId){
        List<FriendChated> friends = friendChatedRepository.findSendedMess(userId);
        return friends;
    }
}
