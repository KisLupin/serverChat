package com.appchat.repository;

import com.appchat.model.data.FriendChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendChatedRepository extends JpaRepository<FriendChat,Integer> {
    @Query(nativeQuery = true,
            value = "SELECT " +
                    "user_profile.id as friend_id, " +
                    "user_profile.nameofchat as friend_nameofchat, " +
                    "user_profile.username as friend_username, " +
                    "user_profile.avatar as friend_avatar, " +
                    "user_profile.date_created as created_time, " +
                    "user_profile.phonenumber as phone " +
                    "FROM user_profile JOIN  " +
                    "(select sender_id from  " +
                    "(SELECT distinct sender_id,receiver_id FROM message where sender_id = :userId or receiver_id = :userId) aaa " +
                    "union " +
                    "select receiver_id from " +
                    "(SELECT distinct sender_id,receiver_id FROM message where sender_id = :userId or receiver_id = :userId) abc) xyz on " +
                    " xyz.sender_id = user_profile.id and sender_id != :userId")
    List<FriendChat> findSendedMess(
            @Param(value = "userId") int userId
    );
}
