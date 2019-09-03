package com.appchat.repository;

import com.appchat.model.data.FriendToAdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendWaitAcceptRepository extends JpaRepository<FriendToAdd,Integer> {
    @Query(nativeQuery = true, value = "select " +
            "user_profile.id as friend_id, " +
            "user_profile.nameofchat as friend_nameofchat, " +
            "user_profile.avatar as friend_avatar " +
            "from user_profile where id in " +
            "(select sender_id from friend where receive_id = :userId and isaccept = 0 and friend.send_addfriend = 1)")
    List<FriendToAdd> allFriendWaitResponse(
            @Param("userId") int userId
    );
}
