package com.appchat.component;

import com.appchat.model.data.*;
import com.appchat.model.request.AddFriendRequest;
import com.appchat.model.request.LastMess;
import com.appchat.model.request.RegisterRequest;
import com.appchat.model.response.*;
import com.appchat.repository.*;
import com.appchat.model.request.LoginRequest;
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
    private StoryChatRepository storyChatRepository;
    @Autowired
    private FriendChatedRepository friendChatedRepository;
    @Autowired
    private AddFriendRepository addFriendRepository;
    @Autowired
    private FriendWaitAcceptRepository friendWaitAcceptRepository;
    @Autowired
    private DeclineFriend declineFriend;
    @Autowired
    private AcceptFriendRepository acceptFriendRepository;
    @Autowired
    private MessAllImage messAllImage;
    @Autowired
    private Unfriend unfriend;
    @Autowired
    private DeletMessWhenUnfriend deletMessWhenUnfriend;

    public Object login(LoginRequest loginRequest) {
        UserProfile userProfile = userProfileRepository.findByUsername(loginRequest.getUsername());
        if (userProfile == null || !userProfile.getPassword().equals(loginRequest.getPassword())) {
            return BaseResponse.createResponse(0, "username or password is invalid");
        }
        return BaseResponse.createResponse(userProfile);
    }

    public Object getAllFriends(int userId) {
        List<FriendChated> friends = friendRepository.findAllFriend(userId);
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

    public Object changeAvatar(UpdateAvatar updateAvatar) {
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
    public Object getAllFriendStoryChat(int userId) {
        List<StoryChatResponse> storyChatResponses = storyChatRepository.findAllFriendStory(userId);
        if(storyChatResponses == null){
            return BaseResponse.createResponse(0,"id invalid");
        }else {
            return BaseResponse.createResponse(storyChatResponses);
        }
    }
    public Object getSenderMess(int userId){
        List<FriendChated> friends = friendChatedRepository.findSendedMess(userId);
        return friends;
    }

    public Object sendRequestAddFriend(AddFriendRequest addFriendRequest){
        addFriendRepository.sendRequestAddFriend(addFriendRequest.getSender_id(),addFriendRequest.getReceiver_id(),addFriendRequest.getIs_send());
        return BaseResponse.createResponse(1,"send request friend is successful");
    }

    public Object getAllFriendWaitResponse(int userId){
        List<FriendToAdd> friendToAdds = friendWaitAcceptRepository.allFriendWaitResponse(userId);
        return friendToAdds;
    }

    public Object declined(AddFriendResponse addFriendResponse){
        declineFriend.declined(addFriendResponse.getSender_id(),addFriendResponse.getReceive_id());
        return BaseResponse.createResponse(0,"decline and removed");
    }

    public Object accepted(AddFriendResponse addFriendResponse){
        acceptFriendRepository.accepted(addFriendResponse.getSender_id(),addFriendResponse.getReceive_id());
        return BaseResponse.createResponse(0,"accepted");
    }

    public Object getAllImageInMess(String type,int sender_id,int receiver_id){
        List<Message> messages = messAllImage.allImage(type,sender_id,receiver_id);
        return messages;
    }

    public Object unfriend(int sender_id,int receive_id){
        unfriend.removeFriend(sender_id,receive_id);
        deletMessWhenUnfriend.unfriend(sender_id,receive_id);
        return BaseResponse.createResponse(0,"remove friend is successful");
    }
}
