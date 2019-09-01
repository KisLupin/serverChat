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
                    "(SELECT receive_id as id " +
                    "FROM friend " +
                    "WHERE sender_id = :userId OR " +
                    "receive_id = :userId) and id not in (:userId)")
    List<FriendToAdd> findAllNotFriend(
            @Param(value = "userId") int userId
    );
}
