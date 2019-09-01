package com.appchat.repository;

import com.appchat.model.data.FriendChated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendChatedRepository extends JpaRepository<FriendChated,Integer> {
    @Query(nativeQuery = true,
            value = "SELECT " +
                    "user_profile.id as friend_id," +
                    "user_profile.nameofchat as friend_nameofchat," +
                    "user_profile.username as friend_username," +
                    "user_profile.avatar as friend_avatar," +
                    "user_profile.date_created as created_time," +
                    "user_profile.phonenumber as phone " +
                    "FROM user_profile JOIN " +
                    "(SELECT distinct receiver_id FROM message where sender_id =:userId) aaa on" +
                    " aaa.receiver_id = user_profile.id")
    List<FriendChated> findSendedMess(
            @Param(value = "userId") int userId
    );
}
