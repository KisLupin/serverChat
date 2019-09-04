package com.appchat.repository;

import com.appchat.model.data.FriendToAdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FindNotFriendRepository extends JpaRepository<FriendToAdd,Integer> {
    @Query(nativeQuery = true,
            value = "select " +
                    "user_profile.id as friend_id, " +
                    "user_profile.nameofchat as friend_nameofchat, " +
                    "user_profile.avatar as friend_avatar  " +
                    "from  user_profile where id not in  " +
                    "(select sender_id as id from (SELECT sender_id,receive_id  from friend where sender_id = 1 or receive_id = 1) sad " +
                    "union " +
                    "select receive_id  from (SELECT sender_id,receive_id from friend where sender_id = 1 or receive_id = 1) sad " +
                    ")")
    List<FriendToAdd> findAllNotFriend(
            @Param(value = "userId") int userId
    );
}
