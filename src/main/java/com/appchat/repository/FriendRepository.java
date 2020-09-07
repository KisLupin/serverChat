package com.appchat.repository;


import com.appchat.model.data.FriendChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<FriendChat, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT " +
                    "friend.id as id, "+
                    "user_profile.id as friend_id, "+
                    "user_profile.nameofchat as friend_nameofchat, "+
                    "user_profile.username as friend_username, "+
                    "user_profile.avatar as friend_avatar, "+
                    "user_profile.phonenumber as phone  " +
                    "FROM " +
                    "friend JOIN user_profile ON friend.isaccept = 1 and " +
                    "((friend.sender_id = :userId AND friend.receive_id = user_profile.id) OR "+
                    "(friend.receive_id = :userId AND friend.sender_id = user_profile.id)) "
    )
    List<FriendChat> findAllFriend(
            @Param(value = "userId") int userId
    );

}
